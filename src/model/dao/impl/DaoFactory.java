package model.dao.impl;

import model.dao.DepartmentDAO;
import model.dao.EmployeeDAO;
import org.jetbrains.annotations.Contract;

public class DaoFactory {
    public static EmployeeDAO newEmployeeDAO(){
        return new EmployeeDaoJDBC();
    }

    public static DepartmentDAO newDepartmentDAO(){
        return new DepartmentDaoJDBC();
    }
}
