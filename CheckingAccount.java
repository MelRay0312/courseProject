package courseProject;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class CheckingAccount extends Account implements AccountInterface {
    public static final double SERVICE_FEE = 0.50;
    public static final double OVERDRAFT_FEE = 30.00;
    private Date transactionDate;
    private String transactionType;
    private double transactionAmount;

    private static final double INTEREST_RATE = 2.0;

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, "CHK", SERVICE_FEE, INTEREST_RATE, OVERDRAFT_FEE);
        this.balance = balance;
    }

    // Method to set transaction details
    public void setTransactionDetails(String date, String type, double amount) throws ParseException {
        SimpleDateFormat dateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormatWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.transactionDate = dateFormatWithTime.parse(date);
        } catch (ParseException e) {
            this.transactionDate = dateFormatWithoutTime.parse(date);
        }
        this.transactionType = type;
        this.transactionAmount = amount;
    }

    @Override
    public void withdrawal(double amount) {
        if (balance - amount - SERVICE_FEE < 0) {
            balance -= (amount + SERVICE_FEE + OVERDRAFT_FEE);
            addTransaction("Overdraft", amount, SERVICE_FEE + OVERDRAFT_FEE);
            System.out.println("Overdraft fee applied.");
        } else {
            balance -= (amount + SERVICE_FEE);
            addTransaction("Withdraw", amount, SERVICE_FEE);
        }
    }

    @Override
    public void deposit(double amount) {
        balance += (amount - SERVICE_FEE);
        addTransaction("Deposit", amount, SERVICE_FEE);
    }

    @Override
    public double balance() {
        return balance;
    }
}
