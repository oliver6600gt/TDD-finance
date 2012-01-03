package tddfinance.day;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

public class DayCount30E360ISDATest {

	@Test(expected = Exception.class)
	public void test() throws Exception{
		DayCount.fraction(DayCount.DC_30E360ISDA, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), Compounding.ANNUAL );  
	}


	@Test
	public void testKnownAs() throws Exception {
		assertEquals( 
			Arrays.asList( "30E/360 ISDA", "German" ), 
			new DayCount30E360ISDA().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCount30E360ISDA dc30360ISDA = new DayCount30E360ISDA();
		dc30360ISDA.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "30E/360 ISDA", "German" ), dc30360ISDA.knownAs() );		
	}
}
