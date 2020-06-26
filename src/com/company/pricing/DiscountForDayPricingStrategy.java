package com.company.pricing;

import com.company.customTypes.Money;
import com.company.domain.Sale;
import com.company.fileOperations.DiscountDayFileOperator;

import java.util.HashMap;

public class DiscountForDayPricingStrategy implements ISalePricingStrategy {
    private float percentage;
    private HashMap discountDays;

    DiscountDayFileOperator dfdfo = new DiscountDayFileOperator("DiscountDays.txt");

    public DiscountForDayPricingStrategy() {
        super();
        loadProdSpecs();
    }

    public void loadProdSpecs(){
        dfdfo.readFromFile();
        discountDays = dfdfo.getDiscountDays();
    }

    //Checking if day has a special discount and calculate total
    public Money getTotal(Sale sale){
        Money pdt = sale.getPreDiscountTotal();
        if(discountDays.containsKey(sale.getDay())) {
            String str = (String) (discountDays.get(sale.getDay()));
            percentage = Float.parseFloat(str);
            percentage = percentage/100;
            return pdt.minus(pdt.times(percentage));
        }
        else return pdt;
    }

    public String getStrategyName(){
        return this.getClass().toString();
    }
}
