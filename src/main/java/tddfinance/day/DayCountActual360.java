package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DayCountActual360 implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "Actual/360", "Act/360", "A/360", "French" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCountActual360.knownAs );
	}
	
	/**
	 *   Factor = DaysBetween(accrualStartDate, accrualEndDate)/360
	 *   <p> 
	 *   nextPaymentSettleDate and paymentFrequency are ignored
	 *   </p>
	 */
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
				
		return DayCountActual360.numberOfDays(accrualStartDate, accrualEndDate) / 360.0;
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
