package tddfinance.day;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DayCountActualActual implements DayCountConventionInterface {

	public double fraction(LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate, int paymentFrequency) {
		return ( (double) Days.daysBetween(accrualStartDate, accrualEndDate).getDays() )
			/ ( paymentFrequency * Days.daysBetween(accrualStartDate, nextPaymentSettleDate).getDays() );
	}

}
