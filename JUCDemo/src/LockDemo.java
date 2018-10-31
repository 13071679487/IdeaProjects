
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rifu
 * @create 2018-05-14 19:18
 *
 * 知识点：用于解决多线程安全问题的方式
 * synchronized:    (隐式锁)
 * 1.同步代码块
 * 2.同步方法
 *
 * lock()  ----unlock()     (显式锁)
 * 3.同步锁(JDK 1.5以后)
 *
 */
public class LockDemo {
    public static void main(String [] args){
        SaleTicket ticket = new SaleTicket();
        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();
    }
}
class SaleTicket implements  Runnable{

    private int ticket = 100;

    private Lock lock=new ReentrantLock();
    @Override
    public void run() {
        while (true){
            lock.lock();
            try{
                if(ticket > 0){
                    try{
                        Thread.sleep(50);
                    }catch(Exception e){

                    }finally{

                    }
                    System.out.println("完成售票，当前剩余票数:"+ --ticket);
                }else{
                    System.exit(1);
                }
            }catch(Exception e){
            }finally{
                lock.unlock();
            }

        }
    }
}
