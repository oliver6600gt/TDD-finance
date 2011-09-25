package tddfinance.day;

import org.joda.time.LocalDate;

/**
 * Implementation of Business Day Convention which adjust a date to the previous business day if the date is not a business day
 */
public class BusinessDayConventionPrevious implements BusinessDayConventionInterface {
	public LocalDate adjust(LocalDate date, HolidayCalendar calendar) {
		if(calendar.isBusinessDay(date))
			return date;
		else
			return calendar.previousBusinessDay(date);
	}
}
