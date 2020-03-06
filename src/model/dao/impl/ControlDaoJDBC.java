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

    @Override
    public List<Control> findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT Employee.*, Department.DepName, Control.Entry, Control.Exit, Control.DaySalary "
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

    @Override
    public List<Control> findByName(Integer id) {
        return null;
    }

    @Override
    public List<Control> findAll() {
        return null;
    }

    private List<Control> instantiateControl(ResultSet rs) throws SQLException {
        List<Control> list = new ArrayList<>();

        Map<Integer, Employee> Emap = new HashMap<>();
        Map<Integer, Department> Dmap = new HashMap<>();

        while (rs.next()) {

            Department dep = Dmap.get(rs.getInt("ID"));
            Employee emp = Emap.get(rs.getInt("ID"));

            if (dep == null) {
                dep = new Department(rs.getInt("DepartmentID"), rs.getString("DepName"));
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
            }

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
