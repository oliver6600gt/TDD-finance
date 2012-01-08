package tddfinance.day;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * 
 * 
 */
public class DayCountActualActualISDATest {

	@Test(expected = Exception.class)
	public void test() throws Exception{
		DayCount.fraction(DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), Compounding.ANNUAL );  
	}

	@Test
	public void testNumberOfDaysInLeapYear() throws Exception {
	}
	
	@Test
	public void testKnownAs() throws Exception {
		assertEquals( Arrays.asList( "Actual/Actual ISDA", "Actual/Actual", "Act/Act", "Actual/365", "Act/365" ), new DayCountActualActualISDA().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCountActualActualISDA dcActActISDA = new DayCountActualActualISDA();
		dcActActISDA.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "Actual/Actual ISDA", "Actual/Actual", "Act/Act", "Actual/365", "Act/365" ), dcActActISDA.knownAs() );		
	}	
}
