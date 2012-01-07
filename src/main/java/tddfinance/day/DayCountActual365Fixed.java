package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DayCountActual365Fixed implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "Actual/365 Fixed", "Act/365 Fixed", "A/365 Fixed", "A/365F", "English" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCountActual365Fixed.knownAs );
	}
	
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
				
		return DayCountActual365Fixed.numberOfDays(accrualStartDate, accrualEndDate) / 365.0;
	}

	/**
	 * Returns the actual number of days between start and end 
	 * 
	 * @param start : start of the period
	 * @param end : end of the period
	 * @return number of days in int
	 */
	public static int numberOfDays(LocalDate start, LocalDate end) {
		return Days.daysBetween(start, end).getDays();
	}
}