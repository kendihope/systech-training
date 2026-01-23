public class BankingSystem {

    // ================= BASE CLASS =================
    static class BankAccount {

        private String accountNumber;
        private String accountHolder;
        protected double balance;

        public BankAccount(String accountNumber, String accountHolder, double balance) {

            if (accountNumber == null || accountNumber.trim().isEmpty()) {
                System.out.println("Invalid account number.");
                accountNumber = "UNKNOWN";
            }

            if (accountHolder == null || accountHolder.trim().isEmpty()) {
                System.out.println("Invalid account holder.");
                accountHolder = "UNKNOWN";
            }

            if (balance < 0) {
                System.out.println("Invalid balance. Setting to 0.");
                balance = 0;
            }

            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit must be positive.");
                return;
            }
            balance += amount;
            System.out.println("Deposited: $" + amount);
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal must be positive.");
                return;
            }

            if (amount > balance) {
                System.out.println("Insufficient funds.");
                return;
            }

            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        }
    }

    // ================= SAVINGS ACCOUNT =================
    static class SavingsAccount extends BankAccount {

        private double interestRate;
        private static final double MIN_BALANCE = 100.0;

        public SavingsAccount(String accNo, String holder, double balance, double interestRate) {
            super(accNo, holder, balance);

            if (interestRate < 0) {
                System.out.println("Invalid interest rate. Setting to 0.");
                interestRate = 0;
            }

            this.interestRate = interestRate;
        }

        @Override
        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal must be positive.");
                return;
            }

            if (balance - amount < MIN_BALANCE) {
                System.out.println("Cannot withdraw. Minimum balance of $100 required.");
                return;
            }

            balance -= amount;
            System.out.println("Withdrawn from Savings: $" + amount);
        }

        public void applyInterest() {
            double interest = balance * interestRate;
            balance += interest;
            System.out.println("Interest applied: $" + interest);
        }
    }

    // ================= CHECKING ACCOUNT =================
    static class CheckingAccount extends BankAccount {

        private double overdraftLimit;

        public CheckingAccount(String accNo, String holder, double balance, double overdraftLimit) {
            super(accNo, holder, balance);

            if (overdraftLimit < 0) {
                System.out.println("Invalid overdraft limit. Setting to 0.");
                overdraftLimit = 0;
            }

            this.overdraftLimit = overdraftLimit;
        }

        @Override
        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal must be positive.");
                return;
            }

            if (amount > balance + overdraftLimit) {
                System.out.println("Overdraft limit exceeded.");
                return;
            }

            balance -= amount;
            System.out.println("Withdrawn from Checking: $" + amount);
        }
    }

    // ================= MAIN (POLYMORPHISM DEMO) =================
    public static void main(String[] args) {

        BankAccount acc1 = new SavingsAccount("SA001", "Alice", 1000, 0.05);
        BankAccount acc2 = new CheckingAccount("CA001", "Bob", 500, 300);

        // Polymorphic calls
        acc1.withdraw(950);   // SavingsAccount logic
        acc2.withdraw(700);   // CheckingAccount logic

        acc1.deposit(200);
        acc2.deposit(100);

        System.out.println("Savings Balance: $" + acc1.getBalance());
        System.out.println("Checking Balance: $" + acc2.getBalance());

        // Downcasting
        if (acc1 instanceof SavingsAccount) {
            ((SavingsAccount) acc1).applyInterest();
        }

        System.out.println("Savings Balance after interest: $" + acc1.getBalance());
    }
}
