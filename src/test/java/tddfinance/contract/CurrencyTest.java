package tddfinance.contract;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CurrencyTest {
	@Test
	public void equalityTest() throws Exception {
		assertEquals(Currency.USD, Currency.USD);
		assertThat(Currency.USD, is(not(Currency.JPY)));
	}
}
