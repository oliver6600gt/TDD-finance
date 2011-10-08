package tddfinance.day;

import org.joda.time.LocalDate;

public class Days30 {
	/**
	 * 
	 * @param start Start date of period to calculate the number of days 
	 * @param end End date of period to calculate the number of days
	 * @return The number of days between start and end.
	 * The formula is as follows, 360 x (Y2 - Y1) + 30 x (M2 - M1) + D2 - D1, 
	 * where start = Y1/M1/D1 and end = Y2/M2/D2. Also, if D1 = 31, it will be 30 (so for D2). 
	 */
	static public int numberOfDays(LocalDate start, LocalDate end){
		
		int startDayOfMonth = start.getDayOfMonth();
		startDayOfMonth = ( startDayOfMonth == 31 ) ? 30 : startDayOfMonth;
				
		int endDayOfMonth = end.getDayOfMonth();
		endDayOfMonth = ( endDayOfMonth == 31 ) ? 30 : endDayOfMonth;

		return 360*(end.getYear() - start.getYear()) + 30*(end.getMonthOfYear() - start.getMonthOfYear()) + endDayOfMonth - startDayOfMonth;
	}
}
