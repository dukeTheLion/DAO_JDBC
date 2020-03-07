package applications;

import model.dao.ControlDAO;
import model.dao.impl.DaoFactory;
import model.entities.Control;

import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {
        ControlDAO empDao = DaoFactory.newControlDAO();

        List<Control> emp = empDao.findByName("Bob", "Temm");

        //System.out.println("=====================1=====================" + "\n" + emp);

        List<Control> emp2 = empDao.findById(3);

        //System.out.println("=====================2=====================" + "\n" + emp2);

        List<Control> emp3 = empDao.findByNameYMD("YM", "2019-2", "Bob");

        System.out.println(emp3);
    }

}
