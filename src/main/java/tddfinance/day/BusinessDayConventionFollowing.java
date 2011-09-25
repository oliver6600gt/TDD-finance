package tddfinance.day;

import org.joda.time.LocalDate;

/**
 * Implementation of Business Day Convention which adjusts a date to the following business day if the date is not a business day
 */
public class BusinessDayConventionFollowing implements BusinessDayConventionInterface {
	
	public LocalDate adjust(LocalDate date, HolidayCalendar calendar) {
		if(calendar.isBusinessDay(date))
			return date;
		else
			return calendar.nextBusinessDay(date);
	}
}
