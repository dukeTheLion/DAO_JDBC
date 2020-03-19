package applications;

import model.dao.ControlDAO;
import model.dao.EmployeeDAO;
import model.dao.impl.DaoFactory;
import model.entities.Control;
import model.entities.Department;
import model.entities.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {
        //ControlDAO empDao = DaoFactory.newControlDAO();

        //List<Control> emp = empDao.findByName("Bob", "Temm");

        //System.out.println("=====================1=====================" + "\n" + emp);

        //List<Control> emp2 = empDao.findById(40577313);

        //System.out.println("=====================2=====================" + "\n" + emp2);

        //List<Control> emp3 = empDao.findByNameYMD("YMD", "2019-2-5", "Bob");

        //System.out.println("=====================3=====================" + "\n" + emp3);

        //Control emp4 = new Control(3d,
        //        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
        //        "0001-01-01 00:00:00",
        //        4000f);

        //empDao.insert(emp4);
        //System.out.println("=====================4=====================" + "\n" + emp4.getIndex());


        //empDao.update(new Control(3d,
        //        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
        //        "2020-01-01 11:00:00",
        //        6000f));

        //List<Control> emp5 = empDao.findById(3);
        //System.out.println("=====================5=====================" + "\n" + emp5);

        EmployeeDAO employee = DaoFactory.newEmployeeDAO();

        //Employee obj = new Employee(40577313d,
        //        "Dante",
        //        "Catones Primeiro",
        //        32343203221d,
        //        "dante.emp@nameEmp.com",
        //        40.0,
        //        40,
        //        new Department(1, "CodeTeam"),
        //        40577313d);

        Employee temp = employee.findById(40577313);

        System.out.print(temp);
    }

}
