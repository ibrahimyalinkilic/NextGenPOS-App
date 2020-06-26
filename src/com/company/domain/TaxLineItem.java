package com.company.domain;

import com.company.customTypes.Money;

public class TaxLineItem {

    private ProductDescription description;
    private float percantage;
    private Money amount;

    public TaxLineItem(ProductDescription description, float percantage, Money amount) {
        this.description = description;
        this.percantage = percantage;
        this.amount = amount;
    }


    public ProductDescription getDescription() {
        return description;
    }

    public void setDescription(ProductDescription description) {
        this.description = description;
    }

    public float getPercantage() {
        return percantage;
    }

    public void setPercantage(float percantage) {
        this.percantage = percantage;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}
