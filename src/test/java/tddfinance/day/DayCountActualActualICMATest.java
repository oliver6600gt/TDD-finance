package tddfinance.day;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * 
 * 
 */
public class DayCountActualActualICMATest {

	@Test
	public void testFraction() throws Exception{
		assertEquals( 0.4958904,    DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 7, 10) ), 1.0e-6 ); //The base case; non exactly 0.5
		assertEquals( 0.4958904,    DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 7, 10), new LocalDate(2002, 1, 10) ), 1.0e-6 ); //The base case + annual compounding; still same
		assertEquals( 0.4958904,    DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 7, 10), Compounding.ANNUAL ), 1.0e-6 ); //The base case + nextPaymentSettleDate (annual); still same
		assertEquals( 0.4958904,    DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 7, 10), new LocalDate(2002, 1, 10), Compounding.ANNUAL ), 1.0e-6 ); //The base case + nextPaymentSettleDate + annual compouding; still same
		assertEquals( 0.4945355,    DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 7, 10), new LocalDate(2002, 1, 11) ), 1.0e-6 ); //One day diff should result in a different value 
		assertEquals( 0.5,          DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 7, 10), new LocalDate(2001, 7, 10), Compounding.SEMI_ANNUAL ), 1.0e-6 ); //two semi-annual periods make up 1.0; the first half 
		assertEquals( 0.5,          DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 7, 10), new LocalDate(2002, 1, 10), new LocalDate(2002, 1, 10), Compounding.SEMI_ANNUAL ), 1.0e-6 ); //two semi-annual periods make up 1.0; the second half 
		assertEquals( 1.0/12,       DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 2, 10), new LocalDate(2001, 2, 10), Compounding.MONTHLY ), 1.0e-6 ); //One month with monthly componding is 1.0/12
		assertEquals( 31.0/365,     DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 2, 10) ), 1.0e-6 ); //but it's different if it's annual compounding
		assertEquals( 31.0/(181*2), DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2001, 1, 10), new LocalDate(2001, 2, 10), Compounding.SEMI_ANNUAL ), 1.0e-6 ); //also different with semi-annual compounding 
	}
	
	@Test
	public void testKnownAs() throws Exception {
		assertEquals( Arrays.asList( "Actual/Actual ICMA", "Actual/Actual", "Act/Act ICMA", "ISMA-99", "Act/Act ISMA" ), new DayCountActualActualICMA().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCountActualActualICMA dcActActICMA = new DayCountActualActualICMA();
		dcActActICMA.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "Actual/Actual ICMA", "Actual/Actual", "Act/Act ICMA", "ISMA-99", "Act/Act ISMA" ), dcActActICMA.knownAs() );		
	}	
}
