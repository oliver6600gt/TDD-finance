package tddfinance.day;

import org.joda.time.LocalDate;

public class Days30 {
	static public int numberOfDays(LocalDate start, LocalDate end){
		
		int startDayOfMonth = start.getDayOfMonth();
		startDayOfMonth = ( startDayOfMonth == 31 ) ? 30 : startDayOfMonth;
				
		int endDayOfMonth = end.getDayOfMonth();
		endDayOfMonth = ( endDayOfMonth == 31 ) ? 30 : endDayOfMonth;

		return 360*(end.getYear() - start.getYear()) + 30*(end.getMonthOfYear() - start.getMonthOfYear()) + endDayOfMonth - startDayOfMonth;
	}
}
