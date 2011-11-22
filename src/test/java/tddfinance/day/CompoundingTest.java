package tddfinance.day;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompoundingTest {

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
