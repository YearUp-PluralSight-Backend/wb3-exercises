package com.pluralsight;

import com.pluralsight.model.Product;
import com.pluralsight.util.InputUtil;

import java.io.*;
import java.util.ArrayList;

/**
 * read the files and display the data
 * one static variable and three static methods.
 */
public class StoreApp {

    final static String FILENAME = "inventory.csv";
    static ArrayList<Product> inventory = getInventory();

    public static void main(String[] args) {

        homeScreen();
    }

    /**
     * start the program from here
     *  a while loop to give user options
     */
    private static void homeScreen(){

        try {
            boolean flag = true;
            while (flag) {
                PrintMenu();
                short command =  InputUtil.promptForShort("Enter your command: ");
                switch (command) {
                    case 1 -> dislpayProducts(inventory);
                    case 2 -> loopUpById();
                    case 3 -> lookUpByPriceRange();
                    case 4 -> addNewProduct();
                    case 5 -> flag = false;
                    default -> System.out.println("Invalid option! Please try it again.");
                }

                format();
            }
        } catch (Exception e) {

        }

    }

    /**
     * print the menu option
     */
    private static void PrintMenu() {
        String info =
                """
                D   - Display the products              (D   = Display)
                LId - Look up product by id             (LId = look id)
                LPR - Look up product by price range    (LR  = look price range)
                A   - Add new product                   (A   = add)
                """;
        System.out.println(info);
    }

    /**
     *  lookup the product based on the price range.
     */
    private static void lookUpByPriceRange() {

        double startPrice = InputUtil.promptForDouble("Enter your start price : ");
        double endPrice = InputUtil.promptForDouble("Enter your end price : ");

        for (Product product : inventory) {

            if (product.getPrice() >= startPrice && product.getPrice() < endPrice) {

                System.out.println(product.toString());
            }
        }
    }

    /**
     * give a new line with 100 -
     */
    private static void format() {
        System.out.println("-".repeat(100));
    }

    /**
     * ask user id and print out the product based on the id.
     */
    private static void loopUpById() {
        int id = InputUtil.promptForInteger("Enter the product Id: ");
        for (Product product : inventory) {

            if (product.getId() == id) {

                System.out.println(product.toString());
            }
        }
    }

    /**
     * add new product and then write the product into csv
     */
    private static void addNewProduct() {
        int id = InputUtil.promptForInteger("Enter product id: ");

        boolean valid = checkId(id);
        while (!valid) {
            System.out.println("You have entered the duplicate ID.");
            id = InputUtil.promptForInteger("Enter product id: ");
            valid = checkId(id);
        }

        String name = InputUtil.promptForString("Enter product name: ");
        double price = InputUtil.promptForDouble("Enter the product price: ");
        Product product = new Product(id, name, price);
        inventory.add(product);

        boolean writable = writeProductToCSV(product);

        if (writable) {
            System.out.println("You have successfully write the product into the inventory.csv");
        } else {
            System.out.println("You failed to write the product to inventory.csv");
        }
        System.out.println("You have successfully added new product: " + product.toString());
    }

    /**
     * write the product to csv file
     *
     * @param product new product
     * @return true or false depends on whether it write successful or not.
     */
    private static boolean writeProductToCSV(Product product) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv", true))) {
            String line = "\n" + product.getId() + "|" + product.getName() + "|" + product.getPrice();
            bufferedWriter.write(line);
            bufferedWriter.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * check the id in the inventory
     *
     * @param id product id
     * @return return true if the id is not in the inventory.
     */
    private static boolean checkId(int id) {

        for (Product product : inventory) {
            if (id == product.getId()) {
                return false;
            }
        }
        return true;
    }

    /**
     * start point of the program!
     * get the inventory and loop it to display them.
     */
    public static void dislpayProducts(ArrayList<Product> inventory) {
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
     * read the file inventory.csv
     * get the content and split them by |.
     * create a {@code Product} to store the data
     * add the {@code Product} to the inventory list
     *
     * @return
     */
    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<>(); // create a list to store the products

        // read the file and add the content to the inventory list.  (try-catch resource release)
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] lineContent = line.split("\\|");
                inventory.add(new Product(Integer.parseInt(lineContent[0]), lineContent[1], Double.parseDouble(lineContent[2])));

//                int id = Integer.parseInt(lineContent[0]);
//                String name = lineContent[1];
//                double price = Double.parseDouble(lineContent[2]);
//                Product product = new Product(id, name, price);
//                inventory.add(product);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }
}