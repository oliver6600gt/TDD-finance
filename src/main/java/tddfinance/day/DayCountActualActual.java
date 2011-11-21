package tddfinance.day;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DayCountActualActual implements DayCountConventionInterface {

	public double fraction(LocalDate accrualStartDate, LocalDate accrualEndDate, LocalDate nextPaymentSettleDate, int paymentFrequency) {
		if( accrualStartDate.equals(accrualEndDate) )
			return 0;
		else{
			double numberOfAccrualDays  = Days.daysBetween(accrualStartDate, accrualEndDate).getDays(); //the number of days your earn accrual
			double numberOfDaysInPeriod = Days.daysBetween(accrualStartDate, nextPaymentSettleDate).getDays();
			
			return numberOfAccrualDays / ( paymentFrequency * numberOfDaysInPeriod );
		}
	}

}
