package courseProject;

import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account {
    private String accountNumber;
    private String accountType;
    protected double serviceFee;
    protected double interestRate;
    protected double overdraftFee;
    protected double balance;
    private List<Transaction> transactions;

    // Constructor
    public Account(String accountNumber, String accountType, double serviceFee, double interestRate, double overdraftFee) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.serviceFee = serviceFee;
        this.interestRate = interestRate;
        this.overdraftFee = overdraftFee;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }

    public double getBalance() {
        return balance;
    }

    // Abstract methods for subclasses to implement
    public abstract void withdrawal(double amount);

    public abstract void deposit(double amount);

    // Method to apply interest
    public void applyInterest() {
        double interest = balance * (interestRate / 100);

        // Format the current date and time for the transaction
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        try {
        // Set the transaction details in the appropriate subclass
        if (this instanceof CheckingAccount) {
            ((CheckingAccount) this).setTransactionDetails(currentDateTime, "DEP", interest);
        } else if (this instanceof SavingsAccount) {
            ((SavingsAccount) this).setTransactionDetails(currentDateTime, "DEP", interest);
        }

        // Deposit the interest amount
        deposit(interest);

        // Record the transaction
        addTransaction("Interest", interest, 0.0);
    } catch (ParseException e) {
    	e.printStackTrace();
    }
 }

    // Method to add a transaction to the transaction list
    protected void addTransaction(String type, double amount, double fee) {
        Transaction transaction = new Transaction(LocalDateTime.now(), type, amount, fee, balance);
        transactions.add(transaction);
    }

    // Method to print transaction history

    public void printTransactionHistory(String customerId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.printf("Customer ID: %s | Account Number: %s | Account Type: %s%n",
                customerId, accountNumber, accountType); // Replace "C1234" with actual customer ID if available
        System.out.println("Date                 Type           Amount         Fee             Balance");
        System.out.println("---------------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }


    @Override
    public String toString() {
        return "\nAccount Information\n------------------- \n" +
                "Account Number: " + accountNumber + "\n" +
                "Account Type: " + accountType + "\n" +
                "Service Fee: $" + serviceFee + "\n" +
                "Interest Rate: " + interestRate + "%\n" +
                "Overdraft Fee: $" + overdraftFee + "\n" +
                "Balance: $" + balance;
    }
}
