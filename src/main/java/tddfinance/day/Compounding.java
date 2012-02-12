package tddfinance.day;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;
import org.joda.time.Weeks;
import org.joda.time.Years;

/**
 * An enumeration class for compounding representation.
 * For almost any method implementation, you are encouraged to use this, instead of relying on 1) an int value as a compounding frequency or 2) ReadablePeriod as unit compounding period length. 
 * 
 */
//----------------------------------------------------------------------------------------------------------------------//
// Most methods in this class use "if( this.equals( Compounding.CONTINUOUS ) )" branch, which is not pretty.
// However, given that only the continuous compounding needs a special handling, it would be okay.
// A cleaner approach is to introduce complete "Strategy" design pattern somehow, but it wouldn't worth it for now
//----------------------------------------------------------------------------------------------------------------------//
public enum Compounding {
	ANNUAL             (1,    Years.ONE,    "Annual Compounding"),
	SEMI_ANNUAL        (2,    Months.SIX,   "Semi-Annual Compounding"),
	EVERY_FOUR_MONTH   (3,    Months.FOUR,  "Every-Four-Month Compounding"),
	QUARTERLY          (4,    Months.THREE, "Quarterly Compounding"),
	BI_MONTHLY         (6,    Months.TWO,   "Bi-Monthly Compounding"),
	MONTHLY            (12,   Months.ONE,   "Monthly Compounding"),
	WEEKLY             (52,   Weeks.ONE,    "Weekly Compounding"),
	DAILY              (365,  Days.ONE,     "Daily Compounding"),
	CONTINUOUS         (0,    null,         "Continuous Compounding"); //0 and null are dummy values, as frequency() and period() should thrown exceptions 
	
	private final String         description;
	private final int            compoundingFrequency;
	private final ReadablePeriod unitPeriodLength;
	
	private Compounding( int compoundingFrequency, ReadablePeriod unitPeriodLength, String description ){
		this.compoundingFrequency = compoundingFrequency;
		this.unitPeriodLength    = unitPeriodLength;
		this.description         = description;
	}

	public String toString(){
		return description;
	}

	public int frequency(){
		if( this.equals( Compounding.CONTINUOUS ) ) //theoretically, it is infinite frequency 
			throw new UnsupportedOperationException( "Compounding.CONTINUOUS does not support the frequency() method" );
		else
			return compoundingFrequency;
	}
	
	public ReadablePeriod period(){
		if( this.equals( Compounding.CONTINUOUS ) ) //theoretically, its period length is 0
			throw new UnsupportedOperationException( "Compounding.CONTINUOUS does not support the period() method" );
		else
			return unitPeriodLength;
	}

	/**
	 * @return number of annual payments ( more precisely, the inverse of frequency() ) 
	 */
	public double fraction() {
		if( this.equals( Compounding.CONTINUOUS ) ) //theoretically, it is infinite fraction
			throw new UnsupportedOperationException( "Compounding.CONTINUOUS does not support the fraction() method" );
		else
			return 1.0 / frequency();
	}

	/**
	 * Calculate the return in the period, given the interest rate to apply
	 * 
	 * @param  rate : Rate to be applied to calculate the compounding   
	 * @param  annualizedPeriod : Annualized length of a period (e.g. 1 year = 1.0, half a year = 0.5). Typically you can calculate annualized length of the period using DayCount.fraction(), and pass that to this method.
	 *   
	 */
	public double returnInPeriod(double rate, double annualizedPeriod) {
		if( this.equals( Compounding.CONTINUOUS ) ) //theoretically, it is infinite fraction
			return Math.exp( rate * annualizedPeriod );
		else
			return Math.pow( 1.0 + rate/frequency(), annualizedPeriod*frequency() );
	}
}
