package applications;

import model.dao.ControlDAO;
import model.dao.impl.DaoFactory;
import model.entities.Control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {
        ControlDAO empDao = DaoFactory.newControlDAO();

        List<Control> emp = empDao.findByName("Bob", "Temm");

        System.out.println("=====================1=====================" + "\n" + emp);

        List<Control> emp2 = empDao.findById(3);

        System.out.println("=====================2=====================" + "\n" + emp2);

        List<Control> emp3 = empDao.findByNameYMD("YMD", "2019-2-5", "Bob");

        System.out.println("=====================3=====================" + "\n" + emp3);

        Control emp4 = new Control((double)4,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                "0001-01-01 00:00:00",
                (float)4000,
                null);

        empDao.insert(emp4);
        System.out.println("=====================3=====================" + "\n" + emp4.getIndex());
    }

}
