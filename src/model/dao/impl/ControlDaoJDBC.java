package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.ControlDAO;
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

public class ControlDaoJDBC implements ControlDAO {

    private Connection conn;

    public ControlDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void update(Control obj) {

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
                    + "Department.DepName, Control.Entry, Control.Exit, Control.DaySalary "
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
                    + "Department.DepName, Control.Entry, Control.Exit, Control.DaySalary "
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
    public List<Control> findByDate() {
        return null;
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
                emp = new Employee(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getLong("CPF"),
                        rs.getDouble("SalaryHour"),
                        rs.getInt("WeeklyHour"),
                        dep);
                E_map.put(rs.getInt("ID"), emp);
            }

            // Cira a lista de 'Control' com vários control aprontando apenas pra um 'Employee' e um 'Department'
            Control control = new Control(rs.getInt("ID"),
                    rs.getDate("Entry"),
                    rs.getDate("Exit"),
                    rs.getFloat("DaySalary"),
                    emp);

            list.add(control);

        }

        return list;
    }
}
