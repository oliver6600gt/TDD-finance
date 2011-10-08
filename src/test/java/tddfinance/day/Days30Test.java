package tddfinance.day;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;


public class Days30Test {
	@Test
	public void numberOfDaysTest() throws Exception {
		//1 month = 30 days
		assertEquals( 30, Days30.numberOfDays( new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1) ) );  
		
		//1 year = 360 days
		assertEquals( 360, Days30.numberOfDays( new LocalDate(2000, 1, 1), new LocalDate(2001, 1, 1) ) );  

		//2 year + 3 months = 810 days
		assertEquals( 810, Days30.numberOfDays( new LocalDate(2000, 1, 1), new LocalDate(2002, 4, 1) ) );  

		//in the same month
		assertEquals( 15, Days30.numberOfDays( new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 16) ) );
		
		//1 month = 30 days, and different days of month
		assertEquals( 25, Days30.numberOfDays( new LocalDate(2000, 1, 15), new LocalDate(2000, 2, 10) ) );
		
		//2 months = 60 days, and different days of month
		assertEquals( 74, Days30.numberOfDays( new LocalDate(2000, 1, 1), new LocalDate(2000, 3, 15) ) );

		//31st should be treated as 30th
		assertEquals( 1, Days30.numberOfDays( new LocalDate(2000, 8, 31), new LocalDate(2000, 9, 1) ) );

		//31st should be treated as 30th
		assertEquals( 30, Days30.numberOfDays( new LocalDate(2000, 7, 30), new LocalDate(2000, 8, 31) ) );

		//31st should be treated as 30th
		assertEquals( 30, Days30.numberOfDays( new LocalDate(2000, 7, 31), new LocalDate(2000, 8, 31) ) );
		
		//Feb 28th is 28th, should not be treated as 30th
		assertEquals( 3, Days30.numberOfDays( new LocalDate(2000, 2, 28), new LocalDate(2000, 3, 1) ) );

		//Feb 29th is 29th, should not be treated as 30th
		assertEquals( 2, Days30.numberOfDays( new LocalDate(2000, 2, 29), new LocalDate(2000, 3, 1) ) );
	}
}
