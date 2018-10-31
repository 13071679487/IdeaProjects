import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Rifu
 * @create 2018-05-15 12:13
 * <p>
 * 线程调度
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 5; i++) {
            ScheduledFuture<Integer> schedule = scheduledThreadPool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);            //延迟1秒执行

            try {
                System.out.println(" : " + schedule.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        scheduledThreadPool.shutdown();
    }
}
