package model.entities;

import java.util.Date;

public class Control {
    private Integer ID;
    private Date entry;
    private Date exit;
    private Float daySalary;

    private Employee employee;

    public Control(Integer ID, Date entry, Date exit, Float daySalary, Employee employee) {
        this.ID = ID;
        this.entry = entry;
        this.exit = exit;
        this.daySalary = daySalary;
        this.employee = employee;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getEntry() {
        return entry;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public Date getExit() {
        return exit;
    }

    public void setExit(Date exit) {
        this.exit = exit;
    }

    public Float getDaySalary() {
        return daySalary;
    }

    public void setDaySalary(Float daySalary) {
        this.daySalary = daySalary;
    }

    public Employee Employee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Control{" +
                "ID=" + ID +
                ", entry=" + entry +
                ", exit=" + exit +
                ", daySalary=" + daySalary +
                ", department=" + employee +
                '}';
    }
}
