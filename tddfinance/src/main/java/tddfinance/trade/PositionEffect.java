package tddfinance.trade;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import tddfinance.contract.Contract;
import tddfinance.contract.ContractZero;

public final class PositionEffect {
	public static final PositionEffect ZERO = new PositionEffect();
	
	private Map<Contract, Double> positionMap;
	
	private PositionEffect(){
		positionMap = new HashMap<Contract, Double>();
	}
	
	/**
	 * Construct a PositionEffect object from two arrays contracts and quantities. <br>
	 * (e.g.) new PositionEffect(new Contract[]{Cash.EUR, Cash.USD}, new double[]{100, 200})
	 * 
	 * @param contracts:  an array of Contarct objects, whose array length must be same as that of the other parameter, quantities
	 * @param quantities: an array of double numbers, whose array length must be same as that of the other parameter, contracts
	 * @throws Exception If invalid contracts and/or quantities which cannot be parsed to position effect, then throw an Exception
	 * 
	 */
	//FixMe: if it does not throw an exception, you don't need "throws Exception" in callers 
	public PositionEffect( Contract[] contracts, double[] quantities) throws Exception{
		if( contracts.length != quantities.length )
			throw new Exception(
				String.format( "Invalid parameters: contracts.length = %d, quantitles.length = %d but they need to be the same length", contracts.length, quantities.length )
			);
		
		positionMap = new HashMap<Contract, Double>();
		for (int i = 0; i < contracts.length; i++){
			Contract contract = contracts[i].unitContract();
			double   quantity = contracts[i].scaleFactor() * quantities[i];

			if( contract.equals(new ContractZero()) )
				continue;
			else if( positionMap.containsKey(contracts[i]) )
				positionMap.put(contract, positionMap.get(contract) + quantity);
			else
				positionMap.put(contract, quantity);
		}
	}

	/**
	 * Construct a PositionEffect object from a single array of input.<br>
	 * (e.g.) new PositionEffect(Cash.EUR, 100, Cash.USD, 100)
	 * 
	 * @param parameters: The size of parameters should be a multiple of 2, and the order should be {Contract, number, Contract, number, ...}
	 * where a number is either in Double or Integer
	 * 
	 * @throws Exception If invalid parameters which cannot be parsed to position effect, then throw an Exception
	 * 
	 */
	public PositionEffect(Object... parameters) throws Exception{
		if( parameters.length % 2 != 0)
			throw new Exception( String.format( "Invalid parameters: parameters = %d is not a multiple of 2", parameters.length )	);
		
		positionMap = new HashMap<Contract, Double>();
		for (int i = 0; i < parameters.length; i +=2 ){
			if( !( parameters[i] instanceof Contract ))
				throw new Exception( String.format("Invalid parameters: parameter no. %d = %s is not a type of Contract", i, parameters[i]));

			Contract contract     = (Contract) parameters[i];
			Contract unitContract = contract.unitContract();

			double   quantity;
			if( parameters[i+1] instanceof Double )
				quantity = contract.scaleFactor() * (Double)parameters[i+1];
			else if ( parameters[i+1] instanceof Integer )
				quantity = contract.scaleFactor() * (Integer)parameters[i+1];
			else
				throw new Exception( String.format("Invalid parameters: parameter no. %d = %s is not a numeric value (must be Double or Integer)", i+1, parameters[i+1]));
			
			if( contract.equals(new ContractZero()) )
				continue;
			else if( positionMap.containsKey(unitContract) )
				positionMap.put(unitContract, positionMap.get(unitContract) + quantity);
			else
				positionMap.put(unitContract, quantity);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if( obj instanceof PositionEffect ){
			PositionEffect theOther = (PositionEffect) obj;
			return positionMap.equals(theOther.positionMap);
		}
		else
			return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Position Effect:\n" + positionMap.toString();
		
	}

	//as the parameter "theOther" is already in PositionEffect, we don't need to think about factor() and unitContract() of theOther
	public PositionEffect add(PositionEffect theOther) {
		PositionEffect newEffect = new PositionEffect();
		newEffect.positionMap.putAll(this.positionMap);
		
		for (Entry<Contract, Double> posEntry : theOther.positionMap.entrySet()) {
			if( newEffect.positionMap.containsKey(posEntry.getKey()) )
				newEffect.positionMap.put(posEntry.getKey(), newEffect.positionMap.get(posEntry.getKey()) + posEntry.getValue());
			else
				newEffect.positionMap.put(posEntry.getKey(), posEntry.getValue());
		}
		
		return newEffect;
	}
	
	public PositionEffect scale(double factor) {
		PositionEffect newEffect = new PositionEffect();

		for (Entry<Contract, Double> posEntry : positionMap.entrySet())
			newEffect.positionMap.put(posEntry.getKey(), posEntry.getValue() * factor);
		
		return newEffect;
	}
}
