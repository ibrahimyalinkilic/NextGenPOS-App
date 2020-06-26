package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public class SeniorPricingStrategy   implements ISalePricingStrategy{
    float percentage;

    public SeniorPricingStrategy(float percentage) {
        super();
        this.percentage = percentage;
    }
    
    // Calculating discount for senior customers
    @Override
    public Money getTotal(Sale sale) {
        Money pdt = sale.getPreDiscountTotal();
        percentage = percentage/100;
        return pdt.minus(pdt.times(percentage));
    }

    public String getStrategyName(){
        return this.getClass().toString();
    }
}
