package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DayCount30E360ISDA implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "30E/360 ISDA", "German" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCount30E360ISDA.knownAs );
	}

	/**
	 *   Factor = {360 x (Y2 - Y1) + 30 x (M2 - M1) + (D2 - D1) } / 360
	 *   <p> 
	 *   nextPaymentSettleDate and paymentFrequency are ignored
	 *   </p>
	 *   <p> 
	 *   Also, it does adjustment to accrualStart/EndDate as defined in the static numberOfDays method
	 *   </p>
	 */
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate, //nextPaymentSettleDate: this is ignored for this class ( convention )
		int       paymentFrequency) {    //paymentFrequency:      this is also ignored for this class ( convention )
				
		return DayCount30E360ISDA.numberOfDays(accrualStartDate, accrualEndDate) / 360.0;

	}
		
	/**
	 * <p>
	 * FIXME: Take the termination date into account SOMEHOW!!
	 * <p>
	 * Returns the number of days between start and end based on the "30/360 US" day count basis.
	 * 
	 * If start == 31st of a month, then it's adjusted to 30th. 
	 * If end == 31st, then it is adjusted to 30th.  
	 * 
	 * @param start : start of the period
	 * @param end : end of the period
	 * @return number of days in int
	 */
	public static int numberOfDays(LocalDate start, LocalDate end) {

		int       startDayOfMonth   = start.getDayOfMonth();
		LocalDate lastDayOfFebruary = new LocalDate( start.getYear(), 3, 1 ).minus(Days.ONE); // last day of February in the same year as start
		startDayOfMonth = ( startDayOfMonth == 31 || start.equals(lastDayOfFebruary) ) ? 30 : startDayOfMonth;

		int endDayOfMonth = end.getDayOfMonth();
		lastDayOfFebruary = new LocalDate( end.getYear(), 3, 1 ).minus(Days.ONE); // last day of February in the same year as star
		endDayOfMonth = ( endDayOfMonth == 31 || end.equals(lastDayOfFebruary) ) ? 30 : endDayOfMonth;

		return 360*(end.getYear() - start.getYear()) + 30*(end.getMonthOfYear() - start.getMonthOfYear()) + endDayOfMonth - startDayOfMonth;
	}
}
