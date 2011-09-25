package tddfinance.pricer;

import tddfinance.contract.Contract;

public interface Pricer {
	double price( Contract contract ) throws Exception;
}
