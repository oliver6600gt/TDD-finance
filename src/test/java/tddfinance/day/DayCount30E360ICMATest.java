package tddfinance.day;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * 
 *
 * 
 */
public class DayCount30E360ICMATest {

	@Test(expected = Exception.class)
	public void test() throws Exception{
		DayCount.fraction(DayCount.DC_30E360ICMA, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), Compounding.ANNUAL );  
	}

	@Test
	public void testKnownAs() throws Exception {
		assertEquals( 
			Arrays.asList( "30/360 ICMA", "30E/360", "Eurobond basis", "Special German" ), 
			new DayCount30E360ICMA().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCount30E360ICMA dc30360ICMA = new DayCount30E360ICMA();
		dc30360ICMA.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "30/360 ICMA", "30E/360", "Eurobond basis", "Special German" ), dc30360ICMA.knownAs() );		
	}
}
