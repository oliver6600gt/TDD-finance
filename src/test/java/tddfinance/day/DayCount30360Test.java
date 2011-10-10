package tddfinance.day;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;


public class DayCount30360Test {
	@Test
	public void fractorTest() throws Exception {
		//1 month = 30 days
		assertEquals( 1.0/12, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), new LocalDate(2000, 7, 1), 2 ), 1.0e-6 );  
		
		//1 year = 360 days
		assertEquals( 1.0, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 1, 1), new LocalDate(2001, 1, 1), new LocalDate(2001, 1, 1) ), 1.0e-6 );  

		//1 year - 3 months - 7 days = 263 days
		assertEquals( 263.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 4, 10), new LocalDate(2001, 1, 3), new LocalDate(2001, 4, 10) ), 1.0e-6 );  

		//in the same month
		assertEquals( 15.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 16), new LocalDate(2001, 1, 1) ), 1.0e-6 );  
		
		//1 month = 30 days, and different days of month
		assertEquals( 25.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 1, 15), new LocalDate(2000, 2, 10), new LocalDate(2001, 1, 15) ), 1.0e-6 );  
		
		//2 months = 60 days, and different days of month
		assertEquals( 74.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 1, 1), new LocalDate(2000, 3, 15), new LocalDate(2001, 1, 1) ), 1.0e-6 );  

		//31st should be treated as 30th
		assertEquals( 1.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 8, 31), new LocalDate(2000, 9, 1), new LocalDate(2001, 8, 31) ), 1.0e-6 );  
		
		//31st should be treated as 30th
		assertEquals( 30.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 7, 30), new LocalDate(2000, 8, 31), new LocalDate(2001, 7, 30) ), 1.0e-6 );  

		//31st should be treated as 30th
		assertEquals( 30.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 7, 31), new LocalDate(2000, 8, 31), new LocalDate(2001, 7, 31) ), 1.0e-6 );  
		
		//Feb 28th is 28th, should not be treated as 30th
		assertEquals( 3.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 2, 28), new LocalDate(2000, 3, 1), new LocalDate(2001, 2, 28) ), 1.0e-6 );  
		
		//Feb 29th is 29th, should not be treated as 30th
		assertEquals( 2.0/360, DayCount.fraction(DayCount.DC_30360, new LocalDate(2000, 2, 29), new LocalDate(2000, 3, 1), new LocalDate(2001, 2, 28) ), 1.0e-6 ); 
	}
}