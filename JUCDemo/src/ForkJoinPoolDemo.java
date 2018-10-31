import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author Rifu
 * @create 2018-05-15 12:26
 *
 * 分支合并     --框架(JDK 1.7)
 */
public class ForkJoinPoolDemo {
    public static void main(String [] args){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSumCalculate calculate = new ForkJoinSumCalculate(0L, 100000000L);
        Long sum = pool.invoke(calculate);
        System.out.println(" sum : "+sum);
        Instant end = Instant.now();

        System.out.println("耗费时间 : "+Duration.between(start,end).toMillis());       //242
    }

    @Test
    public void testCalcSum(){
        Instant start = Instant.now();
        Long sum = 0L;
        for (long i = 0; i < 100000000L; i++) {
            sum+=i;
        }
        System.out.println("sum : "+sum);
        Instant end = Instant.now();

        System.out.println("耗费时间 : "+Duration.between(start,end).toMillis());       //895

    }

    /**
     * 使用Java8新特性
     */
    @Test
    public void testCalcSum2(){
        Instant start = Instant.now();
        Long sum =  LongStream.rangeClosed(0L,100000000L)
                    .parallel()
                .reduce(0L,Long::sum);
        System.out.println("sum : "+sum);


        Instant end = Instant.now();
        System.out.println("耗费时间 : "+Duration.between(start,end).toMillis());       //207
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long>{

    private long start;
    private long end;

    private static final long THURSHOLD=1000L;     //临界值

    public ForkJoinSumCalculate(long start,long end){
        this.start=start;
        this.end=end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if(length<=THURSHOLD){
            long sum=0L;
            for (long i = start; i <=end; i++) {
                sum+=i;
            }
            return sum;
        }else {
            long middle=(start+end)/2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();

            return left.join()+right.join();
        }
    }
}
