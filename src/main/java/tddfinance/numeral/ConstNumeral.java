package tddfinance.numeral;

/**
 * A numeral class always returning the same constant which is passed to the constructor  
 */
public class ConstNumeral implements Numeral {
	
	private static final double equalityThreshold = 1.0e-16; 

	private final double value;
	
	/**
	 * @param value : value to be returned by getValue()
	 */
	public ConstNumeral( double value ) {
		this.value = value;
	}
	
	/** 
	 *  Returning a const value which was specified in the constructoe
	 */
	public double getValue(){
		return this.value;
	}
	
	public String toString(){
		return String.format( "const( %.16f )", value );
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if( obj instanceof ConstNumeral ){
			ConstNumeral theOther = (ConstNumeral) obj;
			
			double absoluteDiff = Math.abs( ( theOther.getValue() - this.getValue() ) / this.getValue() );
			return absoluteDiff < ConstNumeral.equalityThreshold;
		}
		else
			return false;
	}
}
