package tddfinance.day;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 * The class is immutable in a sense that once it's constructed, 
 * you will always get the consistent result from isHoliday( LocalDate ) in the same application session.<br>
 * 
 * However, if underlying holiday rule is updated, (e.g.) when you add a new holiday for JPY calendar,
 * then in the next application session, you will get the new rule reflected by isHoliday()
 */
public class HolidayCalendar {

	/**
	 * Default holiday calendar without any holiday.
	 * (i.e.) Non-business day if and only if it's Saturday or Sunday. All weekdays are all business days.
	 */
	public HolidayCalendar() {
	};
//
//	/**
//	 * 
//	 */
//	public HolidayCalendar( String calendarName ) throws Exception {
//		//invalid calendar name -> throw exception.
//	};
//	
//	//getHolidays will return all holidays of the calendar between lowerBound and UpperBound
//	//Making it private so that user's don't need to care about the bounds
//	//(i.e.) whenever a user wants to see a holiday outside the bound, then the lower or upper bound
//	//will be automatically adjusted, and the return of getHolidays is auto-refreshed
//	private LocalDate lowerBound;
//	private LocalDate upperBound;
//
//	private Set<LocalDate> cachedHolidays;
//	private Set<LocalDate> getHolidays();
//	
	public boolean isHoliday(LocalDate date){
		return false;
	}
	
	public boolean isBusinessDay(LocalDate date){
		int dayOfWeek = date.getDayOfWeek();
		return dayOfWeek != DateTimeConstants.SATURDAY && dayOfWeek != DateTimeConstants.SUNDAY;  
	}

	public boolean isWeekendBusinessDay(LocalDate date){
		return false;
	}
	
	public LocalDate nextBusinessDay(LocalDate date){
		LocalDate nextDay = date.plusDays(1);
		if( isBusinessDay(nextDay) )
			return nextDay;
		else
			return nextBusinessDay(nextDay);
	}
	
	public LocalDate previousBusinessDay(LocalDate date){
		LocalDate previousDay = date.minusDays(1);
		if( isBusinessDay( previousDay ) )
			return previousDay;
		else
			return previousBusinessDay(previousDay);
	}
}
