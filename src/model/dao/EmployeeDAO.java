package model.dao;

import model.entities.Department;
import model.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    void insert(Employee obj);
    void update(Employee obj);
    void delete(Integer id);
    Department findById(Integer id);
    Department findByName(Integer id);
    List<Department> findAll();
}
