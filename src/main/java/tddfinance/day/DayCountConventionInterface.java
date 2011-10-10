package tddfinance.day;

import org.joda.time.LocalDate;

public interface DayCountConventionInterface {
	
	/*
	 * Day Count fraction 
	 */
	public double fraction(LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate, int paymentFrequency);
}
