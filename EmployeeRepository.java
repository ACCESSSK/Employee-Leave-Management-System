package com.example.elms;

import java.lang.String;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository
{
    public void AddEmployee(String Name, String Email, String Department, String Role)
    {
        String sql = "INSERT INTO Employees (Name, Email, Department, Role) VALUES (?,?,?,?)";

        try(Connection conn = Database.Connect();PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1,Name);
            pstmt.setString(2,Email);
            pstmt.setString(3,Department);
            pstmt.setString(4,Role);

            pstmt.executeUpdate();
            System.out.println("Employee Added Successfully!");

        }catch(SQLException e)
        {
            System.out.println("Error Adding Employee: " + e.getMessage());
        }
    }

    public List<String> getAllEmployees()
    {
        List<String> Employees = new ArrayList<>();
        String sql = "SELECT id, Name, email, Department, Role FROM Employees";

        try(Connection conn =Database.Connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                String emp = rs.getInt("id") + " | " +
                        rs.getString("Name") + " | " +
                        rs.getString("Email") + " | " +
                        rs.getString("Department") + " | " +
                        rs.getString("Role");
                Employees.add(emp);
            }
        }catch (SQLException e)
        {
            System.out.println("Error Fething Employees: "+ e.getMessage());
        }

        return Employees;
    }
}
