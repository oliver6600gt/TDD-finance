package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

public class DayCountActualActualICMA implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "Actual/Actual ICMA", "Actual/Actual", "Act/Act ICMA", "ISMA-99", "Act/Act ISMA" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCountActualActualICMA.knownAs );
	}
	
	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
					
		throw new UnsupportedOperationException( "method not implemented" );

	}

}
