package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

import java.util.Iterator;

public class CompositeBestForStorePricingStrategy extends CompositePricingStrategy {
	//Selecting maximum total price in strategy list
	@Override
    public Money getTotal(Sale sale){
        Money maxTotal = new Money(Integer.MIN_VALUE);

        for(Iterator i = strategies.iterator(); i.hasNext();){
            ISalePricingStrategy strategy =
                    (ISalePricingStrategy)i.next();
            Money total = strategy.getTotal(sale);
            maxTotal = total.max(maxTotal);
        }
        return maxTotal;
    }

    public String getStrategyName(){
        return this.getClass().toString();
    }
}
