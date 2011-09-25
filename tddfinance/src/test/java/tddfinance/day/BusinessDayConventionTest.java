package tddfinance.day;

import static org.junit.Assert.*;
import org.junit.Test;
import org.joda.time.LocalDate;

public class BusinessDayConventionTest {
	HolidayCalendar defaultCalendar = new HolidayCalendar();

	@Test
	public void unadjustedTest() throws Exception {
		assertEquals( new LocalDate(2000, 1, 1), BDC.adjust(new LocalDate(2000, 1, 1), BDC.UNADJUSTED) );
		assertEquals( new LocalDate(2000, 1, 1), BDC.adjust(new LocalDate(2000, 1, 1), BDC.UNADJUSTED, defaultCalendar) );
	}

	@Test
	public void follwingTest() throws Exception {
		assertEquals( new LocalDate(1999,12,31), BDC.adjust(new LocalDate(1999,12,31), BDC.FOLLOWING) );
		assertEquals( new LocalDate(2000, 1, 3), BDC.adjust(new LocalDate(2000, 1, 1), BDC.FOLLOWING) );
		assertEquals( new LocalDate(2000, 1, 3), BDC.adjust(new LocalDate(2000, 1, 2), BDC.FOLLOWING, defaultCalendar) );
		assertEquals( new LocalDate(2000, 1, 3), BDC.adjust(new LocalDate(2000, 1, 3), BDC.FOLLOWING, defaultCalendar) );
		assertEquals( new LocalDate(2000,10, 2), BDC.adjust(new LocalDate(2000, 9,30), BDC.FOLLOWING, defaultCalendar) );
	}

	@Test
	public void previousTest() throws Exception {
		assertEquals( new LocalDate(1999,12,31), BDC.adjust(new LocalDate(1999,12,31), BDC.PREVIOUS) );
		assertEquals( new LocalDate(1999,12,31), BDC.adjust(new LocalDate(2000, 1, 1), BDC.PREVIOUS) );
		assertEquals( new LocalDate(1999,12,31), BDC.adjust(new LocalDate(2000, 1, 1), BDC.PREVIOUS) );
		assertEquals( new LocalDate(2000, 1, 3), BDC.adjust(new LocalDate(2000, 1, 3), BDC.PREVIOUS, defaultCalendar) );
	}

	@Test
	public void modifiedFollwingTest() throws Exception {
		assertEquals( new LocalDate(1999,12,31), BDC.adjust(new LocalDate(1999,12,31), BDC.MODIFIED_FOLLOWING) );
		assertEquals( new LocalDate(2000, 1, 3), BDC.adjust(new LocalDate(2000, 1, 1), BDC.MODIFIED_FOLLOWING) );
		assertEquals( new LocalDate(2000, 1, 3), BDC.adjust(new LocalDate(2000, 1, 2), BDC.MODIFIED_FOLLOWING, defaultCalendar) );
		assertEquals( new LocalDate(2000, 1, 3), BDC.adjust(new LocalDate(2000, 1, 3), BDC.MODIFIED_FOLLOWING, defaultCalendar) );
		assertEquals( new LocalDate(2000, 9,29), BDC.adjust(new LocalDate(2000, 9,30), BDC.MODIFIED_FOLLOWING, defaultCalendar) );
	}
}
