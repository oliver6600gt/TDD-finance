package tddfinance.day;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;

public class DayCount {
	public  static final DayCountConventionInterface DC_30360        = new DayCount30360();
	public  static final DayCountConventionInterface DC_ACTUALACTUAL = new DayCountActualActual();

	/**
	 * prohibit instantiation of this class, as it's a utility class 
	 */
	private DayCount(){};

	/**
	 * Day count fraction with arbitrary payment frequency
	 */
	static public double fraction(DayCountConventionInterface convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate, int paymentFrequency){
		return convention.fraction(accrualStartDate, accrualEndDate, nextPaymentSettleDate, paymentFrequency);
	}

	/**
	 * Day count fraction with arbitrary payment frequency, where paymentFrequency should be a divisor of 12
	 */
	static public double fraction(DayCountConventionInterface convention, LocalDate accrualStartDate, LocalDate accrualEndDate, int paymentFrequency){
		if( paymentFrequency < 1 || paymentFrequency > 12 || 12 % paymentFrequency != 0)
			throw new IllegalArgumentException( "paymentFrequency = " + paymentFrequency + " is illegal. It should be a divisor of 12" );
			
		LocalDate nextPaymentSettleDate = accrualStartDate.plus(Months.months(12/paymentFrequency));
		return convention.fraction(accrualStartDate, accrualEndDate, nextPaymentSettleDate, paymentFrequency);
	}

	/**
	 * Day count fraction with annual payment frequency
	 */
	static public double fraction(DayCountConventionInterface convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate){
		return convention.fraction(accrualStartDate, accrualEndDate, nextPaymentSettleDate, 1);
	}


	/**
	 * Day count fraction with annual payment frequency
	 */
	static public double fraction(DayCountConventionInterface convention, LocalDate accrualStartDate, LocalDate accrualEndDate){
		LocalDate nextPaymentSettleDate = accrualStartDate.plus(Years.ONE);
		return convention.fraction(accrualStartDate, accrualEndDate, nextPaymentSettleDate, 1);
	}


}
