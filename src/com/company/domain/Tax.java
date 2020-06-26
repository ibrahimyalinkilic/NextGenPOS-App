package com.company.domain;

import com.company.customTypes.Money;

import java.util.List;


public class Tax  {
    private final float TAX_PERCENTAGE_ALCOHOL= 10;
    private final float TAX_PERCENTAGE_DRINK= 5;
    private final float TAX_PERCENTAGE_FOOD= 20;
    
    private float percantage;
    
    private Money taxAmount = new Money();

    public Tax(){

    }

    public Money getTaxAmount(){
      return taxAmount;
    }
    //calculating total tax amount for each products
    public Money getTax(Sale sale){
        List<SalesLineItem> salelineItems =  sale.getLineItems();

        for(SalesLineItem item : salelineItems){
            TaxLineItem taxItem = createTaxItem(item);
            taxAmount = taxAmount.add(taxItem.getAmount().times(percantage/100));
        }
        return taxAmount;
    }
    //creating tax line items
    public TaxLineItem createTaxItem(SalesLineItem item) {
        if(item.getDescription().getCategory().equals("Alcohol"))  percantage = TAX_PERCENTAGE_ALCOHOL;
        else if(item.getDescription().getCategory().equals("Drink")) percantage = TAX_PERCENTAGE_DRINK;
        else if(item.getDescription().getCategory().equals("Food")) percantage = TAX_PERCENTAGE_FOOD;
        else percantage = 0;
        TaxLineItem taxItem = new TaxLineItem(item.getDescription(),percantage,item.getSubTotal());
        return taxItem;
    }
}
