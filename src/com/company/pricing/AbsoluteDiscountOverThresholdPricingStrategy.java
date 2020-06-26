package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;

public class AbsoluteDiscountOverThresholdPricingStrategy  implements ISalePricingStrategy{
	Money discount = new Money(50);
	Money threshold = new Money(500);
	
	@Override
	public Money getTotal(Sale sale) {
		Money pdt = sale.getPreDiscountTotal();
		// Checking if threshold value is bigger than prediscount total
		int x = pdt.minus(threshold).getAmount().intValue();
		if(x<0) {
			return pdt;
		}
		else {	
			return pdt.minus(discount);
		}		
	}


	public AbsoluteDiscountOverThresholdPricingStrategy() {
		super();
	}

	public String getStrategyName(){
		return this.getClass().toString();
	}
}

