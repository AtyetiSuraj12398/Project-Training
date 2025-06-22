package eCommerceCompany.dataGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class TransactionDataGenerator {
    public static void main(String[] args) {
        int totalTransactions = 10000000;

        Map<String, double[]> categoryRanges = Map.of(
                "Electronics", new double[]{500, 50000},
                "Clothing", new double[]{200, 5000},
                "Grocery", new double[]{50, 2000}
        );


        List<String> categories = new ArrayList<>(categoryRanges.keySet());

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        TransactionGenratorTask task = new TransactionGenratorTask(0, totalTransactions, categories, categoryRanges);

        long start = System.currentTimeMillis();
        List<Transaction> transactions = forkJoinPool.invoke(task);
        long end = System.currentTimeMillis();

        System.out.println("Generated " + transactions.size() + " transactions.");
        System.out.println("Time taken: " + (end - start) + " ms");

        transactions.stream().limit(5).forEach(System.out::println);

    }

}
