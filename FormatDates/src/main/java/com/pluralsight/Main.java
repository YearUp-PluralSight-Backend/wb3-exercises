package com.pluralsight;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

//        09/05/2021
//        2021-09-05
//        September 5, 2021
//        Sunday, Sep 5, 2021 10:02 ß display in GMT time

        String dateOne = "09/05/2021";
        DateTimeFormatter dateTimeFormatterOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDateOne = LocalDate.parse(dateOne, dateTimeFormatterOne);
        String formatDateOne = localDateOne.format(dateTimeFormatterOne);
        System.out.println("Now: " + formatDateOne);

        String dateTwo = "2021-09-05";
        DateTimeFormatter dateTimeFormatterTwo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateTwo = LocalDate.parse(dateTwo, dateTimeFormatterTwo);
        String formatDateTwo = localDateTwo.format(dateTimeFormatterTwo);
        System.out.println("Now: " + formatDateTwo);

        String dateThree = "September 5, 2021";
        DateTimeFormatter dateTimeFormatterThree = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        LocalDate localDateThree = LocalDate.parse(dateThree, dateTimeFormatterThree);
        String formatDateThree = localDateThree.format(dateTimeFormatterThree);
        System.out.println("Now: " + formatDateThree);

//                            "EEEE, MM d, yyyy hh:mm"
        String dateTimeFour = "Sunday, Sep 5, 2021 10:02";
        DateTimeFormatter dateTimeFormatterFour = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh:mm");
        LocalDateTime localDateFour = LocalDateTime.parse(dateTimeFour, dateTimeFormatterFour);
        String forwardSlashFormatDateFour = localDateFour.format(dateTimeFormatterFour);
        System.out.println("Now: " + forwardSlashFormatDateFour);

    }
}