package tddfinance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	tddfinance.contract.AllTests.class,
	tddfinance.curve.AllTests.class,
	tddfinance.day.AllTests.class,
	tddfinance.calculator.AllTests.class,
	tddfinance.trade.AllTests.class })
public class AllTests {

}
