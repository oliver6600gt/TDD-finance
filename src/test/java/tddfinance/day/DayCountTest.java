package tddfinance.day;

import static org.junit.Assert.*;
import static tddfinance.util.Assertion.*;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.junit.Test;

public class DayCountTest {

	@Test
	public void getPeriodFromCompoundingFrequencyTest() throws Exception {
		assertEquals( Years.ONE,    DayCount.periodFromCompoundingFrequency( 1 ) );
		assertEquals( Months.SIX,   DayCount.periodFromCompoundingFrequency( 2 ) );
		assertEquals( Months.FOUR,  DayCount.periodFromCompoundingFrequency( 3 ) );
		assertEquals( Months.THREE, DayCount.periodFromCompoundingFrequency( 4 ) );
		assertEquals( Months.TWO,   DayCount.periodFromCompoundingFrequency( 6 ) );
		assertEquals( Months.ONE,   DayCount.periodFromCompoundingFrequency( 12 ) );
		assertEquals( Weeks.ONE,    DayCount.periodFromCompoundingFrequency( 52 ) );
		assertEquals( Days.ONE,     DayCount.periodFromCompoundingFrequency( 365 ) );
	}
	
	@Test
	public void getPeriodFromCompoundinFrequencyInvalidCasesTest() throws Exception {
		try { DayCount.periodFromCompoundingFrequency( -1 ) ; failUnexpectedToReachThis(); } catch (Exception e) { assertExceptionMessage( e, "compoundingFrequency = -1 is invalid" ); };
		try { DayCount.periodFromCompoundingFrequency( 0 )  ; failUnexpectedToReachThis(); } catch (Exception e) { assertExceptionMessage( e, "compoundingFrequency = 0 is invalid" ); };
		try { DayCount.periodFromCompoundingFrequency( 5 )  ; failUnexpectedToReachThis(); } catch (Exception e) { assertExceptionMessage( e, "compoundingFrequency = 5 is invalid" ); };
	}
}
