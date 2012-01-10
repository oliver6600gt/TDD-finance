package tddfinance.day;

import java.util.List;
import org.joda.time.LocalDate;

/**
 * 
 * The interface to define the Day Count Convention 
 *
 */
public interface DayCountConvention {

	/**
	 * @return The list of aliases for the given day count convention. Two different classes can share the same alias.
	 */
	public List<String> knownAs();
	
	/**
	 * Returns the day count fraction number in double. 
	 * accrualStartDate and accrualEndDate are the mandatory parameters for all the implementation classes, 
	 * but nextPaymentSettleDate and compoundingFrequency are ignored in some implementation classes.  
	 * 
	 * @param accrualStartDate : start date of the accrual period (exclusive)
	 * @param accrualEndDate : end date of the accrual period (inclusive)
	 * @param nextPaymentSettleDate : next payment's settlement date = start date of the next accrual period
	 * @param paymentFrequency : payment frequency
	 * @return the day count fraction in double
	 */
	public double fraction(
		LocalDate accrualStartDate, 
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate, 
		int paymentFrequency);
	
	
}
