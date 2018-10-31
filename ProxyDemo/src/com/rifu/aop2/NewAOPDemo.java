package com.rifu.aop2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Rifu
 * @createtime 2018-05-23  上午10:40
 * Desc:
 */
public class NewAOPDemo {
    public static void main(String[] args) {
        Object proxy = ProxyFactory.getProxy(new UserTransaction(), new UserAop());
        Transaction userTransaction= (Transaction) proxy;               //在這裡需要注意用的是接口，不是接口的实现类
        userTransaction.execute();
    }
}

interface Transaction{
    void execute();
}

class UserTransaction implements Transaction{

    @Override
    public void execute() {
            System.out.println("UserTransaction : execute");
    }
}

interface AOP{
    void preHandle();
    void postHandle();
}

class UserAop implements AOP{

    @Override
    public void preHandle() {
            System.out.println("UserAop : preHandle");
    }

    @Override
    public void postHandle() {
    System.out.println("UserAop : postHandle");
    }
}

class ProxyInvocationHandler implements InvocationHandler {
    private Object proxyObj;        //需要被代理的类对象
    private AOP aopObj;

    public ProxyInvocationHandler(Object proxyObj,AOP aopObj) {
        this.proxyObj = proxyObj;
        this.aopObj=aopObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        aopObj.preHandle();
        Object returnValue = method.invoke(proxyObj,args);
        aopObj.postHandle();
        return returnValue;
    }
}

class ProxyFactory{
    public static Object getProxy(Object obj,AOP aopObj){
        ProxyInvocationHandler handler = new ProxyInvocationHandler(obj, aopObj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}
