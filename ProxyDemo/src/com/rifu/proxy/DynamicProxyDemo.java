package com.rifu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Rifu
 * @create 2018-05-17 10:43
 *
 * 动态代理
 */
public class DynamicProxyDemo {
    public static void main(String [] args){
        //1.创建被代理的对象实例
        RealSubject realSubject = new RealSubject();
        //2.创建一个实现了InvocationHandler接口的对象
        ProxyInvocationHandler handler = new ProxyInvocationHandler();
        //3.调用blind方法，返回一个同样实现了realSubject所在类实现了Subject接口的代理类对象
        Object proxy = handler.blind(realSubject);
        Subject sub= (Subject) proxy;   //此时的sub就是最终的代理类对象

        sub.action();
    }
}

interface Subject{
    void action();
}
//被代理类
class RealSubject implements Subject{

    @Override
    public void action() {
        System.out.println("RealSubject : i will be  proxy");
    }
}

/**
 * ProxyInvocationHandler：生产动态代理类的InvocationHandler
 */
class ProxyInvocationHandler implements InvocationHandler{
    Object factory;

    //通过被代理类的实例化对象，返回代理类的实例
    public Object blind(Object obj){
        this.factory=obj;
        /**
         * 1.factory.getClass().getClassLoader()            类的加载器
         * 2.obj.getClass().getInterfaces()                 类实现的所有接口
         * 3.this                                                           InvocationHandler,绑定以进行invoke的调用
         */
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    /**
     * 当通过代理类的对象发起对被代理类的方法调用时都会转换为对invoke方法的调用
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ProxyInvocationHandler : invoke");
        Object returnVal = method.invoke(factory, args);
        return returnVal;
    }
}
