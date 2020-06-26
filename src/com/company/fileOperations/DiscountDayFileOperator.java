package com.company.fileOperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class DiscountDayFileOperator {
    private String filePath;
    private HashMap discountDays = new HashMap();

    public DiscountDayFileOperator(String filePath) {
        this.filePath = filePath;
    }

    public void readFromFile() {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            //returns true if there is another line to read
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                String[] splitted = line.split(" ");
                discountDays.put(splitted[0],splitted[1]);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("IOException: File could not be read (catalog could not be created). Please check the file and try again.");
            System.exit(-1);
        }


    }

    public HashMap getDiscountDays() {
        return discountDays;
    }
}

