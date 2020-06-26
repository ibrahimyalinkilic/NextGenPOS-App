package com.company.domain;

import com.company.customTypes.CurrencyException;
import com.company.customTypes.Money;
import com.company.pricing.ISalePricingStrategy;
import com.company.pricing.PricingStrategyFactory;

import java.util.*;
import java.text.SimpleDateFormat;

public class Sale {
    private List<SalesLineItem> lineItems = new ArrayList<>();
    private Date date = new Date();
    public String dateFormat = "EEEEEEEEEEEE";
    private SimpleDateFormat objSDF = new SimpleDateFormat(dateFormat);
    private String day = objSDF.format(this.date);
    private boolean isComplete = false;
    private Payment payment;
    private Money total = new Money();
    private PricingStrategyFactory factory = PricingStrategyFactory.getInstance();
    private Tax taxCalculator = new Tax();
    private Money taxAmount;

    public Money getBalance() {
        return payment.getAmount().minus(total);
    }

    public void becomeComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String makeLineItem(ProductDescription desc, int quantity) throws CurrencyException {
        SalesLineItem sl = new SalesLineItem(desc, quantity);
        String slString=sl.toString();
        if (lineItems.size() > 0) { //check if there is any product in the list
            Currency firstItemCurrency =  lineItems.get(0).getSubTotal().getCurrency();
            if (firstItemCurrency == desc.getPrice().getCurrency()) {//check to see if currencies are the same
                lineItems.add(sl);
            } else {
                throw new CurrencyException();
            }
        } else {
            lineItems.add(sl);
        }
        return slString;
    }

    public void calculateTotal() {
        calculateTax();//We are calculating tax before making discounts so tax amount won't be effected from discounts.
    	ISalePricingStrategy strategy = factory.getSalePricingStrategy();
    	System.out.println("Selected Strategy: "+strategy.getStrategyName());
    	total = strategy.getTotal(this);
    }

    public void calculateTax() {
        taxAmount= taxCalculator.getTax(this);
    }

    public void makePayment(Money cashTendered) {
        payment = new Payment(cashTendered);
    }

    public String s;
    @Override
    public String toString() {
        Money totalWithoutDiscount = new Money();
        Money subTotal;
        s = "Date: " + date.toString() + "\n";
        Iterator i = lineItems.iterator();
        
        while (i.hasNext()) {
            SalesLineItem sli = (SalesLineItem) i.next();
            s += sli.toString() + " Without Discount\n";
            subTotal = sli.getSubTotal();
            totalWithoutDiscount = totalWithoutDiscount.add(subTotal); //calculating total price without discount
            }
        
            //calculate total price with applying discounts
            calculateTotal();

            s += "\n";
            s += "Total (without discount): " + totalWithoutDiscount + "\n";
            s += "Total (with discount): " + total + "\n";
            s += "Tax Amount: " + taxAmount + ")\n";
            total = total.add(taxAmount); // adding tax amount to total price
            s += "Total (With Tax): " + total + ")\n";
        return s;
    }

    public String afterSummary(){
            s += "Payment: " + payment.toString() + "\n";
            s += "Balance: " + getBalance() + "\n";
            s += "--------------------------------------\n";
            return s;
    }

	public Money getPreDiscountTotal() {
		Money total = new Money();
        Money subTotal = null;

        for (SalesLineItem lineItem : lineItems) {
            subTotal = lineItem.getSubTotal();
            total = total.add(subTotal);
        }
        return total;
	}

	public void enterCustomerForDiscount(Customer c){
            factory.addCustomerPricingStrategy(c);
    }

    public List<SalesLineItem> getLineItems() {
        return lineItems;
    }

    public String getDay() {
        return day;
    }

    public void setTotal(Money total) {
        this.total = total;
    }
}
