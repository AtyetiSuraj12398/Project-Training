package eCommerceCompany.dataGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class TransactionGenratorTask extends RecursiveTask<List<Transaction>> {

    private static final int THRESHOLD = 10000;
    private final int start;
    private final int end;
    private final List<String> categories;
    private final Map<String, double[]> categoryRanges;


    public TransactionGenratorTask(int start, int end, List<String> categories, Map<String, double[]> categoryRanges) {
        this.start = start;
        this.end = end;
        this.categories = categories;
        this.categoryRanges = categoryRanges;
    }


    private List<Transaction> generateTrasanctions() {
        List<Transaction> transactions = new ArrayList<>(end - start);

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = start; i < end; i++) {
            String category = categories.get(random.nextInt(categories.size()));
            double[] range = categoryRanges.get(category);
            double amount = range[0] + (range[1] - range[0]) * random.nextDouble();

            transactions.add(new Transaction(amount, category));
        }

        return transactions;
    }


    @Override
    protected List<Transaction> compute() {

        if (end - start <= THRESHOLD) {
            return generateTrasanctions();
        } else {
            int mid = (start + end) / 2;
            TransactionGenratorTask leftTask = new TransactionGenratorTask(start, mid, categories, categoryRanges);
            TransactionGenratorTask rightTask = new TransactionGenratorTask(mid, end, categories, categoryRanges);

            leftTask.fork();
            List<Transaction> rightResult = rightTask.compute();
            List<Transaction> leftResult = leftTask.join();

            leftResult.addAll(rightResult);

            return leftResult;

        }


    }
}
