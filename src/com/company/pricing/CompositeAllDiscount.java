package com.company.pricing;


import com.company.customTypes.Money;
import com.company.domain.Sale;

import java.util.Iterator;

public class CompositeAllDiscount extends CompositePricingStrategy {
	// Calculating all discounts
    public Money getTotal(Sale sale){
        Money total = null;
        for(Iterator i = strategies.iterator(); i.hasNext();){
            ISalePricingStrategy strategy =
                    (ISalePricingStrategy)i.next();
            total = strategy.getTotal(sale);
            sale.setTotal(total);
        }
        return total;
    }

    public String getStrategyName(){
        return this.getClass().toString();
    }

}
