package forkAndJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static java.lang.Thread.sleep;

public class JavaForkJoinPoolExample {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(4);

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(123);
//        forkJoinPool1.invoke(myRecursiveAction);

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(123);

//        long result = forkJoinPool1.invoke(myRecursiveTask);

        ForkJoinTask<Long> forkJoinTask = forkJoinPool1.submit(myRecursiveTask);

        try {
            Long result = forkJoinTask.get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        forkJoinPool1.getParallelism();  // getting parallelism
        forkJoinPool1.setParallelism(10); // setting parallelism
        forkJoinPool1.getPoolSize(); // get pool size
        forkJoinPool1.getQueuedSubmissionCount(); // currently queued task
        forkJoinPool1.getRunningThreadCount(); // running thread count
        forkJoinPool1.isShutdown();
        forkJoinPool1.isTerminated();
        forkJoinPool1.isTerminating();

        forkJoinPool1.shutdown(); // shutdown and finished all queued task and will not accept any new task
        forkJoinPool1.shutdownNow();  // shutdown right now without finishing queued task


//        System.out.println("Result: " + result);


    }

}
