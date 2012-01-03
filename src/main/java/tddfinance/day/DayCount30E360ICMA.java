package tddfinance.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

public class DayCount30E360ICMA implements DayCountConvention {

	private static final List<String> knownAs =  Arrays.asList( "30/360 ICMA", "30E/360", "Eurobond basis", "Special German" );
	
	public List<String> knownAs() {
		return new ArrayList<String>( DayCount30E360ICMA.knownAs );
	}

	public double fraction(
		LocalDate accrualStartDate,
		LocalDate accrualEndDate, 
		LocalDate nextPaymentSettleDate,
		int       paymentFrequency) {
			
		throw new UnsupportedOperationException( "method not implemented" );

	}
}
