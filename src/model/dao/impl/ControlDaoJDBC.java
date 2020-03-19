package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.ControlDAO;
import model.entities.Control;
import model.entities.Department;
import model.entities.Employee;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

public class ControlDaoJDBC implements ControlDAO {

    private Connection conn;

    public ControlDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Control obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO DataBase.Control (`ID`, `Entry`, `Exit`, `DaySalary`)  "
                    + "values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setDouble(1, obj.getID());
            st.setString(2, obj.getEntry());
            st.setString(3, obj.getExit());
            st.setFloat(4, obj.getDaySalary());

            int rows = st.executeUpdate();

            if (rows > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setIndex(id);
                }
                DB.closeRs(rs);
            } else throw new DBException("None");
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeSt(st);
        }
    }

    @Override
    public void update(Control obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE `DataBase`.`Control` "
                    + "SET `Exit` = ?, `DaySalary` = ? "
                    + "WHERE `Control`.`Exit` = '0001-01-01 00:00:00' AND `Control`.`ID` = ?;");

            st.setString(1, obj.getExit());
            st.setFloat(2, obj.getDaySalary());
            st.setDouble(3, obj.getID());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeSt(st);
        }

    }

    /**
     * Find by ID via Control:
     * Esta função esta responsável por trazer a lista completa de funcionário, departamento e dias trabalhados.
     */
    @Override
    public List<Control> findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT Employee.*, "
                    + "Department.DepName, Control.Entry, Control.Exit, Control.DaySalary, Control.Index "
                    + "FROM DataBase.Employee "
                    + "INNER JOIN DataBase.Department ON Employee.DepartmentID = Department.ID "
                    + "INNER JOIN DataBase.Control ON Employee.ControlID = Control.ID "
                    + "WHERE Employee.ID = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            return instantiateControl(rs);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeRs(rs);
            DB.closeSt(st);
        }
    }

    /**
     * Find by Name via Control:
     * Esta função esta responsável por trazer a lista completa de funcionário, departamento e dias trabalhados.
     */
    @Override
    public List<Control> findByName(String name, String lastName) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT Employee.*, "
                    + "Department.DepName, Control.Entry, Control.Exit, Control.DaySalary, Control.Index "
                    + "FROM DataBase.Employee "
                    + "INNER JOIN DataBase.Department ON Employee.DepartmentID = Department.ID "
                    + "INNER JOIN DataBase.Control ON Employee.ControlID = Control.ID "
                    + "WHERE Employee.Name = ? AND Employee.LastName = ?");

            st.setString(1, name);
            st.setString(2, lastName);
            rs = st.executeQuery();

            return instantiateControl(rs);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeRs(rs);
            DB.closeSt(st);
        }
    }

    @Override
    public List<Control> findByNameYMD(String type, String date, String name) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(sql(type));

            if (type.length() == 2){
                String[] num2 = date.split("-");
                st.setString(1, num2[0]);
                st.setString(2, num2[1]);
                st.setString(3, name);
            } else {
                st.setString(1, date);
                st.setString(2, name);
            }
            rs = st.executeQuery();

            return instantiateControl(rs);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeRs(rs);
            DB.closeSt(st);
        }
    }

    private List<Control> instantiateControl(ResultSet rs) throws SQLException {
        List<Control> list = new ArrayList<>();

        /*
        * Maps são usados aqui para fazer com seja possível seguir a seguinte estrutura:
        *
        * +----------------+
        * |    Control.    |
        * +----------------+
        * | Id             |
        * | Entry          |
        * | .              | -
        * | .              |  \
        * | .              |   \
        * +----------------+    \
        * +----------------+     \
        * |    Control.    |      \            +----------------+           +----------------+
        * +----------------+       \           |    Employee    |           |   Department   |
        * | Id             |        ------->   +----------------+           +----------------+
        * | Entry          |                   | Id             |           | Id             |
        * | .              | -------------->   | Name           | ------->  | DepName        |
        * | .              |                   | .              |           | .              |
        * | .              |        ------->   | .              |           | .              |
        * +----------------+       /           | .              |           | .              |
        * +----------------+      /            +----------------+           +----------------+
        * |    Control.    |     /
        * +----------------+    /
        * | Id             |   /
        * | Entry          |  /
        * | .              | -
        * | .              |
        * | .              |
        * +----------------+
        * */

        Map<Integer, Employee> E_map = new HashMap<>();
        Map<Integer, Department> D_map = new HashMap<>();

        // Loop que percorre todas linhas da tabela
        while (rs.next()) {

            // Verifica a cada ciclo se existe um objeto ja implementado para evitar duplicadas
            Department dep = D_map.get(rs.getInt("ID"));
            Employee emp = E_map.get(rs.getInt("ID"));

            if (dep == null) {
                dep = new Department(rs.getInt("DepartmentID"), rs.getString("DepName"));
                D_map.put(rs.getInt("ID"), dep);
            }
            if (emp == null) {
                emp = new Employee(rs.getLong("ID"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        rs.getDouble("CPF"),
                        rs.getString("Email"),
                        rs.getDouble("SalaryHour"),
                        rs.getInt("WeeklyHour"),
                        dep,
                        rs.getDouble("ControlID"));
                E_map.put(rs.getInt("ID"), emp);
            }

            // Cira a lista de 'Control' com vários control aprontando apenas pra um 'Employee' e um 'Department'
            Control control = new Control(rs.getLong("ID"),
                    rs.getString("Entry"),
                    rs.getString("Exit"),
                    rs.getFloat("DaySalary"),
                    rs.getInt("Index"),
                    emp);

            list.add(control);

        }

        return list;
    }

    @NotNull
    private String sql (String type){
        String std = "SELECT Employee.*, "
                + "Department.DepName, Control.Entry, Control.Exit, Control.DaySalary, Control.Index "
                + "FROM DataBase.Employee "
                + "INNER JOIN DataBase.Department ON Employee.DepartmentID = Department.ID "
                + "INNER JOIN DataBase.Control ON Employee.ControlID = Control.ID ";

        switch (type) {
            case "Y":
                return std + "WHERE YEAR(Control.Entry) = ? AND Employee.Name = ?";
            case "M":
                return std + "WHERE MONTH(Control.Entry) = ? AND Employee.Name = ?";
            case "D":
                return std + "WHERE DAY(Control.Entry) = ? AND Employee.Name = ?";
            case "YM":
                return std + "WHERE YEAR(Control.Entry) = ? AND MONTH(Control.Entry) = ? AND Employee.Name = ?";
            case "YD":
                return std + "WHERE YEAR(Control.Entry) = ? AND DAY(Control.Entry) = ? AND Employee.Name = ?";
            case "MD":
                return std + "WHERE MONTH(Control.Entry) = ? AND DAY(Control.Entry) = ? AND Employee.Name = ?";
            default:
                return std + "WHERE DATE(Control.Entry) = ? AND Employee.Name = ?";
        }
    }

}
