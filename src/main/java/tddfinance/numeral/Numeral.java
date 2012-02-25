package tddfinance.numeral;

/**
 *  Interface with a lazy-evaluation method, getValue().
 *  For example, lazy evaluation can be used to determine coupon amount which is not fixed at the beginning.
 */
public interface Numeral {

	/**
	 *  Lazy evaluation method to get a double value 
	 */
	public double getValue();
}