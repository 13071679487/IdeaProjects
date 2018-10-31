import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性问题
 * 问题描述：i++的问题，i++实际上分为三个步骤（读、改、写）
 * int temp = i ;
 * i=i+1;
 * i=temp;
 * <p>
 * 2.为了解决原子性问题，提出了原子变量（jdk1.5以后，java.util.concurrent.atomic）
 * 2.1)volatile    保证了内存可见性
 * 2.2)CAS算法(Compare And Swap)     比较并替换
 */
public class AtomicDemo {

    public static void main(String[] args) {
        AtomicThread atomicThread = new AtomicThread();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicThread).start();
        }
    }
}

class AtomicThread implements Runnable {
//    private int serialNum = 0;

    private AtomicInteger serialNum = new AtomicInteger();

    public int getSerialNum() {
        return serialNum.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNum());
    }
}
