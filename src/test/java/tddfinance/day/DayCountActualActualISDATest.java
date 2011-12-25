package tddfinance.day;

//import static org.junit.Assert.*;
import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * 
 * 
 */
public class DayCountActualActualISDATest {

	@Test(expected = Exception.class)
	public void test() throws Exception{
		DayCount.fraction(DayCount.DC_ACTUAL_ACTUAL_ISDA, new LocalDate(2000, 1, 1), new LocalDate(2000, 2, 1), Compounding.ANNUAL );  
	}
}
