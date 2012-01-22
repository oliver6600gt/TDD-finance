package tddfinance.day;

import org.joda.time.LocalDate;

/**
 * Implementation of Business Day Convention which does not adjust a date at all, even if the date is a holiday for a given Holiday Calendar
 */
public class BusinessDayConventionUnadjusted implements BusinessDayConvention {
	public LocalDate adjust(LocalDate date, HolidayCalendar calendar) {
		return date;
	}
}
