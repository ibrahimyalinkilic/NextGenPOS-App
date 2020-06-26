package com.company.domain;

import com.company.customTypes.ItemID;
import com.company.customTypes.Money;


public class ProductDescription {
    private ItemID id;
    private Money price;
    private String description;
    private String category;
    private int discountRate;

    public ProductDescription(ItemID id, Money price, String description,String category, int discountRate){
        this.id=id;
        this.price =price;
        this.description =description;
        this.category = category;
        this.discountRate = discountRate;
    }

    public ItemID getITemID(){
        return id;
    }

    public Money getPrice(){
        return price;
    }

    public String getDescription(){
        return  description;
    }

    public int getDiscountRate() { return discountRate; }

    public String getCategory() { return category; }

    @Override
    public String toString(){
        return id.toString()+ " - "+ price.toString()+" - " + description;
    }



}
