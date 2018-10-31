import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式有四种，其三是实现Callable接口
 * 相对于实现Runnable接口的方式，这个方法可以有返回值，并且可以抛出异常
 *
 * 执行callable的方式，需要FutureTask实现类的支持，用于接收运算结果
 */
public class CallableDemo {
    public static void main(String[] args){
        CallableThread ct=new CallableThread();
        new CallableDemo();
        FutureTask<Integer> task=new FutureTask<>(ct);
        new Thread(task).start();
        try {
            Integer num=task.get();                 //可用于闭锁，等线程执行完才会有返回结果
            System.out.println(num+"");
        }catch (Exception e){
        }
    }
}

class CallableThread implements Callable<Integer>{
    @Override
    public Integer call() {
        int num=0;
        return num;
    }
}


