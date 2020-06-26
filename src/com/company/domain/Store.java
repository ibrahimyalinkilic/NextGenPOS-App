package com.company.domain;

import com.company.fileOperations.SaleFileOperator;
import com.company.fileOperations.CustomerLoadFileOperator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private ProductCatalog catalog = new ProductCatalog();
    private Register register = new Register(catalog,this);
    private List<String> seniorCustomers = new ArrayList<>();

    CustomerLoadFileOperator slfo = new CustomerLoadFileOperator("SeniorCustomers.txt");

    public Store() { loadProdSpecs(); }

    public void loadProdSpecs(){
        slfo.readFromFile();
        seniorCustomers = slfo.getSeniorCustomers();
    }

	SaleFileOperator sfo = new SaleFileOperator("Sales.txt");

    public Register getRegister(){
        return register;
    }

    public void addSale(Sale s){
        saveSale(s);
    }

    public void saveSale(Sale s){
        sfo.saveSale(s);
    }

    public Customer getCustomer(String id) {
    	
    	Customer customer = new Customer(id,false);
    	if(seniorCustomers.contains(id)){
            customer.setSenior(true);
        }
		return customer;
    }
}
