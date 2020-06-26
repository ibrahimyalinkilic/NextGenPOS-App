package com.company.pricing;

import java.util.Iterator;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public class CompositeBestForCustomerPricingStrategy extends CompositePricingStrategy {
	//Selecting minimum total price in strategy list
	@Override
	public Money getTotal(Sale sale){
        Money lowestTotal = new Money(Integer.MAX_VALUE);

        for(Iterator i = strategies.iterator();i.hasNext();){
            ISalePricingStrategy strategy =
                    (ISalePricingStrategy)i.next();
            Money total = strategy.getTotal(sale);
            lowestTotal = total.min(lowestTotal);
        }
        return lowestTotal;
    }

    public String getStrategyName(){
        return this.getClass().toString();
    }

}
