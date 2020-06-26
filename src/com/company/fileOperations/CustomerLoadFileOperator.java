package com.company.fileOperations;

import com.company.customTypes.ItemID;
import com.company.customTypes.Money;
import com.company.domain.ProductDescription;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class CustomerLoadFileOperator {
    private String filePath;
    private List<String> seniorCustomers = new ArrayList<>();

    public CustomerLoadFileOperator(String filePath) {
        this.filePath = filePath;
    }

    public void readFromFile() {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            //returns true if there is another line to read
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                seniorCustomers.add(line);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("IOException: File could not be read (catalog could not be created). Please check the file and try again.");
            System.exit(-1);
        }


    }

    public List<String> getSeniorCustomers() {
        return seniorCustomers;
    }
}
