package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;
import com.company.domain.SalesLineItem;

import java.util.List;

public class PercentageDiscountPricingStrategy implements ISalePricingStrategy {
	private float percentage;
	private float itemDiscount;

	public PercentageDiscountPricingStrategy(float percentage) {
		super();
		this.percentage = percentage;
		//loadProdSpecs();
	}
	
	@Override
	public Money getTotal(Sale sale) {
		//We are checking items to see if they have special discount on them
		List<SalesLineItem> lineItems =  sale.getLineItems();
		for(SalesLineItem item : lineItems){
			itemDiscount = item.getDescription().getDiscountRate();
			item.setSubTotal(item.getSubTotal().minus(item.getSubTotal().times(itemDiscount/100)));
		}
		//Here we are calculating and returning pre-decided discount
		Money pdt = sale.getPreDiscountTotal();
		percentage = percentage/100;
		return pdt.minus(pdt.times(percentage));
	}

	public String getStrategyName(){
		return this.getClass().toString();
	}

}
