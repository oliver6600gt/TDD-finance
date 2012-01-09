package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DayCountActualActualICMA implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "Actual/Actual ICMA", "Actual/Actual", "Act/Act ICMA", "ISMA-99", "Act/Act ISMA" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCountActualActualICMA.knownAs );
	}

	/**
	 * 
	 * @param accrualStartDate: the start date of the accrual perid (exclusive)
	 * @param accrualEndDate: the end date of the accrual period (inclusive), and cannot be after nextPaymentSettleDate
	 * @param nextPaymentSettleDate: the next payment settlement date (i.e. the start of next accrual period). It cannot be before accrualEndDate. 
	 * @param paymentFrequency: payment frequency in int
	 * @return
	 */
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
					
		double numberOfAccrualDays  = Days.daysBetween(accrualStartDate, accrualEndDate).getDays(); //the number of days your ear accrued interest
		double numberOfDaysInPeriod = Days.daysBetween(accrualStartDate, nextPaymentSettleDate).getDays();
		
		return numberOfAccrualDays / ( paymentFrequency * numberOfDaysInPeriod );
	}

}
