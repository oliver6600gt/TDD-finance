package tddfinance.day;

import org.joda.time.LocalDate;

public interface BusinessDayConventionInterface {
	LocalDate adjust(LocalDate date, HolidayCalendar calendar);
}
