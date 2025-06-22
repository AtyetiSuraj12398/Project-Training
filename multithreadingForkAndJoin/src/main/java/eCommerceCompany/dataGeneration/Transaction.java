package eCommerceCompany.dataGeneration;

public class Transaction {
    private final double amount;
    private final String category;

    public Transaction(double amount, String category) {
        this.amount = amount;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Transaction{" + "amount=" + amount + ", category='" + category + '\'' + '}';
    }

}
