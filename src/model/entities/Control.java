package model.entities;

import java.util.Date;

public class Control {
    private Double ID;
    private String entry;
    private String exit;
    private Float daySalary;
    private Integer index;

    private Employee employee;

    public Control(Double ID, String entry, String exit, Float daySalary, Integer index, Employee employee) {
        this.ID = ID;
        this.entry = entry;
        this.exit = exit;
        this.daySalary = daySalary;
        this.index = index;
        this.employee = employee;
    }

    public Control(Double ID, String entry, String exit, Float daySalary) {
        this.ID = ID;
        this.entry = entry;
        this.exit = exit;
        this.daySalary = daySalary;
        this.index = null;
    }

    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public Float getDaySalary() {
        return daySalary;
    }

    public void setDaySalary(Float daySalary) {
        this.daySalary = daySalary;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "\nControl{" +
                "ID=" + ID +
                ", entry=" + entry +
                ", exit=" + exit +
                ", daySalary=" + daySalary +
                ", index=" + index +
                ", employee=" + employee +
                '}';
    }
}
