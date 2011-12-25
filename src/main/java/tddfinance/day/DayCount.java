package tddfinance.day;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class DayCount {
	public  static final DayCountConvention DC_30360                = new DayCount30360();
	public  static final DayCountConvention DC_30360US              = new DayCount30360US();
	public  static final DayCountConvention DC_30E360ICMA           = new DayCount30E360ICMA();
	public  static final DayCountConvention DC_30E360ISDA           = new DayCount30E360ISDA();
	public  static final DayCountConvention DC_ACTUALACTUAL         = new DayCountActualActual();
	public  static final DayCountConvention DC_ACTUAL_ACTUAL_ICMA   = new DayCountActualActualICMA();
	public  static final DayCountConvention DC_ACTUAL_ACTUAL_ISDA   = new DayCountActualActualISDA();
	public  static final DayCountConvention DC_ACTUAL360            = new DayCountActual360();
	public  static final DayCountConvention DC_ACTUAL365FIXED       = new DayCountActual365Fixed();
	public  static final DayCountConvention DC_ACTUAL365L           = new DayCountActual365L();

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
		Compounding        compoundingRule
	){
		if( accrualStartDate.equals(accrualEndDate) ){
			return 0;
		}
		else if( accrualEndDate.isAfter(nextPaymentDate) ){
			//The accrual spans more than one period -> recursively calculate the day count fraction for accrual
			LocalDate accrualStartDateNew = nextPaymentDate;
			LocalDate nextPaymentDateNew  = nextPaymentDate.plus( compoundingRule.period() );
			
			return 1.0 / compoundingRule.frequency() + 
				DayCount.calculateFraction(convention, accrualStartDateNew, accrualEndDate, nextPaymentDateNew, compoundingRule);
		}
		else
			return convention.fraction(accrualStartDate, accrualEndDate, nextPaymentDate, compoundingRule.frequency());			
	}

	
	
	/**
	 * Day count fraction with arbitrary payment frequency, with nextPaymentDate specified
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentDate, Compounding compoundingRule){
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, compoundingRule);
	}

	/**
	 * Day count fraction with arbitrary payment frequency 
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, Compounding compoundingRule){
		LocalDate nextPaymentDate  = accrualStartDate.plus( compoundingRule.period() );
		
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, compoundingRule);
	}

	/**
	 * Day count fraction with annual payment frequency, with nextPaymentDate specified
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentDate){
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, Compounding.ANNUAL);
	}

	/**
	 * Day count fraction with annual payment frequency
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate){
		LocalDate nextPaymentDate = accrualStartDate.plus(Years.ONE);
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentDate, Compounding.ANNUAL);
	}

}
