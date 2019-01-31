// Jawad Mahmoud
// 2253483

package creditcard;
/**
 * new subclass for Exception
 */
public class IllegalCardLengthException extends Exception {
	/**
	 * constructor for the subclass taking fields from the superclass
	 */
	public IllegalCardLengthException(String message) {
		super(message);
	}

}
