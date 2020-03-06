package model.dao.impl;

import db.DB;
import model.dao.ControlDAO;
import model.dao.DepartmentDAO;
import model.dao.EmployeeDAO;
import model.entities.Control;

public class DaoFactory {
    public static EmployeeDAO newEmployeeDAO(){
        return new EmployeeDaoJDBC(DB.connect());
    }

    public static DepartmentDAO newDepartmentDAO(){
        return new DepartmentDaoJDBC();
    }

    public static ControlDAO newControlDAO(){
        return new ControlDaoJDBC(DB.connect());
    }
}
