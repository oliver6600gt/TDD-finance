package tddfinance.day;

import static org.junit.Assert.*;

import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;

public class CompoundingTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
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
	public void testContinuousFrequency() throws Exception {
		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(JUnitMatchers.containsString("does not support the frequency() method"));
		Compounding.CONTINUOUS.frequency();
	}

	@Test
	public void testFraction() throws Exception{
		assertEquals( 1.0/1,   Compounding.ANNUAL.fraction(),           1.0e-6 );
		assertEquals( 1.0/2,   Compounding.SEMI_ANNUAL.fraction(),      1.0e-6 );
		assertEquals( 1.0/3,   Compounding.EVERY_FOUR_MONTH.fraction(), 1.0e-6 );
		assertEquals( 1.0/4,   Compounding.QUARTERLY.fraction(),        1.0e-6 );
		assertEquals( 1.0/6,   Compounding.BI_MONTHLY.fraction(),       1.0e-6 );
		assertEquals( 1.0/12,  Compounding.MONTHLY.fraction(),          1.0e-6 );
		assertEquals( 1.0/52,  Compounding.WEEKLY.fraction(),           1.0e-6 );
		assertEquals( 1.0/365, Compounding.DAILY.fraction(),            1.0e-6 );
	}

	@Test
	public void testContinuousFraction() throws Exception {
		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(JUnitMatchers.containsString("does not support the fraction() method"));
		Compounding.CONTINUOUS.fraction();
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
	public void testContinuousPeriod() throws Exception {
		expectedException.expect(UnsupportedOperationException.class);
		expectedException.expectMessage(JUnitMatchers.containsString("does not support the period() method"));
		Compounding.CONTINUOUS.period();
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
		assertEquals( "Continuous Compounding",       Compounding.CONTINUOUS.toString() );
	}

	@Test
	public void testReturnInPeriod() throws Exception{
		assertEquals( 1.05,         Compounding.ANNUAL.returnInPeriod( 0.05, 1.0 ),           1.0e-10 );
		assertEquals( 1.0246950766, Compounding.ANNUAL.returnInPeriod( 0.05, 0.5 ),           1.0e-10 );
		assertEquals( 1.050625,     Compounding.SEMI_ANNUAL.returnInPeriod( 0.05, 1.0 ),      1.0e-10 );
		assertEquals( 1.061208,     Compounding.EVERY_FOUR_MONTH.returnInPeriod( 0.06, 1.0 ), 1.0e-10 );
		assertEquals( 1.0509453369, Compounding.QUARTERLY.returnInPeriod( 0.05, 1.0 ),        1.0e-10 );
		assertEquals( 1.1044861012, Compounding.QUARTERLY.returnInPeriod( 0.05, 2.0 ),        1.0e-10 );
		assertEquals( 1.0615201506, Compounding.BI_MONTHLY.returnInPeriod( 0.06, 1.0 ),       1.0e-10 );
		assertEquals( 1.1268250301, Compounding.MONTHLY.returnInPeriod( 0.12, 1.0 ),          1.0e-10 );
		assertEquals( 1.6776889214, Compounding.WEEKLY.returnInPeriod( 0.52, 1.0 ),           1.0e-10 );
		assertEquals( 1.4402513134, Compounding.DAILY.returnInPeriod( 0.365, 1.0 ),           1.0e-10 );
		assertEquals( 2.7182818285, Compounding.CONTINUOUS.returnInPeriod( 0.1, 10.0 ),       1.0e-10 );
	}
	
//	@Test
//	public void compareExpAndExpM1() throws Exception {
//      //All those gave me almost same values... probably the native func for exp() was improved??
//		//So I guess no need to use expm1
//		System.out.println( Math.exp(1.0) );
//		System.out.println( Math.expm1(1.0) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.1) );
//		System.out.println( Math.expm1(0.1) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.01) );
//		System.out.println( Math.expm1(0.01) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.001) );
//		System.out.println( Math.expm1(0.001) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.0001) );
//		System.out.println( Math.expm1(0.0001) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.00001) );
//		System.out.println( Math.expm1(0.00001) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.000001) );
//		System.out.println( Math.expm1(0.000001) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.0000001) );
//		System.out.println( Math.expm1(0.0000001) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.00000001) );
//		System.out.println( Math.expm1(0.00000001) + 1 );
//		System.out.println();
//		System.out.println( Math.exp(0.000000001) );
//		System.out.println( Math.expm1(0.000000001) + 1 );
//		System.out.println();
//	}

}
