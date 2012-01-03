package tddfinance.day;

//import static org.junit.Assert.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 *
 * According to Wikipedia, this convention is used for US corporate bonds and many US agency issues
 */
public class DayCount30360USTest {

	@Test
	public void testNumberOfDays() throws Exception {
		//Start = 1 ~ 27, End = 1 ~ 27
		assertEquals( 12,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1,  3 ), new LocalDate( 2001,  1, 15 ) ) ); // Within the same month
		assertEquals( 48,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 15 ), new LocalDate( 2001,  3,  3 ) ) ); // Passes Feb 28
		assertEquals( 48,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 15 ), new LocalDate( 2000,  3,  3 ) ) ); // Passes Feb 29 
		assertEquals( 23,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 10 ), new LocalDate( 2001,  5,  3 ) ) ); // Passes 30 (month-end) 
		assertEquals( 18,  DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4,  8 ) ) ); // Passes 31 
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 30 (month-end), 31 
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31 
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30 (month-end), 31 
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31 
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30 (month-end), 31 
		//Start = 1 ~ 27,  End = Feb 28
		assertEquals(  8,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Within the same month 
		assertEquals( 38,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31 
		assertEquals( 68,  DayCount30360US.numberOfDays( new LocalDate( 2000, 12, 20 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31 
		//Start = 1 ~ 27,  End = Feb 29
		assertEquals(  9,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Within the same month 
		assertEquals( 39,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31 
		assertEquals( 69,  DayCount30360US.numberOfDays( new LocalDate( 1999, 12, 20 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 1 ~ 27,  End = 30 (Month End)
		assertEquals( 10,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Within the same month
		assertEquals( 40,  DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 70,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31
		assertEquals( 70,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 130, DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 70,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 130, DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = 1 ~ 27,  End = 31
		assertEquals( 11,  DayCount30360US.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Within the same month
		assertEquals( 41,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 41,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 41,  DayCount30360US.numberOfDays( new LocalDate( 2001,  6, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30
		assertEquals( 41,  DayCount30360US.numberOfDays( new LocalDate( 2001,  7, 20 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31
		assertEquals( 71,  DayCount30360US.numberOfDays( new LocalDate( 2001,  5, 20 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 71,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 20 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31
		assertEquals( 101, DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 20 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 71,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 20 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31
		assertEquals( 101, DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 20 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 1 ~ 27
		assertEquals( 22,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 22,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29 
		assertEquals( 52,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes 31
		assertEquals( 82,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30, 31
		assertEquals( 52,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 82,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = Feb 29
		assertEquals( 1,   DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  2, 29 ) ) ); 
		//Start = Feb 28, End = 30 (Month End)
		assertEquals( 62,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes 31
		assertEquals( 122, DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 62,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 122, DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 28, End = 31
		assertEquals( 33,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  3, 31 ) ) ); 
		assertEquals( 33,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  3, 31 ) ) ); 
		assertEquals( 93,  DayCount30360US.numberOfDays( new LocalDate( 2001,  2, 28 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30, 31
		assertEquals( 93,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 28 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
		//Start = Feb 29, End = 1 ~ 27
		assertEquals( 21,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  3, 20 ) ) ); // Next month but doesn't end in month-end 
		assertEquals( 51,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes 31
		assertEquals( 81,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes 30, 31
		//Start = Feb 29, End = 30
		assertEquals( 61,  DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes 31 
		assertEquals( 121, DayCount30360US.numberOfDays( new LocalDate( 2000,  2, 29 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes 30, 31
		//Start = 30, End = 1 ~ 27
		assertEquals( 20,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 50,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  6, 20 ) ) ); // Passes 31
		assertEquals( 110, DayCount30360US.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 170, DayCount30360US.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 110, DayCount30360US.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 170, DayCount30360US.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 30, End = Feb 28
		assertEquals( 88,  DayCount30360US.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 148, DayCount30360US.numberOfDays( new LocalDate( 2000,  9, 30 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 30, End = Feb 29
		assertEquals( 89,  DayCount30360US.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 31
		assertEquals( 149, DayCount30360US.numberOfDays( new LocalDate( 1999,  9, 30 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes Feb 28, 30, 31
		//Start = 30, End = 31
		assertEquals( 30,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  5, 31 ) ) ); 
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2001,  6, 30 ), new LocalDate( 2001,  8, 31 ) ) ); // Passes 31 
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 2001,  4, 30 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31 
		assertEquals( 120, DayCount30360US.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 180, DayCount30360US.numberOfDays( new LocalDate( 2000, 11, 30 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 120, DayCount30360US.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 180, DayCount30360US.numberOfDays( new LocalDate( 1999, 11, 30 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 1 ~ 27
		assertEquals( 20,  DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Next month but doesn't end in month-end  
		assertEquals( 50,  DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes 30
		assertEquals( 50,  DayCount30360US.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 20 ) ) ); // Passes 31
		assertEquals( 50,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 20 ) ) ); // Passes Feb 28
		assertEquals( 80,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 20 ) ) ); // Passes Feb 28, 31
		assertEquals( 110, DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 20 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 50,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 20 ) ) ); // Passes Feb 29
		assertEquals( 80,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 20 ) ) ); // Passes Feb 29, 31
		assertEquals( 110, DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 20 ) ) ); // Passes Feb 29, 30, 31
		//Start = 31, End = Feb 28
		assertEquals( 28,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  2, 28 ) ) ); 
		assertEquals( 58,  DayCount30360US.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 31
		assertEquals( 118, DayCount30360US.numberOfDays( new LocalDate( 2000, 10, 31 ), new LocalDate( 2001,  2, 28 ) ) ); // Passes 30, 31
		//Start = 31, End = Feb 29
		assertEquals( 29,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  2, 29 ) ) ); 
		assertEquals( 59,  DayCount30360US.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 31
		assertEquals( 119, DayCount30360US.numberOfDays( new LocalDate( 1999, 10, 31 ), new LocalDate( 2000,  2, 29 ) ) ); // Passes 30, 31
		//Start = 31, End = 30
		assertEquals( 30,  DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  4, 30 ) ) );
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  9, 30 ) ) );
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes 30, 31 
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  4, 30 ) ) ); // Passes Feb 28, 31
		assertEquals( 150, DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  6, 30 ) ) ); // Passes Feb 28, 30, 31 
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  4, 30 ) ) ); // Passes Feb 29, 31
		assertEquals( 150, DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  6, 30 ) ) ); // Passes Feb 29, 30, 31 
		//Start = 31, End = 31
		assertEquals( 30,  DayCount30360US.numberOfDays( new LocalDate( 2001,  7, 31 ), new LocalDate( 2001,  8, 31 ) ) ); 
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes 30
		assertEquals( 120, DayCount30360US.numberOfDays( new LocalDate( 2001,  3, 31 ), new LocalDate( 2001,  7, 31 ) ) ); // Passes 30, 31
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28
		assertEquals( 60,  DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 2000, 12, 31 ), new LocalDate( 2001,  3, 31 ) ) ); // Passes Feb 28, 31 
		assertEquals( 120, DayCount30360US.numberOfDays( new LocalDate( 2001,  1, 31 ), new LocalDate( 2001,  5, 31 ) ) ); // Passes Feb 28, 30, 31
		assertEquals( 90,  DayCount30360US.numberOfDays( new LocalDate( 1999, 12, 31 ), new LocalDate( 2000,  3, 31 ) ) ); // Passes Feb 29, 31 
		assertEquals( 120, DayCount30360US.numberOfDays( new LocalDate( 2000,  1, 31 ), new LocalDate( 2000,  5, 31 ) ) ); // Passes Feb 29, 30, 31
	}
	
	@Test
	public void testKnownAs() throws Exception {
		assertEquals( 
			Arrays.asList( "30/360 US", "30U/360", "Bond basis", "360/360" ), 
			new DayCount30360US().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCount30360US dc30360US = new DayCount30360US();
		dc30360US.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "30/360 US", "30U/360", "Bond basis", "360/360" ), dc30360US.knownAs() );		
	}
}
