import java.util.ArrayList;

// ================= CUSTOM EXCEPTIONS =================

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

// ================= BASE CLASS =================

abstract class BankAccount {

    private String accountNumber;
    private String accountHolder;
    protected double balance;

    public BankAccount(String accNo, String holder, double balance) {
        this.accountNumber = accNo;
        this.accountHolder = holder;
        this.balance = Math.max(balance, 0);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    public abstract void withdraw(double amount)
            throws InvalidAmountException, InsufficientFundsException;

    public String toString() {
        return "Account: " + accountNumber +
               ", Holder: " + accountHolder +
               ", Balance: $" + balance;
    }
}

// ================= SAVINGS ACCOUNT =================

class SavingsAccount extends BankAccount {

    private double interestRate;
    private static final double MIN_BALANCE = 100;

    public SavingsAccount(String accNo, String holder, double balance, double rate) {
        super(accNo, holder, balance);
        this.interestRate = rate;
    }

    @Override
    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientFundsException {

        if (amount <= 0)
            throw new InvalidAmountException("Withdrawal amount must be positive.");

        if (balance - amount < MIN_BALANCE)
            throw new InsufficientFundsException("Minimum balance of $100 required.");

        balance -= amount;
    }

    public void applyInterest() {
        balance += balance * interestRate;
    }
}

// ================= CHECKING ACCOUNT =================

class CheckingAccount extends BankAccount {

    private double overdraftLimit;

    public CheckingAccount(String accNo, String holder, double balance, double limit) {
        super(accNo, holder, balance);
        this.overdraftLimit = limit;
    }

    @Override
    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientFundsException {

        if (amount <= 0)
            throw new InvalidAmountException("Withdrawal amount must be positive.");

        if (amount > balance + overdraftLimit)
            throw new InsufficientFundsException("Overdraft limit exceeded.");

        balance -= amount;
    }
}

// ================= BANK MANAGEMENT CLASS =================

class Bank {

    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account added: " + account.getAccountNumber());
    }

    public BankAccount findAccount(String accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                return acc;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        System.out.println("\n--- All Bank Accounts ---");
        for (BankAccount acc : accounts) {
            System.out.println(acc);
        }
    }

    public double calculateTotalBalance() {
        double total = 0;
        for (BankAccount acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    public void applyInterestToSavingsAccounts() {
        for (BankAccount acc : accounts) {
            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).applyInterest();
            }
        }
        System.out.println("Interest applied to all savings accounts.");
    }
}

// ================= MAIN SYSTEM TEST =================

public class BankingSystemOOP {

    public static void main(String[] args) {

        Bank bank = new Bank();

        BankAccount s1 = new SavingsAccount("SA101", "Alice", 1000, 0.05);
        BankAccount c1 = new CheckingAccount("CA201", "Bob", 500, 300);

        bank.addAccount(s1);
        bank.addAccount(c1);

        try {
            s1.withdraw(950);     // will fail (minimum balance)
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            c1.withdraw(700);     // allowed overdraft
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            s1.deposit(-50);      // invalid deposit
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        bank.applyInterestToSavingsAccounts();

        bank.displayAllAccounts();

        System.out.println("\nTotal Bank Balance: $" + bank.calculateTotalBalance());
    }
}
