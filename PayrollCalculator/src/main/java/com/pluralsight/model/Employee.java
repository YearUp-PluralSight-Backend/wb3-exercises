package com.pluralsight.model;

import java.util.Objects;

public class Employee {

    private int employeeId;
    private String name;
    private double hoursOfWorked;
    private double payRate;

    public Employee() {
    }

    public Employee(int employeeId, String name, double hoursOfWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursOfWorked = hoursOfWorked;
        this.payRate = payRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursOfWorked() {
        return hoursOfWorked;
    }

    public void setHoursOfWorked(double hoursOfWorked) {
        this.hoursOfWorked = hoursOfWorked;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getEmployeeId() == employee.getEmployeeId() && Double.compare(getHoursOfWorked(), employee.getHoursOfWorked()) == 0 && Double.compare(getPayRate(), employee.getPayRate()) == 0 && Objects.equals(getName(), employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getName(), getHoursOfWorked(), getPayRate());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", hoursOfWorked=" + hoursOfWorked +
                ", payRate=" + payRate +
                '}';
    }


    public void displayGrossPay() {
        System.out.println("Id: " + getEmployeeId() + " | " + getName() + " gross pay is "  + getHoursOfWorked() * getPayRate());
    }
}
