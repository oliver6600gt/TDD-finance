package tddfinance.day;

import org.joda.time.LocalDate;

public class DayCount30360US implements DayCountConvention {

	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
				
		throw new UnsupportedOperationException( "method not implemented" );

	}

}
