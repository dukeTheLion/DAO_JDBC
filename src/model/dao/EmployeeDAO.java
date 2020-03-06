package model.dao;

import model.entities.Department;
import model.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    void insert(Employee obj);
    void update(Employee obj);
    void delete(Integer id);
    List<Employee> findById(Integer id);
    List<Employee> findByName(Integer id);
    List<Employee> findAll();
}
