package applications;

import model.dao.EmployeeDAO;
import model.dao.impl.DaoFactory;
import model.entities.Department;
import model.entities.Employee;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Department department = new Department(1, "Books");

        System.out.println(department);

        Employee employee = new Employee(2,
                "Bob",
                "Temm",
                "Bob@mail.com",
                "12312223454",
                20.0,
                30,
                department);

        System.out.println(employee);

        EmployeeDAO ne = DaoFactory.newEmployeeDAO();
    }
}
