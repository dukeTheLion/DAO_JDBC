package model.dao.impl;

import model.dao.EmployeeDAO;
import model.entities.Employee;

import java.sql.Connection;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDAO {

    private Connection conn;

    public EmployeeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Employee obj) {

    }

    @Override
    public void update(Employee obj) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Employee> findById(Integer id) {
        return null;
    }

    @Override
    public List<Employee> findByName(Integer id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
