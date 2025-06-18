package example1;

import java.util.concurrent.*;

public class ExecutorServiceExample1 {
    public static void main(String[] args) {
        int corePoolSize = 10;
        int maxPoolSize = 20;
        long keepAliveTime = 3000;

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(128));

        threadPoolExecutor =(ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);



    }
}
