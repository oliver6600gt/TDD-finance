package tddfinance.day;

import org.joda.time.LocalDate;

public class DayCountActual365Fixed implements DayCountConvention {

	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
				
		throw new UnsupportedOperationException( "method not implemented" );

	}
}
