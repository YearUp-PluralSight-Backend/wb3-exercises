package com.pluralsight.model;

import java.time.LocalDate;
import java.util.Objects;

public class Quote {

    private String author;
    private LocalDate date;
    private String quote;


    public Quote() {
    }

    public Quote(String author, LocalDate date, String quote) {
        this.author = author;
        this.date = date;
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote1 = (Quote) o;
        return Objects.equals(getAuthor(), quote1.getAuthor()) && Objects.equals(getDate(), quote1.getDate()) && Objects.equals(getQuote(), quote1.getQuote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getDate(), getQuote());
    }

    public void displayQuote() {
        System.out.println("Author: " + author);
        System.out.println("Date: " + date);
        System.out.println("Quote: \"" + quote + "\"");
        System.out.println();
    }
}
