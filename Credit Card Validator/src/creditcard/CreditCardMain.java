// Jawad Mahmoud
// 2253483

package creditcard;

import java.util.*;
import creditcard.CreditCardChecker;

public class CreditCardMain {
	/**
	 * main method to run as java application
	 * the scanner takes in the credit card number as string from the user
	 */
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		System.out.println("Enter the credit card number:");
		String creditCard = stdin.next();
		stdin.close();
		/**
		 * new CreditCardChecker object created
		 */
		new CreditCardChecker();
		/**
		 * tries to run the validate method on the newly created object
		 * if validate returns true, number is valid
		 * if validate is false, number is invalid
		 */
		try {
			if (CreditCardChecker.validate(creditCard) == true) {
				System.out.println("Card number is valid");
			} else {
				System.out.println("Card number is invalid");
			}
		/**
		 * if exceptions are thrown, the program prints out the related
		 * message using the getMessage() method from the Exception superclass
		 * prints the message relating to the subclass
		 * and then exits the system
		 */
		} catch (IllegalCardLengthException icle) {
			System.out.println(icle.getMessage());
			System.exit(0);
		} catch (IllegalCardFormatException icfe) {
			System.out.println(icfe.getMessage());
			System.exit(0);
		}
	}
}
