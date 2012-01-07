package tddfinance.day;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

public class DayCountActual360Test {

	@Test
	public void testNumberOfDays() throws Exception{
		//Start = 1 ~ 27, End = 1 ~ 27
		assertEquals( 12,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1,  3 ), new LocalDate( 2001,  1, 15 ) ) ); // Within the same month
		assertEquals( 47,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 15 ), new LocalDate( 2001,  3,  3 ) ) ); // Passes Feb 28
		assertEquals( 48,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 15 ), new LocalDate( 2000,  3,  3 ) ) ); // Passes Feb 29 
		assertEquals( 23,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 10 ), new LocalDate( 2001,  5,  3 ) ) ); // Passes 30 (month-end) 
		assertEquals( 19,  DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4,  8 ) ) ); // Passes 31 
		assertEquals( 61,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 30 (month-end), 31 
		assertEquals( 59,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31 
		assertEquals( 89,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30 (month-end), 31 
		assertEquals( 60,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31 
		assertEquals( 90,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30 (month-end), 31 
		//Start = 1 ~ 27,  End = Feb 28
		assertEquals(  8,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Within the same month 
		assertEquals( 39,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31 
		assertEquals( 70,  DayCountActual360.numberOfDays( new LocalDate( 2000, 12, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31 
		//Start = 1 ~ 27,  End = Feb 29
		assertEquals(  9,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Within the same month 
		assertEquals( 40,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31 
		assertEquals( 71,  DayCountActual360.numberOfDays( new LocalDate( 1999, 12, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 1 ~ 27,  End = 30 (Month End)
		assertEquals( 10,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Within the same month
		assertEquals( 41,  DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 71,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31
		assertEquals( 69,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 130, DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 70,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 131, DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = 1 ~ 27,  End = 31
		assertEquals( 11,  DayCountActual360.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Within the same month
		assertEquals( 39,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 40,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 41,  DayCountActual360.numberOfDays( new LocalDate( 2001,  6, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30
		assertEquals( 42,  DayCountActual360.numberOfDays( new LocalDate( 2001,  7, 20 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31
		assertEquals( 72,  DayCountActual360.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 70,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31
		assertEquals( 100, DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 71,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31
		assertEquals( 101, DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 1 ~ 27
		assertEquals( 20,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 21,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29 
		assertEquals( 51,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes 31
		assertEquals( 81,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30, 31
		assertEquals( 52,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 82,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = Feb 29
		assertEquals( 1,   DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  2, 29 ) ) ); 
		//Start = Feb 28, End = 30 (Month End)
		assertEquals( 61,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 122, DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 62,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 123, DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 31
		assertEquals( 31,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 31 ) ) ); 
		assertEquals( 32,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 31 ) ) ); 
		assertEquals( 92,  DayCountActual360.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30, 31
		assertEquals( 93,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 29, End = 1 ~ 27
		assertEquals( 20,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 51,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes 31
		assertEquals( 81,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes 30, 31
		//Start = Feb 29, End = 30
		assertEquals( 61,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes 31 
		assertEquals( 122, DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes 30, 31
		//Start = Feb 29, End = 31                                                                                        
		assertEquals( 31,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 31 ) ) ); 
		assertEquals( 92,  DayCountActual360.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes 30, 31
		//Start = 30, End = 1 ~ 27
		assertEquals( 20,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 51,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 31
		assertEquals( 110, DayCountActual360.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 171, DayCountActual360.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 111, DayCountActual360.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 172, DayCountActual360.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 30, End = Feb 28
		assertEquals( 90,  DayCountActual360.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 151, DayCountActual360.numberOfDays( new LocalDate( 2000,  9, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 30, End = Feb 29
		assertEquals( 91,  DayCountActual360.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 31
		assertEquals( 152, DayCountActual360.numberOfDays( new LocalDate( 1999,  9, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 30, 31
		//Start = 30, End = 31
		assertEquals( 31,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 31 ) ) ); 
		assertEquals( 62,  DayCountActual360.numberOfDays( new LocalDate( 2001,  6, 30 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31 
		assertEquals( 92,  DayCountActual360.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31 
		assertEquals( 121, DayCountActual360.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 182, DayCountActual360.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 122, DayCountActual360.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 183, DayCountActual360.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 1 ~ 27
		assertEquals( 20,  DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 50,  DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30
		assertEquals( 51,  DayCountActual360.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 20 ) ) ); // Passes 31
		assertEquals( 48,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28
		assertEquals( 79,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 109, DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 49,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29
		assertEquals( 80,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 110, DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 31, End = Feb 28
		assertEquals( 28,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  2, 28 ) ) ); 
		assertEquals( 59,  DayCountActual360.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 120, DayCountActual360.numberOfDays( new LocalDate( 2000, 10, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 31, End = Feb 29
		assertEquals( 29,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  2, 29 ) ) ); 
		assertEquals( 60,  DayCountActual360.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31
		assertEquals( 121, DayCountActual360.numberOfDays( new LocalDate( 1999, 10, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 31, End = 30
		assertEquals( 30,  DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 30 ) ) );
		assertEquals( 61,  DayCountActual360.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 30 ) ) );
		assertEquals( 91,  DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 89,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 150, DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 90,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 151, DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 31
		assertEquals( 31,  DayCountActual360.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  8, 31 ) ) ); 
		assertEquals( 61,  DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30
		assertEquals( 122, DayCountActual360.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 59,  DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 60,  DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 90,  DayCountActual360.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 120, DayCountActual360.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 91,  DayCountActual360.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 121, DayCountActual360.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
	}
	
	@Test
	public void testFraction() throws Exception{
		//Start = 1 ~ 27, End = 1 ~ 27
		assertEquals( 12/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1,  3 ), new LocalDate( 2001,  1, 15 ) ), 1.0e-6 ); // Within the same month
		assertEquals( 47/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 15 ), new LocalDate( 2001,  3,  3 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 48/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 15 ), new LocalDate( 2000,  3,  3 ) ), 1.0e-6 ); // Passes Feb 29 
		assertEquals( 23/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 10 ), new LocalDate( 2001,  5,  3 ) ), 1.0e-6 ); // Passes 30 (month-end) 
		assertEquals( 19/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4,  8 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 61/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 20 ) ), 1.0e-6 ); // Passes 30 (month-end), 31 
		assertEquals( 59/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals( 89/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30 (month-end), 31 
		assertEquals( 60/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals( 90/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30 (month-end), 31 
		//Start = 1 ~ 27,  End = Feb 28
		assertEquals(  8/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Within the same month 
		assertEquals( 39/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 70/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 12, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31 
		//Start = 1 ~ 27,  End = Feb 29
		assertEquals(  9/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Within the same month 
		assertEquals( 40/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 71/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 12, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 1 ~ 27,  End = 30 (Month End)
		assertEquals( 10/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Within the same month
		assertEquals( 41/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 71/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 69/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 130/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 70/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 131/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 1 ~ 27,  End = 31
		assertEquals( 11/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Within the same month
		assertEquals( 39/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 40/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals( 41/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  6, 20 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30
		assertEquals( 42/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  7, 20 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 72/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 70/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 100/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 71/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 101/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 1 ~ 27
		assertEquals( 20/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end 
		assertEquals( 21/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29 
		assertEquals( 51/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 81/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 52/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 82/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = Feb 29
		assertEquals( 1/360.0,   DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); 
		//Start = Feb 28, End = 30 (Month End)
		assertEquals( 61/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 122/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals( 62/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 123/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 31
		assertEquals( 31/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); 
		assertEquals( 32/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); 
		assertEquals( 92/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 93/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb 29, End = 1 ~ 27
		assertEquals( 20/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end 
		assertEquals( 51/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 81/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = Feb 29, End = 30
		assertEquals( 61/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 122/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = Feb 29, End = 31                                                                                        
		assertEquals( 31/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); 
		assertEquals( 92/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 30, End = 1 ~ 27
		assertEquals( 20/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end  
		assertEquals( 51/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  6, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 110/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 171/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 111/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 172/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 30, End = Feb 28
		assertEquals( 90/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 151/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  9, 30 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 30, End = Feb 29
		assertEquals( 91/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 152/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999,  9, 30 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		//Start = 30, End = 31
		assertEquals( 31/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); 
		assertEquals( 62/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  6, 30 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); // Passes 31 
		assertEquals( 92/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals( 121/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals( 182/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31 
		assertEquals( 122/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals( 183/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 1 ~ 27
		assertEquals( 20/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end  
		assertEquals( 50/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes 30
		assertEquals( 51/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 48/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 79/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 109/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 49/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals( 80/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 110/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 31, End = Feb 28
		assertEquals( 28/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); 
		assertEquals( 59/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 120/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 10, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 31, End = Feb 29
		assertEquals( 29/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); 
		assertEquals( 60/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 31
		assertEquals( 121/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 10, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 31, End = 30
		assertEquals( 30/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 );
		assertEquals( 61/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 30 ) ), 1.0e-6 );
		assertEquals( 91/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals( 89/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals( 150/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31 
		assertEquals( 90/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals( 151/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 31
		assertEquals( 31/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); 
		assertEquals( 61/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes 30
		assertEquals( 122/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals( 59/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals( 60/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals( 90/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals( 120/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals( 91/360.0,  DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals( 121/360.0, DayCount.fraction( DayCount.DC_ACTUAL360, new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
	}

	@Test
	public void testKnownAs() throws Exception {
		assertEquals( Arrays.asList( "Actual/360", "Act/360", "A/360", "French" ), new DayCountActual360().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCountActual360 dcAct360 = new DayCountActual360();
		dcAct360.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "Actual/360", "Act/360", "A/360", "French" ), dcAct360.knownAs() );		
	}
}
