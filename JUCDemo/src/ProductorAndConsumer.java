/**
 * @author Rifu
 * @create 2018-05-14 19:35
 *
 *
 * 生产者和消费者的问题
 * 使用synchronized解决同步的问题
 */
public class ProductorAndConsumer {
    public static void main(String [] args){
        Clerk clerk = new Clerk();
        Procutor procutor = new Procutor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(procutor,"生产者1").start();
        new Thread(consumer,"消费者1").start();

        new Thread(procutor,"生产者2").start();
        new Thread(consumer,"消费者2").start();
    }
}

/**
 * 店员
 */
class Clerk{
    private int product=0;

    public synchronized void get(){
        while(product > 10){        //为了避免虚假唤醒，这里使用的是while而不是if
            System.out.println("产品已满！");
            try{
                this.wait();
            }catch(Exception e){
            }finally{
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ ++product);
        this.notifyAll();
    }

    public synchronized void sale(){
        while(product <= 0){
            System.out.println("缺货");
            try{
                this.wait();
            }catch(Exception e){
            }finally{
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ --product);
        this.notifyAll();
    }
}
class  Procutor implements Runnable{
    private Clerk clerk;

    public Procutor(Clerk c){
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
class Consumer implements Runnable{

    private Clerk clerk;

    public Consumer(Clerk c){
        this.clerk=c;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            clerk.sale();
        }
    }
}
