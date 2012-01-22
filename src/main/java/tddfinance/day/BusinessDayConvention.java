package tddfinance.day;

import org.joda.time.LocalDate;

public interface BusinessDayConvention {
	LocalDate adjust(LocalDate date, HolidayCalendar calendar);
}
