package com.pluralsight;


import com.pluralsight.model.Employee;

import java.io.*;

public class Main {

    final static String FILENAME = "employees";

    public static void main(String[] args) {
        readFile(FILENAME);

    }

    public static void readFile(String filename) {

        filename = filename + ".csv";
        int skip = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)); BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("payroll-sept-2023.csv"))) {
            bufferedWriter.write("ID|name|gross pay \n");

            String line; // each line of the content

            while ((line = bufferedReader.readLine()) != null) {

                if (skip == 0) {
                    skip++;
                    continue;
                }
//                System.out.println(line);

                // extract data and assign to the object of employee
                String[] data = line.split("\\|");
                int employeeId = Integer.parseInt(data[0]);
                String name = data[1];
                double hoursOfWorked = Double.parseDouble(data[2]);
                double payRate = Double.parseDouble(data[3]);
                Employee employee = new Employee(employeeId, name, hoursOfWorked, payRate);
//                employee.displayGrossPay(); // display the gross pay


//                write the file
                String content = employeeId + "|" + name + "|" + employee.getGrossPay() + "\n";
                bufferedWriter.write(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}