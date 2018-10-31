package com.rifu.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Rifu
 * @create 2018-05-17 12:47
 *
 * AOP(Aspect  Orient Programming):     面向切面编程
 */
public class AOPDemo {
    public static void main(String [] args){
        UserTransaction userTransaction=new UserTransaction();
        Object proxy = MyProxy.getInstance(userTransaction);
        Transaction transaction= (Transaction) proxy;
        transaction.execute();
        System.out.println(" ============== ");
        transaction.eat();
    }
}

interface Transaction{
    void execute();
    void eat();
}

class UserTransaction implements Transaction{

    @Override
    public void execute() {
        System.out.println("UserTransaction : execute");
    }

    @Override
    public void eat() {
        System.out.println("UserTransaction : eat");
    }
}

class AOPTransaction{
    public static  void preHandle(){
        System.out.println("preHandle : before the method is invoked");
    }

    public static void postHandle(){
        System.out.println("postHandle : after the method is invoked");
    }
}

class ProxyInvocationHandler implements InvocationHandler{
    private Object obj;

    public ProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        AOPTransaction.preHandle();

        Object returnVal = method.invoke(obj, args);

        AOPTransaction.postHandle();
        return returnVal;
    }
}

class MyProxy{
    public static Object getInstance(Object obj){
        ProxyInvocationHandler handler = new ProxyInvocationHandler(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}
