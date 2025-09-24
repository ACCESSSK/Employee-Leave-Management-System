package com.example.elms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
    private static final String URL = "jdbc:sqlite:elms.db";

    public static Connection Connect() throws SQLException
    {
        return DriverManager.getConnection(URL);
    }

    public static void intialize()
    {
        try(Connection conn = Connect(); Statement stmt = conn.createStatement()) {
            String createEmployees = "CREATE TABLE IF NOT EXISTS Employees (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Name TEXT NOT NULL," +
                    "Email TEXT UNIQUE NOT NULL," +
                    "Department TEXT," +
                    "Role TEXT" +
                    ");";

            String createLeaveRequests = "CREATE TABLE IF NOT EXISTS Leave_Requests (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Employee_Id INTEGER, " +
                    "Start_Date TEXT, " +
                    "End_Date TEXT, " +
                    "reason TEXT, " +
                    "status TEXT, " +
                    "FOREIGN KEY(employee_id) REFERENCES Employees(id)" +
                    ");";

            stmt.execute(createEmployees);
            stmt.execute(createLeaveRequests);

            System.out.println("Database Initialised Successfully!");

        }catch (SQLException e)
        {
            System.out.println("Error initialising Database: " +e.getMessage());
        }
    }

}
