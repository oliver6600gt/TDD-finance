package tddfinance.day;

import org.joda.time.LocalDate;
import org.junit.Test;

public class DayCountActual360Test {

	@Test(expected = Exception.class)
	public void test() throws Exception{
		DayCount.fraction(DayCount.DC_ACTUAL360, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), Compounding.ANNUAL );  
	}


}
