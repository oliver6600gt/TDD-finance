package tddfinance.day;

import org.joda.time.LocalDate;

/**
 * Utility class to deal with business day conventions.
 * Typical conventions are Following, Modified Following, Previous and Unadjusted.
 * <p>
 * For ease of code-writing, this class name, "BDC" was chosen because you would write this so many times.
 * Maybe it's not the best practice though - using acronym for a class name is not always recommended.
 */

public final class BDC {
	public  static final BusinessDayConventionInterface FOLLOWING          = new BusinessDayConventionFollowing();
	public  static final BusinessDayConventionInterface MODIFIED_FOLLOWING = new BusinessDayConventionModifiedFollowing();
	public  static final BusinessDayConventionInterface UNADJUSTED         = new BusinessDayConventionUnadjusted();
	public  static final BusinessDayConventionInterface PREVIOUS           = new BusinessDayConventionPrevious();
	private static final HolidayCalendar defaultCalender                   = new HolidayCalendar();            

	/**
	 * prohibit instantiation of this class, as it's a utility class 
	 */
	private BDC(){};
	
	/**
 	 * Return the adjusted date depending on the convention (= Business Day Convention) using the default HolidayCalendar
	 */
	static LocalDate adjust(LocalDate date, BusinessDayConventionInterface convention){
		return convention.adjust(date, defaultCalender);
	}

	/**
	 * Adjust the date depending on the convention (= Business Day Convention) and the calendar(Holiday Caleanar)
	 */
	static LocalDate adjust(LocalDate date, BusinessDayConventionInterface convention, HolidayCalendar calendar){
		return convention.adjust(date, calendar);
	}
}
