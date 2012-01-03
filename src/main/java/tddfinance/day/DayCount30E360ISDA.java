package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

public class DayCount30E360ISDA implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "30E/360 ISDA", "German" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCount30E360ISDA.knownAs );
	}

	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
				
		throw new UnsupportedOperationException( "method not implemented" );

	}
}
