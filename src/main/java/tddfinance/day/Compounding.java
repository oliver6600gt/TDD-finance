package tddfinance.day;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;
import org.joda.time.Weeks;
import org.joda.time.Years;

/**
 * An enumeration class for clearer compounding representation, 
 * instead of using 1) int as compounding frequency, and 2) ReadablePeriod as unit compounding period length, 
 * 
 * <p>
 * Benefits:<br>
 * A) No need to create the same method with to diff argument sets - with int (frequency) and ReadablePeriod (unit period) <br>
 * B) You can only use the set of valid values<br>
 * <br>
 */
public enum Compounding {
	ANNUAL             (1,   Years.ONE,    "Annual Compounding"),
	SEMI_ANNUAL        (2,   Months.SIX,   "Semi-Annual Compounding"),
	EVERY_FOUR_MONTH   (3,   Months.FOUR,  "Every-Four-Month Compounding"),
	QUARTERLY          (4,   Months.THREE, "Quarterly Compounding"),
	BI_MONTHLY         (6,   Months.TWO,   "Bi-Monthly Compounding"),
	MONTHLY            (12,  Months.ONE,   "Monthly Compounding"),
	WEEKLY             (52,  Weeks.ONE,    "Weekly Compounding"),
	DAILY              (365, Days.ONE,     "Daily Compounding");
	
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
		return compoundingFrequency;
	}
	
	public ReadablePeriod period(){
		return unitPeriodLength;
	}


}