package com.pluralsight;

import com.pluralsight.model.Product;
import com.pluralsight.util.InputUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * read the files and display the data
 * one static variable and three static methods.
 */
public class StoreApp {

    final static String FILENAME = "inventory.csv";
    static Map<Integer, Product> inventory = getInventory();

    public static void main(String[] args) {

        homeScreen();
    }

    /**
     * start the program from here
     * a while loop to give user options
     */
    private static void homeScreen() {

        try {
            boolean flag = true;
            while (flag) {
                PrintMenu();
                short command = InputUtil.promptForShort("Enter your command: ");
                switch (command) {
                    case 1 -> dislpayProducts(inventory);
                    case 2 -> loopUpById();
                    case 3 -> lookUpByPriceRange();
                    case 4 -> addNewProduct();
                    case 5 -> deleteProductById();
                    case 6 -> updateProductById();
                    case 7 -> {
                        flag = false;
                        System.out.println("Thank you for using our application! have a great day.");
                    }
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
                        1 - Display the products
                        2 - Look up product by id
                        3 - Look up product by price range
                        4 - Add new product
                        5 - Delete product By Id
                        6 - Update product by Id
                        7 - Exit
                        """;
        System.out.println(info);
    }

    /**
     * lookup the product based on the price range.
     */
    private static void lookUpByPriceRange() {

        HashMap<Integer, Product> productList = new HashMap<>();
        double startPrice = InputUtil.promptForDouble("Enter your start price : ");
        double endPrice = InputUtil.promptForDouble("Enter your end price : ");

        for (int key: inventory.keySet()) {
            Product product = inventory.get(key);
            if (product.getPrice() >= startPrice && product.getPrice() < endPrice) {

                productList.put(product.getId(), product);
            }
        }

        dislpayProducts(productList);
    }

    /**
     * ask user id and print out the product based on the id.
     */
    private static void loopUpById() {
        int id = InputUtil.promptForInteger("Enter the product Id: ");


        Product product = inventory.get(id);
        if (product.getId() == id) {
            System.out.println(product.toString());
        } else {
            System.out.println("Not found!");
        }

    }

    /**
     * add new product and then write the product into csv
     */
    private static void addNewProduct() {

        Object[] productData = productDataInput();
        int id = (Integer) productData[0];
        String name = (String) productData[1];
        double price = (Double) productData[2];
        Product product = new Product(id, name, price);
        inventory.put(id, product);
        updateCSV();

        System.out.println("You have successfully added new product: " + product.toString());
    }

    /**
     * update the product information by id
     */
    private static void updateProductById() {

        int id = InputUtil.promptForInteger("Enter the product id: ");
        Product product = inventory.get(id);
        if (product == null) {
            System.out.println("ID is not found!");
        }
        Object[] productData = productDataInput();
        int newId = (Integer) productData[0];
        String newName = (String) productData[1];
        double newPrice = (Double) productData[2];

        product.setId(newId);
        product.setName(newName);
        product.setPrice(newPrice);
        updateCSV();

        System.out.println("You have updated the product: " + product.toString());
    }

    /**
     * delete the product by product id
     *
     * @return true if it found by id and deleted.
     */
    private static boolean deleteProductById() {
        int id = InputUtil.promptForInteger("Enter the product id: ");
        Product product = inventory.get(id);
        if (product != null) {
            System.out.println("Not found!");
            return false;
        }
        product = inventory.get(id);
        System.out.println(product.toString());

        updateCSV();
        return true;
    }

    /**
     * ask user to input the product information, id name, price
     *
     * @return id, name, price
     */
    private static Object[] productDataInput() {
        int id = InputUtil.promptForInteger("Enter product id: ");

        boolean valid = checkId(id);
        while (!valid) {
            System.out.println("You have entered the duplicate ID.");
            id = InputUtil.promptForInteger("Enter product id: ");
            valid = checkId(id);
        }

        String name = InputUtil.promptForString("Enter product name: ");
        double price = InputUtil.promptForDouble("Enter the product price: ");
        return new Object[]{id, name, price};
    }

    /**
     * give a new line with 100 -
     */
    private static void format() {
        System.out.println("-".repeat(100));
    }

    /**
     * write the product to csv file
     *
     * @param product new product
     * @return true or false depends on whether it write successful or not.
     */
    private static void writeProductToCSV(Product product, boolean overWrite) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv", overWrite))) {
            String line = "\n" + product.getId() + "|" + product.getName() + "|" + product.getPrice();
            bufferedWriter.write(line);
            bufferedWriter.flush();
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
        Product product = inventory.get(id);
        if (product == null) {
            return false;
        }
        return true;
    }

    /**
     * update the csv file
     */
    private static void updateCSV() {
        for(int key: inventory.keySet()) {
            Product newProduct = inventory.get(key);
            writeProductToCSV(newProduct, true);
        }

        System.out.println("You have successfully update the inventory.csv");
    }

    /**
     * start point of the program!
     * get the inventory and loop it to display them.
     */
    private static void dislpayProducts(Map<Integer, Product> inventory) {
        try {
            System.out.println("We carry the following inventory: ");

            for (Integer key : inventory.keySet()) {

                Product p = inventory.get(key);
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
    private static Map<Integer, Product> getInventory() {
        Map<Integer, Product> inventory = new HashMap<>(); // create a list to store the products

        // read the file and add the content to the inventory list.  (try-catch resource release)
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] lineContent = line.split("\\|");
                inventory.putIfAbsent(Integer.parseInt(lineContent[0]), new Product(Integer.parseInt(lineContent[0]), lineContent[1], Double.parseDouble(lineContent[2])));

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