package applications;

import model.dao.ControlDAO;
import model.dao.impl.DaoFactory;
import model.entities.Control;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ControlDAO empDao = DaoFactory.newControlDAO();

        List<Control> emp = empDao.findByName("Bob", "Temm");

        System.out.println(emp.get(0).getEmployee().getDepartment().getName());
    }

}
