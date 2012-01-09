package tddfinance.day;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * 
 * 
 */
public class DayCountActualActualISDATest {

	@Test
	public void testNumberOfDaysInNonLeapYear() throws Exception {
		//start is before a leap year
		assertEquals(   30, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 1999,  12,  10 ) ) ); //start = before a leap year, end = before a leap year  
		assertEquals(   51, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,   1,   1 ) ) ); //start = before a leap year, end = beginning of a leap year 
		assertEquals(   51, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,   4,  10 ) ) ); //start = before a leap year, end = middle of a leap year
		assertEquals(   51, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,  12,  31 ) ) ); //start = before a leap year, end = end of a leap year
		assertEquals(  516, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2002,   4,  10 ) ) ); //start = before a leap year, end = a day between a leap year
		assertEquals( 1146, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,   1,   1 ) ) ); //start = before a leap year, end = beginning of another leap year
		assertEquals( 1146, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,   4,  10 ) ) ); //start = before a leap year, end = middle of another leap year
		assertEquals( 1146, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,  12,  31 ) ) ); //start = before a leap year, end = end of another leap year
		assertEquals( 1246, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2005,   4,  10 ) ) ); //start = before a leap year, end = a day after the two leap years

		//start = beginning of a leap year
		assertEquals(    0, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,   1,   1 ) ) ); //start = beginning of a leap year, end = beginning of a leap year 
		assertEquals(    0, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,   4,  10 ) ) ); //start = beginning of a leap year, end = middle of a leap year
		assertEquals(    0, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,  12,  31 ) ) ); //start = beginning of a leap year, end = end of a leap year
		assertEquals(  465, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2002,   4,  10 ) ) ); //start = beginning of a leap year, end = a day between a leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,   1,   1 ) ) ); //start = beginning of a leap year, end = beginning of another leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,   4,  10 ) ) ); //start = beginning of a leap year, end = middle of another leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,  12,  31 ) ) ); //start = beginning of a leap year, end = end of another leap year
		assertEquals( 1195, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2005,   4,  10 ) ) ); //start = beginning of a leap year, end = a day after the two leap years

		//start = middle of a leap year
		assertEquals(    0, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2000,   4,  10 ) ) ); //start = middle of a leap year, end = middle of a leap year
		assertEquals(    0, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2000,  12,  31 ) ) ); //start = middle of a leap year, end = end of a leap year
		assertEquals(  465, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2002,   4,  10 ) ) ); //start = middle of a leap year, end = a day between a leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,   1,   1 ) ) ); //start = middle of a leap year, end = beginning of another leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,   4,  10 ) ) ); //start = middle of a leap year, end = middle of another leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,  12,  31 ) ) ); //start = middle of a leap year, end = end of another leap year
		assertEquals( 1195, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2005,   4,  10 ) ) ); //start = middle of a leap year, end = a day after the two leap years
		                                                                                              
		//start = end of a leap year
		assertEquals(  465, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2002,   4,  10 ) ) ); //start = end of a leap year, end = a day between a leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,   1,   1 ) ) ); //start = end of a leap year, end = beginning of another leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,   4,  10 ) ) ); //start = end of a leap year, end = middle of another leap year
		assertEquals( 1095, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,  12,  31 ) ) ); //start = end of a leap year, end = end of another leap year
		assertEquals( 1195, DayCountActualActualISDA.numberOfDaysInNonLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2005,   4,  10 ) ) ); //start = end of a leap year, end = a day after the two leap years
	}
	
	@Test
	public void testNumberOfDaysInLeapYear() throws Exception {
		//start is before a leap year
		assertEquals(   0, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 1999,  12,  10 ) ) ); //start = before a leap year, end = before a leap year  
		assertEquals(   1, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,   1,   1 ) ) ); //start = before a leap year, end = beginning of a leap year 
		assertEquals( 101, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,   4,  10 ) ) ); //start = before a leap year, end = middle of a leap year
		assertEquals( 366, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,  12,  31 ) ) ); //start = before a leap year, end = end of a leap year
		assertEquals( 366, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2002,   4,  10 ) ) ); //start = before a leap year, end = a day between a leap year
		assertEquals( 367, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,   1,   1 ) ) ); //start = before a leap year, end = beginning of another leap year
		assertEquals( 467, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,   4,  10 ) ) ); //start = before a leap year, end = middle of another leap year
		assertEquals( 732, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,  12,  31 ) ) ); //start = before a leap year, end = end of another leap year
		assertEquals( 732, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 1999,  11, 10 ), new LocalDate( 2005,   4,  10 ) ) ); //start = before a leap year, end = a day after the two leap years

		//start = beginning of a leap year
		assertEquals(   0, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,   1,   1 ) ) ); //start = beginning of a leap year, end = beginning of a leap year 
		assertEquals( 100, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,   4,  10 ) ) ); //start = beginning of a leap year, end = middle of a leap year
		assertEquals( 365, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,  12,  31 ) ) ); //start = beginning of a leap year, end = end of a leap year
		assertEquals( 365, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2002,   4,  10 ) ) ); //start = beginning of a leap year, end = a day between a leap year
		assertEquals( 366, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,   1,   1 ) ) ); //start = beginning of a leap year, end = beginning of another leap year
		assertEquals( 466, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,   4,  10 ) ) ); //start = beginning of a leap year, end = middle of another leap year
		assertEquals( 731, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,  12,  31 ) ) ); //start = beginning of a leap year, end = end of another leap year
		assertEquals( 731, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   1,  1 ), new LocalDate( 2005,   4,  10 ) ) ); //start = beginning of a leap year, end = a day after the two leap years

		//start = middle of a leap year
		assertEquals(  31, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2000,   4,  10 ) ) ); //start = middle of a leap year, end = middle of a leap year
		assertEquals( 296, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2000,  12,  31 ) ) ); //start = middle of a leap year, end = end of a leap year
		assertEquals( 296, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2002,   4,  10 ) ) ); //start = middle of a leap year, end = a day between a leap year
		assertEquals( 297, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,   1,   1 ) ) ); //start = middle of a leap year, end = beginning of another leap year
		assertEquals( 397, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,   4,  10 ) ) ); //start = middle of a leap year, end = middle of another leap year
		assertEquals( 662, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,  12,  31 ) ) ); //start = middle of a leap year, end = end of another leap year
		assertEquals( 662, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,   3, 10 ), new LocalDate( 2005,   4,  10 ) ) ); //start = middle of a leap year, end = a day after the two leap years
		                                                                                              
		//start = end of a leap year
		assertEquals(   0, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2002,   4,  10 ) ) ); //start = end of a leap year, end = a day between a leap year
		assertEquals(   1, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,   1,   1 ) ) ); //start = end of a leap year, end = beginning of another leap year
		assertEquals( 101, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,   4,  10 ) ) ); //start = end of a leap year, end = middle of another leap year
		assertEquals( 366, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,  12,  31 ) ) ); //start = end of a leap year, end = end of another leap year
		assertEquals( 366, DayCountActualActualISDA.numberOfDaysInLeapYear( new LocalDate( 2000,  12, 31 ), new LocalDate( 2005,   4,  10 ) ) ); //start = end of a leap year, end = a day after the two leap years
	}
	
	@Test
	public void testFraction() throws Exception {
		//start is before a leap year
		assertEquals( 0.082191781, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 1999,  12,  10 ) ), 1.0e-6 ); //start = before a leap year, end = before a leap year  
		assertEquals( 0.142458268, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,   1,   1 ) ), 1.0e-6 ); //start = before a leap year, end = beginning of a leap year 
		assertEquals( 0.415682312, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,   4,  10 ) ), 1.0e-6 ); //start = before a leap year, end = middle of a leap year
		assertEquals( 1.139726027, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2000,  12,  31 ) ), 1.0e-6 ); //start = before a leap year, end = end of a leap year
		assertEquals(  2.41369863, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2002,   4,  10 ) ), 1.0e-6 ); //start = before a leap year, end = a day between a leap year
		assertEquals( 4.142458268, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,   1,   1 ) ), 1.0e-6 ); //start = before a leap year, end = beginning of another leap year
		assertEquals( 4.415682312, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,   4,  10 ) ), 1.0e-6 ); //start = before a leap year, end = middle of another leap year
		assertEquals( 5.139726027, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2004,  12,  31 ) ), 1.0e-6 ); //start = before a leap year, end = end of another leap year
		assertEquals(  5.41369863, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 1999,  11, 10 ), new LocalDate( 2005,   4,  10 ) ), 1.0e-6 ); //start = before a leap year, end = a day after the two leap years

		//start = beginning of a leap year
		assertEquals(           0, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,   1,   1 ) ), 1.0e-6 ); //start = beginning of a leap year, end = beginning of a leap year 
		assertEquals( 0.273224044, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,   4,  10 ) ), 1.0e-6 ); //start = beginning of a leap year, end = middle of a leap year
		assertEquals(  0.99726776, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2000,  12,  31 ) ), 1.0e-6 ); //start = beginning of a leap year, end = end of a leap year
		assertEquals( 2.271240362, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2002,   4,  10 ) ), 1.0e-6 ); //start = beginning of a leap year, end = a day between a leap year
		assertEquals(           4, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,   1,   1 ) ), 1.0e-6 ); //start = beginning of a leap year, end = beginning of another leap year
		assertEquals( 4.273224044, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,   4,  10 ) ), 1.0e-6 ); //start = beginning of a leap year, end = middle of another leap year
		assertEquals(  4.99726776, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2004,  12,  31 ) ), 1.0e-6 ); //start = beginning of a leap year, end = end of another leap year
		assertEquals( 5.271240362, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   1,  1 ), new LocalDate( 2005,   4,  10 ) ), 1.0e-6 ); //start = beginning of a leap year, end = a day after the two leap years

		//start = middle of a leap year
		assertEquals( 0.084699454, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   3, 10 ), new LocalDate( 2000,   4,  10 ) ), 1.0e-6 ); //start = middle of a leap year, end = middle of a leap year
		assertEquals( 0.808743169, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   3, 10 ), new LocalDate( 2000,  12,  31 ) ), 1.0e-6 ); //start = middle of a leap year, end = end of a leap year
		assertEquals( 2.082715772, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   3, 10 ), new LocalDate( 2002,   4,  10 ) ), 1.0e-6 ); //start = middle of a leap year, end = a day between a leap year
		assertEquals(  3.81147541, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,   1,   1 ) ), 1.0e-6 ); //start = middle of a leap year, end = beginning of another leap year
		assertEquals( 4.084699454, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,   4,  10 ) ), 1.0e-6 ); //start = middle of a leap year, end = middle of another leap year
		assertEquals( 4.808743169, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   3, 10 ), new LocalDate( 2004,  12,  31 ) ), 1.0e-6 ); //start = middle of a leap year, end = end of another leap year
		assertEquals( 5.082715772, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,   3, 10 ), new LocalDate( 2005,   4,  10 ) ), 1.0e-6 ); //start = middle of a leap year, end = a day after the two leap years
		                                                                                   
		//start = end of a leap year
		assertEquals( 1.273972603, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,  12, 31 ), new LocalDate( 2002,   4,  10 ) ), 1.0e-6 ); //start = end of a leap year, end = a day between a leap year
		assertEquals(  3.00273224, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,   1,   1 ) ), 1.0e-6 ); //start = end of a leap year, end = beginning of another leap year
		assertEquals( 3.275956284, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,   4,  10 ) ), 1.0e-6 ); //start = end of a leap year, end = middle of another leap year
		assertEquals(           4, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,  12, 31 ), new LocalDate( 2004,  12,  31 ) ), 1.0e-6 ); //start = end of a leap year, end = end of another leap year
		assertEquals( 4.273972603, DayCount.fraction( DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate( 2000,  12, 31 ), new LocalDate( 2005,   4,  10 ) ), 1.0e-6 ); //start = end of a leap year, end = a day after the two leap years
	}

	@Test
	public void testKnownAs() throws Exception {
		assertEquals( Arrays.asList( "Actual/Actual ISDA", "Actual/Actual", "Act/Act", "Actual/365", "Act/365" ), new DayCountActualActualISDA().knownAs() );
	}

	@Test
	public void testKnownAsImmutable() throws Exception {
		DayCountActualActualISDA dcActActISDA = new DayCountActualActualISDA();
		dcActActISDA.knownAs().add("Invalid Alias");

		assertEquals( Arrays.asList( "Actual/Actual ISDA", "Actual/Actual", "Act/Act", "Actual/365", "Act/365" ), dcActActISDA.knownAs() );		
	}	
}
