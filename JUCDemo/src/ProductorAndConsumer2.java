import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Rifu
 * @create 2018-05-14 19:35
 *
 *使用lock解决同步锁的问题
 * 生产者和消费者的问题
 */
public class ProductorAndConsumer2 {
    public static void main(String [] args){
        Clerk1 clerk = new Clerk1();
        Procutor1 procutor = new Procutor1(clerk);
        Consumer1 consumer = new Consumer1(clerk);

        new Thread(procutor,"生产者1").start();
        new Thread(consumer,"消费者1").start();
    }
}

/**
 * 店员
 */
class Clerk1{
    private int product=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public  void get(){
        lock.lock();
        try {
            while(product > 10){        //为了避免虚假唤醒，这里使用的是while而不是if
                System.out.println("产品已满！");
                try{
                    condition.await();
                }catch(Exception e){
                }finally{
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ ++product);

            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public  void sale(){
        lock.lock();
        try {
            while(product <= 0){
                System.out.println("缺货");
                try{
                    condition.await();
                }catch(Exception e){
                }finally{
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ --product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

class  Procutor1 implements Runnable{
    private Clerk1 clerk;

    public Procutor1(Clerk1 c){
        this.clerk=c;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            try{
                Thread.sleep(200);
            }catch(Exception e){

            }finally{
            }
            clerk.get();
        }
    }
}

class Consumer1 implements Runnable{

    private Clerk1 clerk;

    public Consumer1(Clerk1 c){
        this.clerk=c;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            clerk.sale();
        }
    }
}
