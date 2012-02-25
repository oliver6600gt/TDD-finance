package tddfinance.numeral;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import tddfinance.day.Compounding;
import tddfinance.day.DayCount;

public class AccruedInterestTest {
	
	@Test
	public void accrualStartDateTest() {
		assertEquals( new LocalDate( 2010, 4, 10 ), new AccruedInterest( 100, 0.01, new LocalDate(2010,4,10), new LocalDate(2010,6,10), new LocalDate(2010,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).accrualStartDate() );
		assertEquals( new LocalDate( 2011, 4, 10 ), new AccruedInterest( 100, 0.01, new LocalDate(2011,4,10), new LocalDate(2011,6,10), new LocalDate(2011,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).accrualStartDate() );
	}
	
	@Test
	public void accrualEndDateTest() {
		assertEquals( new LocalDate( 2010, 6, 10 ), new AccruedInterest( 100, 0.01, new LocalDate(2010,4,10), new LocalDate(2010,6,10), new LocalDate(2010,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).accrualEndDate() );
		assertEquals( new LocalDate( 2011, 6, 10 ), new AccruedInterest( 100, 0.01, new LocalDate(2011,4,10), new LocalDate(2011,6,10), new LocalDate(2011,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).accrualEndDate() );
	}

	@Test
	public void nextCouponDateTest() {
		assertEquals( new LocalDate( 2010, 7, 10 ), new AccruedInterest( 100, 0.01, new LocalDate(2010,4,10), new LocalDate(2010,6,10), new LocalDate(2010,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).nextCouponDate() );
		assertEquals( new LocalDate( 2011, 7, 10 ), new AccruedInterest( 100, 0.01, new LocalDate(2011,4,10), new LocalDate(2011,6,10), new LocalDate(2011,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).nextCouponDate() );
	}
	
	@Test
	public void couponRateTest() {
		assertEquals( 0.01, new AccruedInterest( 100, 0.01, new LocalDate(2010,4,10), new LocalDate(2010,6,10), new LocalDate(2010,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).couponRate(), 1.0e-8 );
		assertEquals( 0.02, new AccruedInterest( 100, 0.02, new LocalDate(2011,4,10), new LocalDate(2011,6,10), new LocalDate(2011,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).couponRate(), 1.0e-8 );
	}
	
	@Test
	public void faceValueTest() {
		assertEquals( 100, new AccruedInterest( 100, 0.01, new LocalDate(2010,4,10), new LocalDate(2010,6,10), new LocalDate(2010,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).faceValue(), 1.0e-8 );
		assertEquals( 200, new AccruedInterest( 200, 0.02, new LocalDate(2011,4,10), new LocalDate(2011,6,10), new LocalDate(2011,7,10), DayCount.DC_30360US, Compounding.QUARTERLY ).faceValue(), 1.0e-8 );
	}
	
	@Test
	public void dayCountConventionTest() {
		assertEquals( DayCount.DC_30360US,    new AccruedInterest( 100, 0.01, new LocalDate( 2010, 4, 10 ), new LocalDate( 2010, 6, 10 ), new LocalDate( 2010, 7, 10 ), DayCount.DC_30360US,    Compounding.QUARTERLY ).dayCountConvention() );
		assertEquals( DayCount.DC_30E360ICMA, new AccruedInterest( 100, 0.01, new LocalDate( 2010, 4, 10 ), new LocalDate( 2010, 6, 10 ), new LocalDate( 2010, 7, 10 ), DayCount.DC_30E360ICMA, Compounding.QUARTERLY ).dayCountConvention() );
	}

	@Test
	public void compoundingTest() {
		assertEquals( Compounding.QUARTERLY,   new AccruedInterest( 100, 0.01, new LocalDate( 2010, 4, 10 ), new LocalDate( 2010, 6, 10 ), new LocalDate( 2010, 7, 10 ),  DayCount.DC_30360US, Compounding.QUARTERLY   ).compoundingRule() );
		assertEquals( Compounding.SEMI_ANNUAL, new AccruedInterest( 100, 0.01, new LocalDate( 2010, 4, 10 ), new LocalDate( 2010, 6, 10 ), new LocalDate( 2010, 10, 10 ), DayCount.DC_30360US, Compounding.SEMI_ANNUAL ).compoundingRule() );
	}

	@Test
	public void getValueTest() throws Exception {
		assertEquals( 100*0.03*60.0/360, new AccruedInterest( 100, 0.03, new LocalDate( 2010, 4, 10 ), new LocalDate( 2010, 6, 10 ), new LocalDate( 2010, 7, 10 ), DayCount.DC_30360US, Compounding.QUARTERLY ).getValue(), 1.0e-8 );
	}
	
}
