package tddfinance.day;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

public class DayCount30E360ISDATest {

	@Test
	public void testNumberOfDays() throws Exception {
		//Start = 1 ~ 27, End = 1 ~ 27
		assertEquals( 12,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1,  3 ), new LocalDate( 2001,  1, 15 ) ) ); // Within the same month
		assertEquals( 48,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 15 ), new LocalDate( 2001,  3,  3 ) ) ); // Passes Feb 28
		assertEquals( 48,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 15 ), new LocalDate( 2000,  3,  3 ) ) ); // Passes Feb 29 
		assertEquals( 23,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 10 ), new LocalDate( 2001,  5,  3 ) ) ); // Passes 30 (month-end) 
		assertEquals( 18,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4,  8 ) ) ); // Passes 31 
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 30 (month-end), 31 
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31 
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30 (month-end), 31 
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31 
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30 (month-end), 31 
		//Start = 1 ~ 27,  End = Feb 28
		assertEquals( 10,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Within the same month 
		assertEquals( 40,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31 
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 12, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31 
		//Start = 1 ~ 27,  End = Feb 29
		assertEquals( 10,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Within the same month 
		assertEquals( 40,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31 
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 12, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 1 ~ 27,  End = 30 (Month End)
		assertEquals( 10,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Within the same month
		assertEquals( 40,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 130, DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 130, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = 1 ~ 27,  End = 31
		assertEquals( 10,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Within the same month
		assertEquals( 40,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 40,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 40,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  6, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30
		assertEquals( 40,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  7, 20 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31
		assertEquals( 100, DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 70,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31
		assertEquals( 100, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 1 ~ 27
		assertEquals( 20,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 22,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29 
		assertEquals( 50,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes 31
		assertEquals( 80,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30, 31
		assertEquals( 52,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 82,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = Feb 29
		assertEquals( 2,   DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  2, 29 ) ) ); 
		//Start = Feb 28, End = 30 (Month End)
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 62,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 122, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 31
		assertEquals( 30,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 31 ) ) ); 
		assertEquals( 32,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 31 ) ) ); 
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30, 31
		assertEquals( 92,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 29, End = 1 ~ 27
		assertEquals( 20,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 50,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes 31
		assertEquals( 80,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes 30, 31
		//Start = Feb 29, End = 30
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes 31 
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes 30, 31
		//Start = Feb 29, End = 31                                                                                        
		assertEquals( 30,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 31 ) ) ); 
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes 30, 31
		//Start = 30, End = 1 ~ 27
		assertEquals( 20,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 50,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 31
		assertEquals( 110, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 170, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 110, DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 170, DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 30, End = Feb 28
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 150, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  9, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 30, End = Feb 29
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 31
		assertEquals( 150, DayCount30E360ISDA.numberOfDays( new LocalDate( 1999,  9, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 30, 31
		//Start = 30, End = 31
		assertEquals( 30,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 31 ) ) ); 
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  6, 30 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31 
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31 
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 180, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 180, DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 1 ~ 27
		assertEquals( 20,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 50,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30
		assertEquals( 50,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 20 ) ) ); // Passes 31
		assertEquals( 50,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28
		assertEquals( 80,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 110, DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 50,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29
		assertEquals( 80,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 110, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 31, End = Feb 28
		assertEquals( 30,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  2, 28 ) ) ); 
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 10, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 31, End = Feb 29
		assertEquals( 30,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  2, 29 ) ) ); 
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 10, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 31, End = 30
		assertEquals( 30,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 30 ) ) );
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 30 ) ) );
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 150, DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 150, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 31
		assertEquals( 30,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  8, 31 ) ) ); 
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 60,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 90,  DayCount30E360ISDA.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 120, DayCount30E360ISDA.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
	}
	
	@Test
	public void testFactor() throws Exception {
//		//Start = 1 ~ 27, End = 1 ~ 27
		assertEquals(  12/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1,  3 ), new LocalDate( 2001,  1, 15 ) ), 1.0e-6 ); // Within the same month
		assertEquals(  48/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 15 ), new LocalDate( 2001,  3,  3 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals(  48/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 15 ), new LocalDate( 2000,  3,  3 ) ), 1.0e-6 ); // Passes Feb 29 
		assertEquals(  23/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 10 ), new LocalDate( 2001,  5,  3 ) ), 1.0e-6 ); // Passes 30 (month-end) 
		assertEquals(  18/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4,  8 ) ), 1.0e-6 ); // Passes 31 
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 20 ) ), 1.0e-6 ); // Passes 30 (month-end), 31 
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30 (month-end), 31 
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30 (month-end), 31 
		//Start = 1 ~  27, End = Feb 28                                                                                     
		assertEquals(  10/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Within the same month 
		assertEquals(  40/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31 
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 12, 20 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31 
		//Start = 1 ~  27, End = Feb 29                                                                                   
		assertEquals(  10/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Within the same month 
		assertEquals(  40/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 31 
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 12, 20 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 1 ~  27, End = 30 (Month End)                                                                             
		assertEquals(  10/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Within the same month
		assertEquals(  40/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals(  130/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals(  130/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 1 ~  27, End = 31                                                                                   
		assertEquals(  10/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Within the same month
		assertEquals(  40/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals(  40/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals(  40/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  6, 20 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30
		assertEquals(  40/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  7, 20 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals(  100/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals(  70/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals(  100/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb  28, End = 1 ~ 27                                                                                       
		assertEquals(  20/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end 
		assertEquals(  22/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29 
		assertEquals(  50/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  80/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals(  52/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals(  82/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb  28, End = Feb 29                                                                                    
		assertEquals(  2/360.0,   DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); 
		//Start = Feb  28, End = 30 (Month End)                                                                          
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals(  62/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals(  122/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb  28, End = 31                                                                                         
		assertEquals(  30/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); 
		assertEquals(  32/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); 
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals(  92/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = Feb  29, End = 1 ~ 27                                                                                      
		assertEquals(  20/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end 
		assertEquals(  50/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  80/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = Feb  29, End = 30                                                                                           
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes 31 
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = Feb  29, End = 31                                                                                        
		assertEquals(  30/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); 
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 30,  End = 1 ~ 27                                                                                         
		assertEquals(  20/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end  
		assertEquals(  50/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  6, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  110/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals(  170/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals(  110/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals(  170/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 30,  End = Feb 28                                                                                          
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  150/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  9, 30 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 30,  End = Feb 29                                                                                      
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals(  150/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999,  9, 30 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		//Start = 30,  End = 31                                                                                              
		assertEquals(  30/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); 
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  6, 30 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); // Passes 31 
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals(  180/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31 
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals(  180/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31 
		//Start = 31,  End = 1 ~ 27                                                                                     
		assertEquals(  20/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Next month but doesn't end in month-end  
		assertEquals(  50/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes 30
		assertEquals(  50/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 20 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  50/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 20 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals(  80/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 20 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals(  110/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 20 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals(  50/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 20 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals(  80/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 20 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals(  110/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 20 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
		//Start = 31,  End = Feb 28                                                                                       
		assertEquals(  30/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); 
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 10, 31 ), new LocalDate( 2001,  2, 28 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 31,  End = Feb 29                                                                               
		assertEquals(  30/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); 
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 31
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 10, 31 ), new LocalDate( 2000,  2, 29 ) ), 1.0e-6 ); // Passes 30, 31
		//Start = 31,  End = 30                                                                        
		assertEquals(  30/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 );
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 30 ) ), 1.0e-6 );
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes 30, 31 
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 30 ) ), 1.0e-6 ); // Passes Feb 28, 31
		assertEquals(  150/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  6, 30 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31 
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 30 ) ), 1.0e-6 ); // Passes Feb 29, 31
		assertEquals(  150/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  6, 30 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31 
		//Start = 31,  End = 31                                                                                              
		assertEquals(  30/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  8, 31 ) ), 1.0e-6 ); 
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes 30
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  7, 31 ) ), 1.0e-6 ); // Passes 30, 31
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28
		assertEquals(  60/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  3, 31 ) ), 1.0e-6 ); // Passes Feb 28, 31 
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 31 ) ), 1.0e-6 ); // Passes Feb 28, 30, 31
		assertEquals(  90/360.0,  DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  3, 31 ) ), 1.0e-6 ); // Passes Feb 29, 31 
		assertEquals(  120/360.0, DayCount.fraction( DayCount.DC_30E360ISDA,  new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 31 ) ), 1.0e-6 ); // Passes Feb 29, 30, 31
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
