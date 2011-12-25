package tddfinance.contract;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	BondTest.class, 
	CashflowSetTest.class,
	CashflowTest.class,
	CashTest.class, 
	ContractAddTest.class, 
	ContractGetTest.class,
	ContractScaleTest.class, 
	CurrencyTest.class, 
	ZeroCouponTest.class })
public class AllTests {

	public static void main(String[] args) {
		JUnitCore.main(AllTests.class.getName());
	}

}
