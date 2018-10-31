package com.rifu.proxy;

/**
 * @author Rifu
 * @create 2018-05-17 10:38
 *
 * 静态代理类
 */
public class StaticProxyDemo {
    public static void main(String [] args){
        NikeFactory nikeFactory = new NikeFactory();
        ProxyFactory proxyFactory = new ProxyFactory(nikeFactory);
        proxyFactory.produceShoe();
    }
}

interface ShoesFactory{
    void produceShoe();
}
class NikeFactory implements ShoesFactory{

    @Override
    public void produceShoe() {
        System.out.println("Nike : has produced a serials shoes");
    }
}

/**
 * 代理类
 */
class ProxyFactory implements ShoesFactory{

    private ShoesFactory factory;

    public ProxyFactory(ShoesFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceShoe() {
        factory.produceShoe();
    }
}
