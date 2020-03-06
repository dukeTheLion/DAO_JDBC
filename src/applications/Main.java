package applications;

import db.DB;
import db.DBException;
import model.dao.ControlDAO;
import model.dao.impl.DaoFactory;
import model.entities.Control;
import model.entities.Department;
import model.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ControlDAO empDao = DaoFactory.newControlDAO();

        List<Control> emp = empDao.findById(2);

        System.out.println(emp);
    }

}
