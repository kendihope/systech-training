import java.util.HashMap;

// ================= CUSTOM EXCEPTIONS =================

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

// ================= BANK ACCOUNT CLASS =================

class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance)
            throws InvalidTransactionException {

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new InvalidTransactionException("Invalid account number.");
        }
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new InvalidTransactionException("Invalid account holder.");
        }
        if (balance < 0) {
            throw new InvalidTransactionException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // ===== Deposit =====
    public void deposit(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    // ===== Withdraw =====
    public void withdraw(double amount)
            throws InvalidTransactionException, InsufficientFundsException {

        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be positive.");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }

        balance -= amount;
    }

    // ===== Transfer =====
    public void transfer(BankAccount toAccount, double amount)
            throws InvalidTransactionException, InsufficientFundsException, AccountNotFoundException {

        if (toAccount == null) {
            throw new AccountNotFoundException("Target account does not exist.");
        }

        this.withdraw(amount);
        toAccount.deposit(amount);
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               ", Holder: " + accountHolder +
               ", Balance: $" + balance;
    }
}

// ================= BANK CLASS (ACCOUNT MANAGEMENT) =================

class Bank {

    private HashMap<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public BankAccount findAccount(String accountNumber)
            throws AccountNotFoundException {

        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
        return account;
    }
}

// ================= MAIN SYSTEM TEST =================

public class BankTransactionSystem {

    public static void main(String[] args) {

        Bank bank = new Bank();

        try {
            BankAccount acc1 = new BankAccount("A1001", "Alice", 1000);
            BankAccount acc2 = new BankAccount("A2002", "Bob", 500);

            bank.addAccount(acc1);
            bank.addAccount(acc2);

            // Deposit
            acc1.deposit(200);

            // Withdrawal
            acc2.withdraw(100);

            // Transfer
            acc1.transfer(acc2, 300);

            // Invalid transfer (non-existent account)
            BankAccount fake = null;
            acc1.transfer(fake, 100);

        } catch (InvalidTransactionException |
                 InsufficientFundsException |
                 AccountNotFoundException e) {

            System.out.println("Transaction Error: " + e.getMessage());
        }

        try {
            BankAccount acc = bank.findAccount("A1001");
            System.out.println(acc);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
