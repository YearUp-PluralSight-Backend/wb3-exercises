package com.pluralsight;

import com.pluralsight.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoreApp {

    final static String FILENAME = "inventory.csv";
    public static void main(String[] args) {

        homeScreen();

    }

    /**
     * start point of the program!
     * get the inventory and loop it to display them.
     */
    public static void homeScreen() {

        ArrayList<Product> inventory = getInventory();
        try {
            System.out.println("We carry the following inventory: ");

            for (int i = 0; i < inventory.size(); i++) {
                Product p = inventory.get(i);
                System.out.printf("id: %d - name: %s - Price: $%.2f\n",
                        p.getId(), p.getName(), p.getPrice());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *  read the file inventory.csv
     *  get the content and split them by |.
     *  create a {@code Product} to store the data
     *  add the {@code Product} to the inventory list
     * @return
     */
    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<>(); // create a list to store the products

        // read the file and add the content to the inventory list.  (try-catch resource release)
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] lineContent = line.split("\\|");
                int id = Integer.parseInt(lineContent[0]);
                String name = lineContent[1];
                double price = Double.parseDouble(lineContent[2]);
                Product product = new Product(id, name, price);
                inventory.add(product);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }
}