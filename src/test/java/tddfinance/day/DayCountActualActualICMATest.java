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

	@Test(expected = Exception.class)
	public void test() throws Exception{
		DayCount.fraction(DayCount.DC_ACTUAL_ACTUAL_ICMA, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), Compounding.ANNUAL );  
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
