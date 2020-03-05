package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String CPF;
    private Double salaryHour;
    private Integer weeklyHour;

    private Department department;

    public Employee(Integer id, String name, String lastName, String email, String CPF,
                    Double salaryHour, Integer weeklyHour, Department department) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.CPF = CPF;
        this.salaryHour = salaryHour;
        this.weeklyHour = weeklyHour;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Double getSalaryHour() {
        return salaryHour;
    }

    public void setSalaryHour(Double salaryHour) {
        this.salaryHour = salaryHour;
    }

    public Integer getWeeklyHour() {
        return weeklyHour;
    }

    public void setWeeklyHour(Integer weeklyHour) {
        this.weeklyHour = weeklyHour;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", CPF='" + CPF + '\'' +
                ", salaryHour=" + salaryHour +
                ", weeklyHour=" + weeklyHour +
                ", department=" + department +
                '}';
    }
}
