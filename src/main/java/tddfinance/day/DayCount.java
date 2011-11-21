package tddfinance.day;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;
import org.joda.time.Weeks;
import org.joda.time.Years;

public class DayCount {
	public  static final DayCountConvention DC_30360        = new DayCount30360();
	public  static final DayCountConvention DC_ACTUALACTUAL = new DayCountActualActual();

	/**
	 * prohibit instantiation of this class, as it's a utility class 
	 */
	private DayCount(){};

	/**
	 * Common internal function called from the static "fraction" methods of this class (with different argument definition).
	 * <p>
	 * In case accrualEndDate >= nextPaymentDate, it recursively calculates the fraction. 
	 * This doesn't happen in a usual accrued interest calculation scenario, however, 
	 * when you are calculating the annualized period length while (e.g.) discounting cashflows, pricing a long-dated zero coupon, etc, 
	 * a fraction that is greater than 1.0/compounding frequency will be needed.
	 */
	static private double calculateFraction(
		DayCountConvention convention,
		LocalDate          accrualStartDate,
		LocalDate          accrualEndDate,
		LocalDate          nextPaymentDate,
		ReadablePeriod     unitPeriodLength,
		int                compoundingFrequency	
	){
		if( accrualStartDate.equals(accrualEndDate) ){
			return 0;
		}
		else if( accrualEndDate.isAfter(nextPaymentDate) ){
			//The accrual spans more than one period -> recursively calculate the day count fraction for accrual
			LocalDate accrualStartDateNew = nextPaymentDate;
			LocalDate nextPaymentDateNew  = nextPaymentDate.plus( unitPeriodLength );
			
			return 1.0 / compoundingFrequency + 
				DayCount.calculateFraction(convention, accrualStartDateNew, accrualEndDate, nextPaymentDateNew, unitPeriodLength, compoundingFrequency);
		}
		else
			return convention.fraction(accrualStartDate, accrualEndDate, nextPaymentDate, compoundingFrequency);			
	}

	
	
	/**
	 * Day count fraction with arbitrary payment frequency, with nextPaymentDate specified
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentDate, int compoundingFrequency){
		ReadablePeriod unitPeriodLength = DayCount.periodFromCompoundingFrequency(compoundingFrequency);
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, unitPeriodLength, compoundingFrequency);
	}

	/**
	 * Day count fraction with arbitrary payment frequency 
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, int compoundingFrequency){
		ReadablePeriod unitPeriodLength      = DayCount.periodFromCompoundingFrequency(compoundingFrequency);
		LocalDate      nextPaymentDate = accrualStartDate.plus( unitPeriodLength );

		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, unitPeriodLength, compoundingFrequency);
	}

	/**
	 * Day count fraction with annual payment frequency, with nextPaymentDate specified
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentDate){
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, Years.ONE, 1);
	}

	/**
	 * Day count fraction with annual payment frequency
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate){
		LocalDate nextPaymentDate = accrualStartDate.plus(Years.ONE);
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, Years.ONE, 1);
	}

	/**
	 * Return the ReadablePeriod value from the compounding frequency. For the simplicity, anything other than below would be invalid.
	 * <p>
	 * Compounding Frequency = 1   -> Years.ONE //annual compounding <br>
	 * Compounding Frequency = 2   -> Months.months(6) //semi-annual <br>
	 * Compounding Frequency = 3   -> Months.months(4) <br>
	 * Compounding Frequency = 4   -> Months.months(3) //quaterly<br>
	 * Compounding Frequency = 6   -> Months.months(2) <br>
	 * Compounding Frequency = 12  -> Months.months(1) //monthly<br>
	 * Compounding Frequency = 52  -> Weeks.weeks(1) //weekly<br>  
	 * Compounding Frequency = 365 -> Days.days(1) //daily<br>:  
	 * <p>
	 */
	public static ReadablePeriod periodFromCompoundingFrequency(int compoundingFrequency) {
		if( compoundingFrequency == 1 ) //annual
			return Years.ONE; 
		else if( 2 <= compoundingFrequency  && compoundingFrequency <= 12 && 12 % compoundingFrequency == 0) //semi-annual ~ monthly
			return Months.months( 12 / compoundingFrequency );
		else if( compoundingFrequency == 52 )
			return Weeks.ONE;
		else if( compoundingFrequency == 365 )
			return Days.ONE;
		else
			throw new IllegalArgumentException( "compoundingFrequency = " + compoundingFrequency + " is invalid for getPeriodFromCompoundingFrequency" );
	}


}
