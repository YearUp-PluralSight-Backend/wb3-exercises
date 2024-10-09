package com.pluralsight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Demo2 {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in); FileInputStream fileInputStream = new FileInputStream("peom.txt")){

            int lineNumber = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.printf("%d: %s\n", lineNumber++, line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
