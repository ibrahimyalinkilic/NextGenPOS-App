package com.company.domain;

import com.company.customTypes.Money;

import java.util.HashMap;


public class SalesLineItem  {
    private int quantity;
    private ProductDescription description;
    private Money subTotal;

    public SalesLineItem(ProductDescription desc, int quantity){
        this.description = desc;
        this.quantity = quantity;
        subTotal = description.getPrice().times(quantity);
    }

    public Money getSubTotal(){
        return  subTotal;
    }

    @Override
    public String toString(){
        return description.toString() + " (x" + quantity +") " + "(Subtotal: "  + getSubTotal()+ ")" ;
    }

    public ProductDescription getDescription() {
        return description;
    }

    public void setSubTotal(Money subTotal) {
        this.subTotal = subTotal;

    }

}
