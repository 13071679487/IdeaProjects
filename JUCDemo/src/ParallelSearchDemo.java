import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rifu
 * @create 2018-08-28 15:10
 * 并行搜索算法
 * 应用场景：
 * 搜索一个数组里面指定的值，不存在则返回-1，使用分割数组多线程来查询实现
 */
public class ParallelSearchDemo {
    static int[] arr;

    static ExecutorService pool=Executors.newCachedThreadPool();
    static final int THREAD_NUM= 5 ;        //默认开启两个线程去搜索值
    static AtomicInteger result = new AtomicInteger(-1);

    public static void main(String [] args){
        initData();
        long start=System.currentTimeMillis();
        try {
            int index=execute(88888888);
            long end = System.currentTimeMillis();

            System.out.println("使用并行搜索：index : "+index+"------总共耗时："+(end - start));

            long start1=System.currentTimeMillis();
            int index1 = normalSearch(88888888);
            long end1 = System.currentTimeMillis();
            System.out.println("一般搜索：index : "+index1+"------总共耗时："+(end1 - start1));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void initData(){
        arr=new int[100000000];
        for(int i =0 ;i< arr.length;i++){
            arr[i]=i;
        }
    }

    public static int normalSearch(int searchValue){
        for(int i =0 ;i< arr.length;i++){
            if(searchValue == arr[i])
                return i;
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer>{
        int start,end,searchValue;

        public SearchTask(int start, int end, int searchValue) {
            this.start = start;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() throws Exception {
            return search(searchValue,start,end);
        }
    }


    public static int search(int searchValue,int start,int end){
        int i = start;
        for(;i<end;i++){
            if(result.get()>0){
                return result.get();
            }
            if(arr[i] == searchValue){
                //如果设置失败，则表示其他线程找到了
                if(result.compareAndSet(-1,i)){
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static int execute(int searchValue) throws InterruptedException,ExecutionException {
        int size = arr.length/THREAD_NUM+1;
        List<Future<Integer>> resultList=new ArrayList<>();
        for(int i =0;i<arr.length;i+=size){
            int end = i+size;
            if(end >= arr.length)
                end= arr.length;
            resultList.add(pool.submit(new SearchTask(i,end,searchValue)));
        }
        for(Future<Integer> f:resultList){
            if(f.get()>=0) return f.get();
        }
        return -1;
    }



}
