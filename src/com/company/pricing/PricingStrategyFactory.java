package com.company.pricing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.company.domain.Customer;

public class PricingStrategyFactory {
	private static PricingStrategyFactory factory;
	private boolean isSeniorCustomer = false;
	private float seniorCustomerPercentage = 5; //Special discount percentage for senior customers
	private float OverallDiscountPercentage = 10;

	public static synchronized PricingStrategyFactory getInstance() {
		if (factory == null) {
			factory = new PricingStrategyFactory();
		}
		return factory;
	}
	public ISalePricingStrategy getSalePricingStrategy() {

		int  strategyRule = 2;//[0: BestForStore /1: BestForCustomer /2: All Discount]


		PercentageDiscountPricingStrategy percentageDiscount = new PercentageDiscountPricingStrategy(getOverallDiscountPercentage());
		AbsoluteDiscountOverThresholdPricingStrategy absoluteDiscount = new AbsoluteDiscountOverThresholdPricingStrategy();
		DiscountForDayPricingStrategy discountForDay = new DiscountForDayPricingStrategy();

		switch (strategyRule){
			case 0://BEST FOR STORE
				CompositeBestForStorePricingStrategy bestForStoreStrategy = new CompositeBestForStorePricingStrategy();
				bestForStoreStrategy.add(percentageDiscount);
				bestForStoreStrategy.add(absoluteDiscount);
				bestForStoreStrategy.add(discountForDay);
				if(isSeniorCustomer){
					SeniorPricingStrategy seniorDiscount = new SeniorPricingStrategy(getSeniorCustomerPercentage());
					bestForStoreStrategy.add(seniorDiscount);
				}
				return bestForStoreStrategy;
			case 1://BEST FOR CUSTOMER
				CompositeBestForCustomerPricingStrategy bestForCustomerStrategy = new CompositeBestForCustomerPricingStrategy();
				bestForCustomerStrategy.add(percentageDiscount);
				bestForCustomerStrategy.add(absoluteDiscount);
				bestForCustomerStrategy.add(discountForDay);
				if(isSeniorCustomer){
					SeniorPricingStrategy seniorDiscount = new SeniorPricingStrategy(getSeniorCustomerPercentage());
					bestForCustomerStrategy.add(seniorDiscount);
				}
				return bestForCustomerStrategy;
			case 2://ALL DISCOUNTS ARE VALID
				CompositeAllDiscount allDiscountStrategy = new CompositeAllDiscount();
				allDiscountStrategy.add(absoluteDiscount);
				allDiscountStrategy.add(percentageDiscount);
				allDiscountStrategy.add(discountForDay);
				
				if(isSeniorCustomer){
					SeniorPricingStrategy seniorDiscount = new SeniorPricingStrategy(getSeniorCustomerPercentage());
					allDiscountStrategy.add(seniorDiscount);
				}
				return allDiscountStrategy;
		}

		System.out.print("Null Strategy!!");
		return null;
	}

	public float getSeniorCustomerPercentage() {
		return seniorCustomerPercentage;
	}

	public float getOverallDiscountPercentage() {
		return OverallDiscountPercentage;
	}

	public void addCustomerPricingStrategy(Customer c) {
		isSeniorCustomer = c.getisSenior();
	}
}