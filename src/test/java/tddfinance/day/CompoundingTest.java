package tddfinance.day;

import static org.junit.Assert.*;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.junit.Test;

public class CompoundingTest {

	@Test
	public void testFrequency() throws Exception{
		assertEquals( 1,   Compounding.ANNUAL.frequency() );
		assertEquals( 2,   Compounding.SEMI_ANNUAL.frequency() );
		assertEquals( 3,   Compounding.EVERY_FOUR_MONTH.frequency() );
		assertEquals( 4,   Compounding.QUARTERLY.frequency() );
		assertEquals( 6,   Compounding.BI_MONTHLY.frequency() );
		assertEquals( 12,  Compounding.MONTHLY.frequency() );
		assertEquals( 52,  Compounding.WEEKLY.frequency() );
		assertEquals( 365, Compounding.DAILY.frequency() );
	}

	@Test
	public void testPeriod() throws Exception{
		assertEquals( Years.ONE,    Compounding.ANNUAL.period() );
		assertEquals( Months.SIX,   Compounding.SEMI_ANNUAL.period() );
		assertEquals( Months.FOUR,  Compounding.EVERY_FOUR_MONTH.period() );
		assertEquals( Months.THREE, Compounding.QUARTERLY.period() );
		assertEquals( Months.TWO,   Compounding.BI_MONTHLY.period() );
		assertEquals( Months.ONE,   Compounding.MONTHLY.period() );
		assertEquals( Weeks.ONE,    Compounding.WEEKLY.period() );
		assertEquals( Days.ONE,     Compounding.DAILY.period() );
	}

	@Test
	public void testToString() throws Exception{
		assertEquals( "Annual Compounding",           Compounding.ANNUAL.toString() );
		assertEquals( "Semi-Annual Compounding",      Compounding.SEMI_ANNUAL.toString() );
		assertEquals( "Every-Four-Month Compounding", Compounding.EVERY_FOUR_MONTH.toString() );
		assertEquals( "Quarterly Compounding",        Compounding.QUARTERLY.toString() );
		assertEquals( "Bi-Monthly Compounding",       Compounding.BI_MONTHLY.toString() );
		assertEquals( "Monthly Compounding",          Compounding.MONTHLY.toString() );
		assertEquals( "Weekly Compounding",           Compounding.WEEKLY.toString() );
		assertEquals( "Daily Compounding",            Compounding.DAILY.toString() );
	}

}
