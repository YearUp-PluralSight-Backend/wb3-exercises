package com.pluralsight.util;

import java.util.Scanner;

public class InputUtil {


    private static Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param prompt for remind the user
     * @return string value
     */
    public static String promptForString(String prompt) {
        System.out.println(prompt);
        String value;
        try {
            value = scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static String promptForString() {
        String value;
        try {
            value = scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static int promptForInteger(String prompt) {
        System.out.println(prompt);
        int value;
        try {
            value = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static int promptForInteger() {
        int value;
        try {
            value = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static Double promptForDouble(String prompt) {
        System.out.println(prompt);
        double value;
        try {
            value = Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static Double promptForDouble() {
        double value;
        try {
            value = Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static short promptForShort(String prompt) {
        System.out.println(prompt);
        short value;
        try {
            value = Short.parseShort(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
