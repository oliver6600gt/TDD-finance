package tddfinance.calculator;

import tddfinance.contract.Contract;

public interface Pricer {
	double price( Contract contract ) throws Exception;
}
