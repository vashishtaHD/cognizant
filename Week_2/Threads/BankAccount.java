package Week_2.Threads;

class Transaction extends Thread {
    private BankAccount account;
    private boolean isDeposit;
    private double amount;

    public Transaction(BankAccount account, boolean isDeposit, double amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class BankAccount {
    private double balance = 0;

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", New balance: " + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal of " + amount);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Transaction[] transactions = new Transaction[6];

        transactions[0] = new Transaction(account, true, 100);
        transactions[1] = new Transaction(account, true, 50);
        transactions[2] = new Transaction(account, false, 30);
        transactions[3] = new Transaction(account, true, 70);
        transactions[4] = new Transaction(account, false, 50);
        transactions[5] = new Transaction(account, false, 150);

        for (Transaction t : transactions) {
            t.start();
        }

        for (Transaction t : transactions) {
            t.join();
        }

        System.out.println("All transactions completed");
    }
}
