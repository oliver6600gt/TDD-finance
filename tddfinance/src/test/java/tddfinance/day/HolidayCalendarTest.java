package tddfinance.day;

import static org.junit.Assert.*;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Test;


public class HolidayCalendarTest {
	HolidayCalendar defaultCalendar = new HolidayCalendar();
	
	@Test
	public void isHolidayTest() throws Exception {
		LocalDate date = new LocalDate(2000, 1, 1);
		while( !date.isAfter(new LocalDate(2010, 1, 1)) ){
			assertFalse( defaultCalendar.isHoliday( date ) );
			date = date.plusDays(1);
		}
	}
	
	@Test
	public void isBusinessDayTest() throws Exception {
		LocalDate date = new LocalDate(2000, 1, 1);
		while( !date.isAfter(new LocalDate(2010, 1, 1)) ){
			switch (date.getDayOfWeek()) {
			case DateTimeConstants.SATURDAY:
			case DateTimeConstants.SUNDAY:
				assertFalse( defaultCalendar.isBusinessDay( date ) );
				break;
			default:
				assertTrue( defaultCalendar.isBusinessDay( date ) );
			}
			
			date = date.plusDays(1);
		}
	}
	
	@Test
	public void nextBusinessDayTest() throws Exception {
		assertEquals( new LocalDate(2000, 1, 4),  defaultCalendar.nextBusinessDay( new LocalDate(2000, 1, 3)) );
		assertEquals( new LocalDate(2000, 1, 10), defaultCalendar.nextBusinessDay( new LocalDate(2000, 1, 7)) );
		assertEquals( new LocalDate(2000, 1, 10), defaultCalendar.nextBusinessDay( new LocalDate(2000, 1, 8)) );
		assertEquals( new LocalDate(2000, 1, 10), defaultCalendar.nextBusinessDay( new LocalDate(2000, 1, 9)) );
	}

	@Test
	public void previousBusinessDayTest() throws Exception {
		assertEquals( new LocalDate(1999, 12, 31), defaultCalendar.previousBusinessDay( new LocalDate(2000, 1, 1)) );
		assertEquals( new LocalDate(1999, 12, 31), defaultCalendar.previousBusinessDay( new LocalDate(2000, 1, 2)) );
		assertEquals( new LocalDate(1999, 12, 31), defaultCalendar.previousBusinessDay( new LocalDate(2000, 1, 3)) );
		assertEquals( new LocalDate(2000, 1,   3), defaultCalendar.previousBusinessDay( new LocalDate(2000, 1, 4)) );
	}
}
