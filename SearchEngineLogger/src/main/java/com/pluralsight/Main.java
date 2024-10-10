package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static final String FILENAME = "logs.txt";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.US).withZone(ZoneOffset.UTC);
    public static void main(String[] args) {

        homeScreen();
    }

    /**
     * Home Screen to start the program
     */
    private static void homeScreen() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILENAME, true));
            bufferedWriter.write(LocalDateTime.now().format(dateTimeFormatter) + " search: ");
            printMenu();
            boolean flag = true;
            while (flag) {
                String command = promptForString("Please enter your option: ").toUpperCase();

                if (command.equalsIgnoreCase("S")) {
                    String text = promptForString("Please enter text: ");
                    search(text, bufferedWriter);

                } else if (command.equalsIgnoreCase("X")) {
                    flag = false;

                } else {
                    System.out.println("Invalid Option!  please try it again");
                    System.out.println();
                }

                if (flag == false) {
                    break;
                }

                printMenu();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * write the text to the file
     *
     * @param text user text that will write into the logs.txt file
     */
    private static void search(String text,  BufferedWriter bufferedWriter) {

        try {
            bufferedWriter.write(LocalDateTime.now().format(dateTimeFormatter) + " search: " + text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * accept a prompt and return a String
     *
     * @param prompt prompt infomation
     * @return User's input
     */
    private static String promptForString(String prompt) {
        System.out.println(prompt);
        String value;
        Scanner scanner;
        try {
            scanner = new Scanner(System.in);
            value = scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * print the option menu
     */
    private static void printMenu() {
        String infor = """
                S. Searching for a term
                X. Exiting the application
                """;
        System.out.println(infor);
    }


}