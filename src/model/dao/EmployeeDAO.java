package model.dao;

import model.entities.Department;
import model.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    void insert(Employee obj);
    void update(Employee obj);
    void delete(Integer id);
    Employee findById(Integer id);
    Employee findByName(String id);
    List<Employee> findAll();
}
