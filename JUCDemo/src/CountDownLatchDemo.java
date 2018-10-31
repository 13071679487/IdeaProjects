import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch   闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前线程才继续执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(5);
        CountDownLatchThread countDownLatchThread = new CountDownLatchThread(latch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(countDownLatchThread).start();
        }

        try {
            latch.await();      //闭锁等待
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));
    }
}

class CountDownLatchThread implements Runnable {
    private CountDownLatch latch;

    public CountDownLatchThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {

        } finally {
            latch.countDown();
        }
    }
}
