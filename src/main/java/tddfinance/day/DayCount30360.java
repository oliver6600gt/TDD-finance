package tddfinance.day;

import org.joda.time.LocalDate;

public class DayCount30360 implements DayCountConventionInterface {

	public double fraction(LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate, int paymentFrequency) {
		if( accrualStartDate.equals(accrualEndDate) )
			return 0;
		else
			return Days30.numberOfDays(accrualStartDate, accrualEndDate) / 360.0;
	}

}
