/**
 * volatile关键字:当多个线程操作共享数据时，保证内存可见性
 * 相较于synchronized是一种较为轻量级的同步策略
 *
 * 注意：
 * 1.volatile 不具备互斥性
 * 2.volatile  不能保证变量的“原子性”
 */
public class VolatileTest {
    public static void main(String[] args) {
        MyThread mtd = new MyThread();
        new Thread(mtd).start();
        while (true) {
            if (mtd.isFlag()) {
                System.out.println("---------Read form main memory-------");
                break;
            }
        }

    }
}

class MyThread implements Runnable {
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        flag = true;
        System.out.println("flag:" + flag);
    }
}
