package courseProject;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.time.LocalDate;

public class BankAcctApp {

    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create customers and their accounts
        while (true) {
            System.out.println("Enter customer details \n----------------------");
            String id = DataEntry.inputString("Customer ID (max 5 chars): ", 5);
            String ssn = DataEntry.inputStringNumeric("Customer SSN (9 digits): ", 9);
            String lastName = DataEntry.inputString("Last Name (max 20 chars): ", 20);
            String firstName = DataEntry.inputString("First Name (max 15 chars): ", 15);
            String street = DataEntry.inputString("Street (max 20 chars): ", 20);
            String city = DataEntry.inputString("City (max 20 chars): ", 20);
            String state = DataEntry.inputString("State (2 chars): ", 2);
            String zip = DataEntry.inputString("Zip (5 digits): ", 5);
            String phone = DataEntry.inputString("Phone (10 digits): ", 10);

            Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);

            Account account = null;
            while (account == null) {
                try {
                    String accountNumber = DataEntry.inputString("Account Number (max 5 chars): ", 5);
                    String accountType = DataEntry.inputString("Account Type (CHK or SAV): ", 3).toUpperCase();
                    double initialBalance = DataEntry.inputDouble("Initial Balance: ", 0, Double.MAX_VALUE);

                    if (accountType.equals("CHK")) {
                        account = new CheckingAccount(accountNumber, initialBalance);
                    } else if (accountType.equals("SAV")) {
                        account = new SavingsAccount(accountNumber, initialBalance);
                    } else {
                        throw new IllegalArgumentException("Invalid account type. Must be 'CHK' or 'SAV'.");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage() + " Please enter the account details again.");
                }
            }

            customer.setAccount(account);
            customers.add(customer);

            System.out.print("Do you want to add another customer? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }

            System.out.println();
        }

        // Step 2: Interactive Menu for Account Operations
        System.out.println("\nPerforming Transactions...");
        for (Customer customer : customers) {
            Account account = customer.getAccount();
            while (true) {
                System.out.println("\nChoose an option for account " + account.getAccountNumber() + ": ");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Apply Interest");
                System.out.println("4. View Balance");
                System.out.println("5. Exit");
                
                int choice = -1;
                boolean validInput = false;
                
                while (!validInput) {
                    System.out.print("Enter your choice: ");
                    String input = scanner.nextLine(); // Use nextLine() to capture all input

                    try {
                        choice = Integer.parseInt(input); // Parse the input as an integer
                        validInput = true; // If parsing is successful, exit the loop
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    }
                }
                

                try {
                    switch (choice) {
                        case 1: // Deposit
                            System.out.print("Enter deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline

                            if (account instanceof CheckingAccount) {
                                ((CheckingAccount) account).setTransactionDetails(LocalDate.now().toString(), "DEP", depositAmount);
                            } else if (account instanceof SavingsAccount) {
                                ((SavingsAccount) account).setTransactionDetails(LocalDate.now().toString(), "DEP", depositAmount);
                            }

                            account.deposit(depositAmount);
                            System.out.println("Deposit successful. New balance: $" + account.getBalance());
                            break;

                        case 2: // Withdraw
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawalAmount = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline

                            if (account instanceof CheckingAccount) {
                                ((CheckingAccount) account).setTransactionDetails(LocalDate.now().toString(), "WTH", withdrawalAmount);
                            } else if (account instanceof SavingsAccount) {
                                ((SavingsAccount) account).setTransactionDetails(LocalDate.now().toString(), "WTH", withdrawalAmount);
                            }

                            account.withdrawal(withdrawalAmount);
                            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                            break;

                        case 3: // Apply Interest
                            account.applyInterest();
                            System.out.println("Interest applied. New balance: $" + account.getBalance());
                            break;

                        case 4: // View Balance
                            System.out.println("Current balance: $" + account.getBalance());
                            break;

                        case 5: // Exit
                            System.out.println("Exiting account menu for " + account.getAccountNumber());
                            account.printTransactionHistory(customer.getID()); // Print transaction history before exiting
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (ParseException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                if (choice == 5) {
                    break; // Exit the loop and move on to the next customer/account
                }
            }
        }

        scanner.close();
    }

    // Method to print transaction details (if needed)
    public static void printTransactionDetails(Customer customer, Account account, String transactionDate, String transactionType, double transactionAmount) {
        System.out.println("\nTransaction Details");
        System.out.println("-------------------");
        System.out.printf("Customer ID: %s%n", customer.getID());
        System.out.printf("Account Number: %s%n", account.getAccountNumber());
        System.out.printf("Account Type: %s%n", account.getAccountType());
        System.out.printf("Transaction Date: %s%n", transactionDate);
        System.out.printf("Transaction Type: %s%n", transactionType);
        System.out.printf("Transaction Amount: $%.2f%n", transactionAmount);

        if (account instanceof CheckingAccount) {
            System.out.printf("Service Fee: $%.2f%n", CheckingAccount.SERVICE_FEE);
            if (transactionType.equals("WTH") && account.getBalance() < 0) {
                System.out.printf("Overdraft Fee: $%.2f%n", CheckingAccount.OVERDRAFT_FEE);
            }
        } else if (account instanceof SavingsAccount) {
            System.out.printf("Service Fee: $%.2f%n", SavingsAccount.SERVICE_FEE);
        }

        System.out.printf("Balance: $%.2f%n", account.getBalance());
        System.out.println("-------------------\n");
    }
}
