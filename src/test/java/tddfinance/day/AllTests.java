package tddfinance.day;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	BusinessDayConventionTest.class, 
	CompoundingTest.class,
	DayCount30360USTest.class,
	DayCount30E360ICMATest.class, 
	DayCount30E360ISDATest.class,
	DayCountActual360Test.class, 
	DayCountActual365FixedTest.class,
	DayCountActual365LTest.class, 
	DayCountActualActualICMATest.class,
	DayCountActualActualISDATest.class, 
	Days30Test.class, 
	HolidayCalendarTest.class })
public class AllTests {
	public static void main(String[] args) {
		JUnitCore.main(AllTests.class.getName());
	}
}
