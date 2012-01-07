package tddfinance.day;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

public class DayCountActual365LTest {

	@Test(expected = Exception.class)
	public void test() throws Exception{
		DayCount.fraction(DayCount.DC_ACTUAL365L, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), Compounding.ANNUAL );  
	}
	@Test
	public void testKnownAs() throws Exception {
		assertEquals( Arrays.asList( "Actual/365L", "ISMA-Year" ), new DayCountActual365L().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCountActual365L dcAct365L = new DayCountActual365L();
		dcAct365L.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "Actual/365L", "ISMA-Year" ), dcAct365L.knownAs() );		
	}
}
