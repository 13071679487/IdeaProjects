/**
 * @author Rifu
 * @create 2018-05-15 10:39
 * <p>
 * 线程8锁
 * 应用题目：判断打印的是“1”还是“2”
 * 1.两个普通的同步方法，两个线程     结果：1    2
 * 2.让getOne()休眠3秒，同上               结果：1    2
 * 3.新增普通方法getThree()   三个线程    结果：3    1   2
 * 4.两个普通同步方法，两个Number对象    结果：2    1
 * 5.修改getOne()为静态同步方法，   结果：2      1
 * 6.两个方法都是静态同步方法，      结果：1    2
 * 7.getOne()为静态同步方法，getTwo为非静态同步方法，两个Number对象      结果：2    1
 * 8.两个静态同步方法，两个Number对象，结果：1   2
 * <p>
 * <p>
 * 知识点：
 * ①静态方法的锁默认锁的是this,就是类，非静态方法锁的是对应的class的实例
 * ②某一时刻内，只能有一个线程持有锁，无论几个方法
 */
public class Thread8Monitor {
    public static void main(String[] args) {
        final Number number = new Number();
        final Number number2 = new Number();
        System.out.println("number1 : "+number.toString());
        System.out.println("number2 : "+number2.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getOne();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getTwo();
            }
        }).start();
    }
}

class Number {

    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " : " + 1+"\t"+Number.class);
    }

    public static  synchronized void getTwo() {
        System.out.println(Thread.currentThread().getName() + " : " + 2);
    }

}
