package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.EmployeeDAO;
import model.entities.Department;
import model.entities.Employee;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDAO {

    private Connection conn;

    public EmployeeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Employee obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO DataBase.Employee "
                            + "(ID, Name, LastName, CPF, Email, SalaryHour, WeeklyHour, DepartmentID, ControlID) "
                            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            st.setLong(1, obj.getId());
            st.setString(2, obj.getName());
            st.setString(3, obj.getLastName());
            st.setDouble(4, obj.getCPF());
            st.setString(5, obj.getEmail());
            st.setDouble(6, obj.getSalaryHour());
            st.setInt(7, obj.getWeeklyHour());
            st.setInt(8, obj.getDepartment().getId());
            st.setDouble(9, obj.getControlID());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeSt(st);
        }
    }

    @Override
    public void update(Employee obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE `DataBase`.`Employee` "
                    + "SET `Name` = ?, `LastName` = ?, `CPF` = ?, `Email` = ?, "
                    + "`SalaryHour` = ?, `WeeklyHour` = ?, `DepartmentID` = ?, `ControlID` = ? "
                    + "WHERE `Employee`.`ID` = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getLastName());
            st.setDouble(3, obj.getCPF());
            st.setString(4, obj.getEmail());
            st.setDouble(5, obj.getSalaryHour());
            st.setInt(6, obj.getWeeklyHour());
            st.setInt(7, obj.getDepartment().getId());
            st.setDouble(8, obj.getControlID());

            st.setLong(9, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeSt(st);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Employee findById(Integer id) {
        Employee temp = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT `Employee`.*, `Department`.`DepName` " +
                    "FROM `DataBase`.`Employee` " +
                    "INNER JOIN `DataBase`.`Department` ON `Employee`.`DepartmentID` = `Department`.`ID` " +
                    "WHERE `Employee`.`ID` = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                temp = new Employee(rs.getLong("ID"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        rs.getDouble("CPF"),
                        rs.getString("Email"),
                        rs.getDouble("SalaryHour"),
                        rs.getInt("WeeklyHour"),
                        new Department(rs.getInt("DepartmentID"), rs.getString("DepName")),
                        rs.getDouble("ControlID"));
            }
            return temp;

        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeRs(rs);
            DB.closeSt(st);
        }
    }

    @Override
    public Employee findByName(String id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

}