package applications;

import db.DB;
import db.DBException;
import embedded.entities.Arduino;
import model.dao.ControlDAO;
import model.dao.DepartmentDAO;
import model.dao.EmployeeDAO;
import model.dao.impl.DaoFactory;
import model.entities.Control;
import model.entities.Department;
import model.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        /*
        ControlDAO empDao = DaoFactory.newControlDAO();

        List<Control> emp = empDao.findByName("Dante", "Catones Primeiro");

        List<Control> emp2 = empDao.findById(40577313);


        List<Control> emp3 = empDao.findByNameYMD("ym", "0001-1",
                "Dante", "Catones Primeiro");


        Control emp4 = new Control(2172763187L,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                "0001-01-01 00:00:00",
                0f);

        empDao.insert(emp4);


        empDao.update(new Control(2172763187L,
                null,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                3000f));

        List<Control> emp5 = empDao.findById(2172763187L);

        EmployeeDAO employee = DaoFactory.newEmployeeDAO();

        Employee obj = new Employee(2172763187L,
                "Carlo",
                "Anonito",
                42141203222L,
                "Carlo.emp@nameEmp.com",
                30.0,
                40,
                new Department(1, "CodeTeam"),
                2172763187L);

        employee.update(obj);

        //Employee temp = employee.findByName("Carlo", "Anonito");

        employee.delete(2172763187L);

*/

        /*ControlDAO cont = DaoFactory.newControlDAO();

        Control con4 = new Control(40577312L,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        cont.cardUpdate(con4);*/

        ControlDAO cont = DaoFactory.newControlDAO();
        //Arduino arduino = new Arduino();

        //for (int i = 0; i < 4; i++) {
        //    cont.cardUpdate(new Control(arduino.getCardId(),
        //            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
        //            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        //}
        //arduino.getConn().closeIn();
        //System.exit(0);


        System.out.printf("R$%010.2f", (cont.monthSalary(40577313L, 3)));
    }

}
