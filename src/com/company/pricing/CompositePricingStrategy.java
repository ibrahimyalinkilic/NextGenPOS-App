package com.company.pricing;

import java.util.ArrayList;
import java.util.List;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public abstract class CompositePricingStrategy implements ISalePricingStrategy {
	protected List<ISalePricingStrategy> strategies = new ArrayList<ISalePricingStrategy>();
	
	public void add(ISalePricingStrategy strategy) {
		strategies.add(strategy);
	}
	public abstract Money getTotal(Sale sale);
		
}
