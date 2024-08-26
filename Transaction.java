package courseProject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDateTime dateTime;
    private String type;
    private double amount;
    private double fee;
    private double balance;

    // Constructor that matches the expected parameters
    public Transaction(LocalDateTime dateTime, String type, double amount, double fee, double balance) {
        this.dateTime = dateTime;
        this.type = type;
        this.amount = amount;
        this.fee = fee;
        this.balance = balance;
    }

    // Getters for the fields, if needed
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    public double getBalance() {
        return balance;
    }

    // Override toString() method to format the transaction details
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-20s %-15s %-15.2f %-15.2f %-15.2f",
                dateTime.format(formatter),
                type,
                amount,
                fee,
                balance);
    }
}
