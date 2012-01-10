package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

public class DayCountActual365L implements DayCountConvention {
	
	private static final List<String> knownAs =  Arrays.asList( "Actual/365L", "ISMA-Year" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCountActual365L.knownAs );
	}
	
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
					
		throw new UnsupportedOperationException( "method not implemented" );

	}
}
