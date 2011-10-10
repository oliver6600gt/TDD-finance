package tddfinance.day;

import org.joda.time.LocalDate;
import static org.junit.Assert.*;

import org.junit.Test;


public class DayCountActualActualTest {
	@Test
	public void fractionTest() throws Exception {
		//January = 31 days, 2000 = 366 days
		assertEquals( 31.0/366, DayCount.fraction(DayCount.DC_ACTUALACTUAL, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), 1 ), 1.0e-6 );  
		
		//January = 31 days, 2001 = 365 days
		assertEquals( 31.0/365, DayCount.fraction(DayCount.DC_ACTUALACTUAL, new LocalDate(2001, 1, 1), new LocalDate(2001, 2, 1) ), 1.0e-6 );  

		//1 year = 360 days
		assertEquals( 1.0, DayCount.fraction(DayCount.DC_ACTUALACTUAL, new LocalDate(2000, 1, 1), new LocalDate(2001, 1, 1) ), 1.0e-6 );  

		//Semi-annual 
		assertEquals( 31.0 / (182*2), DayCount.fraction(DayCount.DC_ACTUALACTUAL, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), 2 ), 1.0e-6 );  

		//Semi-annual, adjusted settlement date
		assertEquals( 31.0 / (181*2), DayCount.fraction(DayCount.DC_ACTUALACTUAL, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), new LocalDate(2000, 6, 30), 2 ), 1.0e-6 );  

		//leap year
		assertEquals( 364.0 / 366, DayCount.fraction(DayCount.DC_ACTUALACTUAL, new LocalDate(2003, 2, 28), new LocalDate(2004, 2, 27), new LocalDate(2004, 2, 29)), 1.0e-6 );  
	}
}
