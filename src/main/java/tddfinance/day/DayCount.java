package tddfinance.day;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class DayCount {
	public  static final DayCountConvention DC_30360US              = new DayCount30360US();
	public  static final DayCountConvention DC_30E360ICMA           = new DayCount30E360ICMA();
	public  static final DayCountConvention DC_30E360ISDA           = new DayCount30E360ISDA();
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
		//FIXME: this doesn't sound like correct for DayCountActualActualICMA...? But removing this breaks some unite tests.
		if( accrualEndDate.isAfter(nextPaymentDate) && convention instanceof DayCountActualActualICMA ){
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
	 * Day count fraction with specified payment frequency and nextPaymentDate specified
	 * <p>
	 * Most likely you'll only need to use this for convention = DayCountActualActualICMA (and DayCountActual365L?), 
	 * as other DayCountConvention implementation classes ignore nextPaymentDate and compoundingRule
	 * </p>
	 * @param convention: day count convention class instance
	 * @param accrualStartDate : start date of the accrual period (exclusive)
	 * @param accrualEndDate : end date of the accrual period (inclusive)
	 * @param nextPaymentSettleDate : next payment's settlement date = start date of the next accrual period
	 * @param compoundingRule : compounding rule to tell you payment frequency
	 * @return the day count fraction in double
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate, Compounding compoundingRule){
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentSettleDate, compoundingRule);
	}

	/**
	 * Day count fraction with specified payment frequency. nextPaymentSettleDate will be figured out from compoundingFrequency.
	 * <p>
	 * Most likely you'll only need to use this for convention = DayCountActualActualICMA (and DayCountActual365L?), 
	 * as other DayCountConvention implementation classes ignore compoundingRule
	 * </p>
	 * @param convention: day count convention class instance
	 * @param accrualStartDate : start date of the accrual period (exclusive)
	 * @param accrualEndDate : end date of the accrual period (inclusive)
	 * @param compoundingRule : compounding rule to tell you payment frequency
	 * @return the day count fraction in double
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, Compounding compoundingRule){
		LocalDate nextPaymentSettleDate  = accrualStartDate.plus( compoundingRule.period() );
		
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentSettleDate, compoundingRule);
	}

	/**
	 * Day count fraction with annual payment frequency, with nextPaymentSettleDate specified
	 * <p>
	 * Most likely you'll only need to use this for convention = DayCountActualActualICMA (and DayCountActual365L?), 
	 * as other DayCountConvention implementation classes ignore nextPaymentSettleDate
	 * </p>
	 * @param convention: day count convention class instance
	 * @param accrualStartDate : start date of the accrual period (exclusive)
	 * @param accrualEndDate : end date of the accrual period (inclusive)
	 * @param compoundingRule : compounding rule to tell you payment frequency
	 * @return the day count fraction in double
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate){
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentSettleDate, Compounding.ANNUAL);
	}

	/**
	 * Day count fraction with annual payment frequency, with nextPaymentSettleDate calculated internally
	 * <p>
	 * Most of the cases you can use this as it's straightforward for most of the Day Count Convention classes,
	 * unless you are using DayCountActualActualISMA or DAyCountActual365L which require additional paramenters. 
	 * </p>
	 * @param convention: day count convention class instance
	 * @param accrualStartDate : start date of the accrual period (exclusive)
	 * @param accrualEndDate : end date of the accrual period (inclusive)
	 * @return the day count fraction in double
	 */
	static public double fraction(DayCountConvention convention, LocalDate accrualStartDate, LocalDate accrualEndDate){
		LocalDate nextPaymentSettleDate = accrualStartDate.plus(Years.ONE);
		return DayCount.calculateFraction(convention, accrualStartDate, accrualEndDate, nextPaymentSettleDate, Compounding.ANNUAL);
	}

}
