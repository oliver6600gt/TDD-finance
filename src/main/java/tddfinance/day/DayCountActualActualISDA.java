package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

public class DayCountActualActualISDA implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "Actual/Actual ISDA", "Actual/Actual", "Act/Act", "Actual/365", "Act/365" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCountActualActualISDA.knownAs );
	}
	
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
					
		throw new UnsupportedOperationException( "method not implemented" );

	}

}
