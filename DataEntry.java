package courseProject;

import java.util.Scanner;

public class DataEntry {
    
    public static double inputDouble(String prompt, double min, double max) {
    	Scanner scanner = new Scanner(System.in);
    	double value = 0;
    	boolean valid = false;
    	
    	while (!valid) {
    		System.out.print(prompt);
    		try {
    			value = Double.parseDouble(scanner.nextLine());
    			if (value >= min && value <= max) {
    				valid = true;
    			} else {
    				System.out.println("Please enter a value between " + min + " and" + max + ".");
    			}
    		} catch (NumberFormatException e) {
    			System.out.println("Invalid input. Please enter a numeric value.");
    		}
    	}
    	
    	return value;
    }
    
    public static String inputString(String prompt, int maxLength) {
    	Scanner scanner = new Scanner(System.in);
    	String input = "";
    	boolean valid = false;
    	
    	while (!valid) {
    		System.out.print(prompt);
    		input = scanner.nextLine();
    		if (input.length() <= maxLength && !input.trim().isEmpty()) {
    			valid = true;
    		} else {
    			System.out.println("Please enter a valid string with a maximum of " + maxLength + " characters.");
    		}
    	}
    	
    	return input;
    }
    
    public static String inputStringNumeric(String prompt, int exactLength) {
    	Scanner scanner = new Scanner(System.in);
    	String input = "";
    	boolean valid = false;
    	
    	while (!valid) {
    		System.out.print(prompt);
    		input = scanner.nextLine();
    		if (input.length() == exactLength && input.matches("\\d+")) {
    			valid = true;
    		} else {
    			System.out.println("Please enter a numeric string with exactly " + exactLength + " digits.") ;
    		}
    	}
    	
    	return input;
    }
}
