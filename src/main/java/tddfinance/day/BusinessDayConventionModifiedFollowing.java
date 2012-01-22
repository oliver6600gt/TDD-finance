package tddfinance.day;

import org.joda.time.LocalDate;

/**
 * Implementation of Business Day Convention which adjusts a date to the following business day if the date is not a business day,
 * but adjusts to the previous business day if the following business day falls into the next month
 */
public class BusinessDayConventionModifiedFollowing implements BusinessDayConvention {

	//to re-use the logic
	private static final BusinessDayConvention FOLLOWING = new BusinessDayConventionFollowing();
	private static final BusinessDayConvention PREVIOUS  = new BusinessDayConventionPrevious();
	
	public LocalDate adjust(LocalDate date, HolidayCalendar calendar) {
		//of course there could be no actual adjustment 
		LocalDate followingBusinessDay = FOLLOWING.adjust(date, calendar);

		if( date.getMonthOfYear() != followingBusinessDay.getMonthOfYear() )
			return PREVIOUS.adjust(date, calendar);
		else 
			return followingBusinessDay;
	}
}
