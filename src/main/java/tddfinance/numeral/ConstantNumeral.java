package tddfinance.numeral;

/**
 * A numeral class always returning the same constant which is passed to the constructor  
 */
public class ConstantNumeral implements Numeral {
	private final double value;
	
	/**
	 * @param value : value to be returned by getValue()
	 */
	public ConstantNumeral( double value ) {
		this.value = value;
	}
	
	/** 
	 *  Returning a const value which was specified in the constructoe
	 */
	public double getValue(){
		return this.value;
	}
	
}
