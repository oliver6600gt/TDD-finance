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
		assertEquals( Years.ONE,    DayCount.getPeriodFromCompoundingFrequency( 1 ) );
		assertEquals( Months.SIX,   DayCount.getPeriodFromCompoundingFrequency( 2 ) );
		assertEquals( Months.FOUR,  DayCount.getPeriodFromCompoundingFrequency( 3 ) );
		assertEquals( Months.THREE, DayCount.getPeriodFromCompoundingFrequency( 4 ) );
		assertEquals( Months.TWO,   DayCount.getPeriodFromCompoundingFrequency( 6 ) );
		assertEquals( Months.ONE,   DayCount.getPeriodFromCompoundingFrequency( 12 ) );
		assertEquals( Weeks.ONE,    DayCount.getPeriodFromCompoundingFrequency( 52 ) );
		assertEquals( Days.ONE,     DayCount.getPeriodFromCompoundingFrequency( 365 ) );
	}
	
	@Test
	public void getPeriodFromCompoundinFrequencyInvalidCasesTest() throws Exception {
		try { DayCount.getPeriodFromCompoundingFrequency( -1 ) ; failUnexpectedToReachThis(); } catch (Exception e) { assertExceptionMessage( e, "compoundingFrequency = -1 is invalid" ); };
		try { DayCount.getPeriodFromCompoundingFrequency( 0 )  ; failUnexpectedToReachThis(); } catch (Exception e) { assertExceptionMessage( e, "compoundingFrequency = 0 is invalid" ); };
		try { DayCount.getPeriodFromCompoundingFrequency( 5 )  ; failUnexpectedToReachThis(); } catch (Exception e) { assertExceptionMessage( e, "compoundingFrequency = 5 is invalid" ); };
	}
}
