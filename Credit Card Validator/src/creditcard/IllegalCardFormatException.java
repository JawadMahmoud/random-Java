// Jawad Mahmoud
// 2253483

package creditcard;
/**
 * new subclass for Exception
 */
public class IllegalCardFormatException extends Exception {
	/**
	 * constructor for the subclass taking fields from the superclass
	 */
	public IllegalCardFormatException(String message) {
		super(message);
	}
}
