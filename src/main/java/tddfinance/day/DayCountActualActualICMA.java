package tddfinance.day;

import org.joda.time.LocalDate;

public class DayCountActualActualICMA implements DayCountConvention {

	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
					
		throw new UnsupportedOperationException( "method not implemented" );

	}

}
