package com.pluralsight;

import java.io.FileInputStream;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        homeScreen();
    }

    /**
     * Enter of the Application
     */
    public static void homeScreen() {

        boolean flag = true;
        while (flag) {
            printMenu();
            String command = promptForString("Enter your option: ").toLowerCase();

            switch (command) {
                case "read" -> {String fileName = promptForString("Enter the name of a story: ").toLowerCase();
                    readStoryToKid(fileName);
                }
                case "exit" -> exit(-1);
                default -> System.out.println("Invalid Option! Please either (read) or (exit)");
            }


        }
    }

    /**
     * print the menu
     */
    public static void printMenu() {
        String info =
                """
                Welcome to our BedTime Stories Application
                Option (read)
                Option (exit)
                """;
        System.out.println(info);

        System.out.println();
    }

    /**
     *  prompt for a string
     * @param prompt
     * @return string
     */
    public static String promptForString(String prompt) {
        System.out.print(prompt);
        String command = null;
        try{
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return command;

    }

    /**
     * read the story
     * @param filename story name
     */
    public static void readStoryToKid(String filename) {

        ClassLoader classLoader = Main.class.getClassLoader();
        System.out.println(classLoader);

        filename = filename + ".txt";

        try (FileInputStream fileInputStream = new FileInputStream(filename); Scanner scanner = new Scanner(fileInputStream)) {

            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String lineContent = scanner.nextLine();
                System.out.printf("%d : %s\n", lineNumber++, lineContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thank you for listening");
            System.out.println();
        }
    }
}