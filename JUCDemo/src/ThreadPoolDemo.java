import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Rifu
 * @create 2018-05-15 11:09
 * <p>
 * 线程池：提供了一个线程对列，对列中保存着所有等待状态的线程。避免创建和销毁的额外开销，提高相应速度（Executor）
 * 线程池的体系结构：
 * java.util.concurrent.Executor:   负责线程的使用与调度的根接口
 * \  --   ExecutorService 子接口：线程池的主要接口
 * \--     ThreadPoolExecutor  实现类
 * \--     ScheduledExecutorService    子接口：负责线程的调度
 * \--ScheduledThreadPoolExecutor  :继承ThreadPoolExecutor，实现ScheduledExecutorService
 * <p>
 * 推荐使用工厂方法获得线程池
 * 工具类：Executors
 * ExecutorService ----newFixedThreadPool():    创建固定大小的线程池
 * ExecutorService ----newCachedThreadPool():   缓存线程池，线程池的数量不固定，可以根据需求自动更改数量
 * ExecutorService ----newSingleThreadExecutor():   创建单个线程池
 * <p>
 * ScheduledExecutorService ----newScheduledThreadPool():    创建固定大小的线程池，可以延时或定时执行任务
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        new ThreadPoolDemo().testFixedThreadPool2();
    }

    /**
     * 线程池使用Runnable方式
     */
    public void testFixedThreadPool(){
        //1.创建线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        //2.为线程池中的线程分配任务
        SubThread subThread = new SubThread();
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(subThread);
        }
        //3.关闭线程池
        fixedThreadPool.shutdown();
    }

    /**
     * 线程池使用callable方式
     */
    public void testFixedThreadPool2(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        List<Future<Integer>> list=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = fixedThreadPool.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    return 5;
                }
            });
            list.add(future);
        }

        fixedThreadPool.shutdown();

        for (Future<Integer> f:list) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class SubThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() );
    }
}




