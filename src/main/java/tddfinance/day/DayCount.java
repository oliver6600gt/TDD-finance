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
	 * Day count fraction with arbitrary payment frequency. <br>
	 * The base method of this class, which is called by other methods of the same class, so that you'll have common parameter checkers only in this method
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate, int paymentFrequency){
		if( accrualStartDate.equals(accrualEndDate) )
			return 0;
		else
			return convention.fraction(accrualStartDate, accrualEndDate, nextPaymentSettleDate, paymentFrequency);
	}

	/**
	 * Day count fraction with arbitrary payment frequency, where paymentFrequency should be a divisor of 12
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, int paymentFrequency){
		if( paymentFrequency < 1 || paymentFrequency > 12 || 12 % paymentFrequency != 0)
			throw new IllegalArgumentException( "paymentFrequency = " + paymentFrequency + " is illegal. It should be a divisor of 12" );
			
		LocalDate nextPaymentSettleDate = accrualStartDate.plus(Months.months(12/paymentFrequency));
		return DayCount.fraction(convention, accrualStartDate, accrualEndDate, nextPaymentSettleDate, paymentFrequency);
	}

	/**
	 * Day count fraction with annual payment frequency
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate){
		return DayCount.fraction(convention, accrualStartDate, accrualEndDate, nextPaymentSettleDate, 1);
	}


	/**
	 * Day count fraction with annual payment frequency
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate){
		LocalDate nextPaymentSettleDate = accrualStartDate.plus(Years.ONE);
		return DayCount.fraction(convention, accrualStartDate, accrualEndDate, nextPaymentSettleDate, 1);
	}

	/**
	 * Return the ReadablePeriod value from the compounding frequency. For the simplicity, anything other than below would be invalid.
	 * <p>
	 * Compounding Frequcency = 1   -> Years.ONE //annual compounding <br>
	 * Compounding Frequcency = 2   -> Months.months(6) //semi-annual <br>
	 * Compounding Frequcency = 3   -> Months.months(4) <br>
	 * Compounding Frequcency = 4   -> Months.months(3) //quaterly<br>
	 * Compounding Frequcency = 6   -> Months.months(2) <br>
	 * Compounding Frequcency = 12  -> Months.months(1) //monthly<br>
	 * Compounding Frequcency = 52  -> Weeks.weeks(1) //weekly<br>  
	 * Compounding Frequcency = 365 -> Days.days(1) //daily<br>:  
	 * <p>
	 */
	public static ReadablePeriod getPeriodFromCompoundingFrequency(int compoundingFrequency) {
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
