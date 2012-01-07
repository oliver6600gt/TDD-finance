package tddfinance.day;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

public class DayCountActual365FixedTest {

	@Test
	public void testNumberOfDays() throws Exception{
		//Start = 1 ~ 27, End = 1 ~ 27
		assertEquals( 12,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1,  3 ), new LocalDate( 2001,  1, 15 ) ) ); // Within the same month
		assertEquals( 47,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 15 ), new LocalDate( 2001,  3,  3 ) ) ); // Passes Feb 28
		assertEquals( 48,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 15 ), new LocalDate( 2000,  3,  3 ) ) ); // Passes Feb 29 
		assertEquals( 23,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 10 ), new LocalDate( 2001,  5,  3 ) ) ); // Passes 30 (month-end) 
		assertEquals( 19,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4,  8 ) ) ); // Passes 31 
		assertEquals( 61,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 30 (month-end), 31 
		assertEquals( 59,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31 
		assertEquals( 89,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30 (month-end), 31 
		assertEquals( 60,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31 
		assertEquals( 90,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30 (month-end), 31 
		//Start = 1 ~ 27,  End = Feb 28
		assertEquals(  8,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Within the same month 
		assertEquals( 39,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31 
		assertEquals( 70,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 12, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31 
		//Start = 1 ~ 27,  End = Feb 29
		assertEquals(  9,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Within the same month 
		assertEquals( 40,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31 
		assertEquals( 71,  DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 12, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 1 ~ 27,  End = 30 (Month End)
		assertEquals( 10,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Within the same month
		assertEquals( 41,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 71,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31
		assertEquals( 69,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 130, DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 70,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 131, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = 1 ~ 27,  End = 31
		assertEquals( 11,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Within the same month
		assertEquals( 39,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 40,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 41,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  6, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30
		assertEquals( 42,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  7, 20 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31
		assertEquals( 72,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 70,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31
		assertEquals( 100, DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 71,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31
		assertEquals( 101, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 1 ~ 27
		assertEquals( 20,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 21,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29 
		assertEquals( 51,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes 31
		assertEquals( 81,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30, 31
		assertEquals( 52,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 82,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = Feb 29
		assertEquals( 1,   DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  2, 29 ) ) ); 
		//Start = Feb 28, End = 30 (Month End)
		assertEquals( 61,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 122, DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 62,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 123, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 31
		assertEquals( 31,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 31 ) ) ); 
		assertEquals( 32,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 31 ) ) ); 
		assertEquals( 92,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30, 31
		assertEquals( 93,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 29, End = 1 ~ 27
		assertEquals( 20,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 51,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes 31
		assertEquals( 81,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes 30, 31
		//Start = Feb 29, End = 30
		assertEquals( 61,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes 31 
		assertEquals( 122, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes 30, 31
		//Start = Feb 29, End = 31                                                                                        
		assertEquals( 31,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 31 ) ) ); 
		assertEquals( 92,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes 30, 31
		//Start = 30, End = 1 ~ 27
		assertEquals( 20,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 51,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 31
		assertEquals( 110, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 171, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 111, DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 172, DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 30, End = Feb 28
		assertEquals( 90,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 151, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  9, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 30, End = Feb 29
		assertEquals( 91,  DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 31
		assertEquals( 152, DayCountActual365Fixed.numberOfDays( new LocalDate( 1999,  9, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 30, 31
		//Start = 30, End = 31
		assertEquals( 31,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 31 ) ) ); 
		assertEquals( 62,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  6, 30 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31 
		assertEquals( 92,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31 
		assertEquals( 121, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 182, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 122, DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 183, DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 1 ~ 27
		assertEquals( 20,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 50,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30
		assertEquals( 51,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 20 ) ) ); // Passes 31
		assertEquals( 48,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28
		assertEquals( 79,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 109, DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 49,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29
		assertEquals( 80,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 110, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 31, End = Feb 28
		assertEquals( 28,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  2, 28 ) ) ); 
		assertEquals( 59,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 120, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 10, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 31, End = Feb 29
		assertEquals( 29,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  2, 29 ) ) ); 
		assertEquals( 60,  DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31
		assertEquals( 121, DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 10, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 31, End = 30
		assertEquals( 30,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 30 ) ) );
		assertEquals( 61,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 30 ) ) );
		assertEquals( 91,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 89,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 150, DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 90,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 151, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 31
		assertEquals( 31,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  8, 31 ) ) ); 
		assertEquals( 61,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30
		assertEquals( 122, DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 59,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 60,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 90,  DayCountActual365Fixed.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 120, DayCountActual365Fixed.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 91,  DayCountActual365Fixed.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 121, DayCountActual365Fixed.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
	}
	
	@Test
	public void testFraction() throws Exception{
		//Start = 1 ~ 27, End = 1 ~ 27
		assertEquals( 12/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1,  3 ), new LocalDate( 2001,  1, 15 ) ), 1.0e-6 ); // Within the same month
		assertEquals( 47/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 15 ), new LocalDate( 2001,  3,  3 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 48/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 15 ), new LocalDate( 2000,  3,  3 ) ), 1.0e-6 ); // Passes Feb 29 
		assertEquals( 23/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 10 ), new LocalDate( 2001,  5,  3 ) ), 1.0e-6 ); // Passes 30 (month-end) 
		assertEquals( 19/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4,  8 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 61/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 20 ) ), 1.0e-6 ); // Passes 30 (month-end), 31 
		assertEquals( 59/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals( 89/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30 (month-end), 31 
		assertEquals( 60/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals( 90/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30 (month-end), 31 
		//Start = 1 ~ 27,  End = Feb 28
		assertEquals(  8/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Within the same month 
		assertEquals( 39/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 70/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 12, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31 
		//Start = 1 ~ 27,  End = Feb 29
		assertEquals(  9/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Within the same month 
		assertEquals( 40/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 71/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 12, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 1 ~ 27,  End = 30 (Month End)
		assertEquals( 10/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Within the same month
		assertEquals( 41/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 71/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 69/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 130/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 70/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 131/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 1 ~ 27,  End = 31
		assertEquals( 11/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Within the same month
		assertEquals( 39/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 40/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals( 41/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  6, 20 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30
		assertEquals( 42/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  7, 20 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 72/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 70/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 100/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 71/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 101/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 1 ~ 27
		assertEquals( 20/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end 
		assertEquals( 21/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29 
		assertEquals( 51/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 81/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 52/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 82/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = Feb 29
		assertEquals( 1/365.0,   DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); 
		//Start = Feb 28, End = 30 (Month End)
		assertEquals( 61/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 122/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals( 62/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 123/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 31
		assertEquals( 31/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); 
		assertEquals( 32/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); 
		assertEquals( 92/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 93/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 29, End = 1 ~ 27
		assertEquals( 20/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end 
		assertEquals( 51/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 81/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = Feb 29, End = 30
		assertEquals( 61/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 122/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = Feb 29, End = 31                                                                                        
		assertEquals( 31/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); 
		assertEquals( 92/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 30, End = 1 ~ 27
		assertEquals( 20/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end  
		assertEquals( 51/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  6, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 110/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 171/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 111/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 172/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 30, End = Feb 28
		assertEquals( 90/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 151/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  9, 30 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 30, End = Feb 29
		assertEquals( 91/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 152/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999,  9, 30 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		//Start = 30, End = 31
		assertEquals( 31/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); 
		assertEquals( 62/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  6, 30 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 92/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals( 121/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals( 182/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31 
		assertEquals( 122/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals( 183/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 1 ~ 27
		assertEquals( 20/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end  
		assertEquals( 50/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes 30
		assertEquals( 51/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 48/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 79/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 109/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 49/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals( 80/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 110/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 31, End = Feb 28
		assertEquals( 28/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); 
		assertEquals( 59/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 120/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 10, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 31, End = Feb 29
		assertEquals( 29/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); 
		assertEquals( 60/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 121/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 10, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 31, End = 30
		assertEquals( 30/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 );
		assertEquals( 61/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 30 ) ), 1.0e-6 );
		assertEquals( 91/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals( 89/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 150/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31 
		assertEquals( 90/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 151/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 31
		assertEquals( 31/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); 
		assertEquals( 61/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes 30
		assertEquals( 122/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 59/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 60/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals( 90/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals( 120/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 91/365.0,  DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals( 121/365.0, DayCount.fraction( DayCount.DC_ACTUAL365FIXED, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
	}
	
	@Test
	public void testKnownAs() throws Exception {
		assertEquals( Arrays.asList(  "Actual/365 Fixed", "Act/365 Fixed", "A/365 Fixed", "A/365F", "English" ), new DayCountActual365Fixed().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCountActual365Fixed dcAct365Fixed = new DayCountActual365Fixed();
		dcAct365Fixed.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList(  "Actual/365 Fixed", "Act/365 Fixed", "A/365 Fixed", "A/365F", "English" ), dcAct365Fixed.knownAs() );		
	}
}
