// Jawad Mahmoud
// 2253483

/**
 * class added to the new package created
 * called creditcard
 */
package creditcard;

/**
 * importing custom exceptions
 */
import creditcard.IllegalCardLengthException;
import creditcard.IllegalCardFormatException;

public class CreditCardChecker {
	/**
	 * initiate validate method which takes the creditCard string and checks if
	 * it is valid. it also throws the new exceptions in different cases
	 */
	public static boolean validate(String creditCard) throws IllegalCardLengthException, IllegalCardFormatException {
		/**
		 * the try code block includes the code that may or may not cause errors
		 * when the program runs
		 */
		try {
			/**
			 * for loop goes over each character in the string creditCard
			 */
			for (int index = 0; index < creditCard.length(); index++) {
				char character = creditCard.charAt(index);
				/**
				 * if the character at the index position is not a digit
				 * (number) it throws the IllegalCardFormatException exception
				 * with the message that follows
				 */
				if (Character.isDigit(character) == false) {
					throw new IllegalCardFormatException("Illegal character: " + character);
					/**
					 * if the length of the string creditCard is not between 13
					 * and 19 inclusive it throws the IllegalCardLengthException
					 * exception with the message that follows
					 */
				} else if (creditCard.length() < 13 || creditCard.length() > 19) {
					throw new IllegalCardLengthException("Invalid card length: " + creditCard.length());
				}
			}
			/**
			 * finally closes the try block
			 */
		} finally {

		}
		/**
		 * Performs all the checks on the validity of the creditCard number as
		 * in the previous lab first checks whether the prefix can be identified
		 * then checks if the length matches the credit card type
		 */
		String cardType = null;
		boolean validLength = true;
		if (creditCard.startsWith("34") || creditCard.startsWith("37")) {
			// American Express
			validLength = (creditCard.length() == 15);
			cardType = "American Express";
		} else if (creditCard.startsWith("4")) {
			// Visa
			validLength = (creditCard.length() == 13 || creditCard.length() == 16 || creditCard.length() == 19);
			cardType = "Visa";
		} else if (creditCard.startsWith("5")) {
			// MasterCard
			int prefix = Integer.valueOf(creditCard.substring(0, 2));
			if (prefix >= 51 && prefix <= 55) {
				validLength = (creditCard.length() == 16);
				cardType = "MasterCard";
			}
		}
		/**
		 * if cardType is not identifited, the validate method returns false
		 */
		if (cardType == null) {
			return false;
		}
		/**
		 * If validLength is false, validate method returns false
		 */
		if (!validLength) {
			return false;
		}
		/**
		 * final checks for the credit card validity
		 */
		int sum = 0;
		/**
		 * Go through the characters one by one
		 */
		for (int index = 1; index <= creditCard.length(); index++) {
			int value = Character.getNumericValue(creditCard.charAt(creditCard.length() - index));

			/**
			 * special treatment for characters in even positions
			 */
			if (index % 2 == 0) {
				if (value <= 4) {
					sum += (value * 2);
				} else {
					sum += (value * 2 - 9);
				}
			} else {
				/**
				 * odd position characters get added directly
				 */
				sum += value;
			}
		}

		/**
		 * validate only returns true if the sum is a multiple of 10 otherwise
		 * it will return false
		 */
		if (sum % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}
}
