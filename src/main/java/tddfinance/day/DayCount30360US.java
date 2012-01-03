package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

public class DayCount30360US implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "30/360 US", "30U/360", "Bond basis", "360/360" );
	
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
				
		throw new UnsupportedOperationException( "method not implemented" );

	}

	public List<String> knownAs() {
		return new ArrayList<String>( DayCount30360US.knownAs );
	}

	public static int numberOfDays(LocalDate start, LocalDate end) {
		int startDayOfMonth = start.getDayOfMonth();
		startDayOfMonth = ( startDayOfMonth == 31 ) ? 30 : startDayOfMonth;
				
		//if start.getDayOfMonth() == 30 or 31, then startDayOfMonth is set to 30 as above
		int endDayOfMonth = end.getDayOfMonth();
		endDayOfMonth = ( endDayOfMonth == 31 && startDayOfMonth == 30) ? 30 : endDayOfMonth;

		return 360*(end.getYear() - start.getYear()) + 30*(end.getMonthOfYear() - start.getMonthOfYear()) + endDayOfMonth - startDayOfMonth;
	}

}
