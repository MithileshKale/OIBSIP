import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String userId;
    private String pin;
    private Account account;
    private List<Transaction> transactions;

    public User(String name, String userId, String pin) {
        this.name = name;
        this.userId = userId;
        this.pin = pin;
        this.account = new Account();
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
