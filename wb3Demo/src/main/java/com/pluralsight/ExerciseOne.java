package com.pluralsight;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class ExerciseOne {

    final static int LENGTH = 10;
    static Quote[] quotes = new Quote[LENGTH];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        homeScreen();
    }

    public static void homeScreen() {
        initializingQuotes();
        boolean flag = true;
        while (flag) {

            String command = printMenuAndAskPrompt();
            switch (command) {
                case "R" -> randomDislpayQuotes(quotes);
                case "S" -> {
                    int index = promptFoInt("Enter your index");
                    displayIndexOf(index);
                }
                case "Q" -> flag = false;
                default -> System.out.println("Invalid Command! Please enter ( R, S, Q)");
            }


        }
    }

    public static String printMenuAndAskPrompt() {
        System.out.println("(S) Select a number between 1 and 10 and display that quote.");
        String command = null;
        try {
            command = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return command;
    }

    public static void displayIndexOf(int index) {
        System.out.println("Enter your index: ");
        quotes[index].displayQuote();
    }

    public static void randomDislpayQuotes(Quote[] quotes) {
        Random random = new Random();
        int nextValue = random.nextInt(0, 10);
        quotes[nextValue].displayQuote();
    }


    public static void initializingQuotes() {

        quotes[0] = new Quote("Albert Einstein", LocalDate.of(1921, 3, 14), "Imagination is more important than knowledge.");
        quotes[1] = new Quote("Isaac Newton", LocalDate.of(1687, 7, 5), "If I have seen further it is by standing on the shoulders of Giants.");
        quotes[2] = new Quote("Yoda", LocalDate.of(1980, 5, 21), "Do or do not, there is no try.");
        quotes[3] = new Quote("Steve Jobs", LocalDate.of(2005, 6, 12), "Stay hungry, stay foolish.");
        quotes[4] = new Quote("Nelson Mandela", LocalDate.of(1994, 5, 10), "It always seems impossible until it's done.");
        quotes[5] = new Quote("Mahatma Gandhi", LocalDate.of(1930, 1, 30), "Be the change that you wish to see in the world.");
        quotes[6] = new Quote("Marie Curie", LocalDate.of(1903, 12, 10), "Nothing in life is to be feared, it is only to be understood.");
        quotes[7] = new Quote("William Shakespeare", LocalDate.of(1603, 4, 23), "To be, or not to be, that is the question.");
        quotes[8] = new Quote("Martin Luther King Jr.", LocalDate.of(1963, 8, 28), "I have a dream.");
        quotes[9] = new Quote("Walt Disney", LocalDate.of(1947, 2, 10), "The way to get started is to quit talking and begin doing.");
    }

    public static int promptFoInt(String prompt) {
        System.out.println(prompt);
        int index = -1;
        boolean flag = true;
        while (flag) {
            try {
                index = scanner.nextInt();

                if (index >= 0 || index <= 9) {
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println("Invalid Range!  Only between (0 - 9)");
                e.printStackTrace();
            }
        }
        scanner.nextLine();
        return index;
    }
}
