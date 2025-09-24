package com.example.elms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestRepository
{
    public void AddLeave(int Employee_Id,String Start_Date, String End_Date,String reason)
    {
        String sql = "INSERT INTO Leave_Requests(Employee_Id,Start_Date,End_Date,reason,status) VALUES (?,?,?,?,?)";

        try (Connection conn = Database.Connect();PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,Employee_Id);
            pstmt.setString(2,Start_Date);
            pstmt.setString(3,End_Date);
            pstmt.setString(4,reason);
            pstmt.setString(5,"PENDING");

            pstmt.executeUpdate();
            System.out.println("Leave Request Granted Successfully");
        }catch (SQLException e)
        {
            System.out.println("Error Applying Leave:"+ e.getMessage());
        }
    }

    public List<String> getAllLeaveRequests()
    {
        List<String> requests = new ArrayList<>();
        String sql = "SELECT id, Employee_Id, Start_Date,End_Date,Reason, Status FROM Leave_Requests";

        try(Connection conn = Database.Connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                String req =rs.getInt("id") +  " | EmpID: " + rs.getInt("Employee_Id") +
                        " | " + rs.getString("Start_Date") + " -> " +  rs.getString("End_Date") +
                        " | Reason: " + rs.getString("reason") + " | Status: " + rs.getString("status");

                requests.add(req);
            }
        }catch (SQLException e)
        {
            System.out.println("Error fetching leave requests: " + e.getMessage());
        }

        return requests;
    }

    public  void updateLeaveStatus(int requestId, String status)
    {
        String sql ="UPDATE Leave_Requests SET Status = ? WHERE id = ?";

        try(Connection conn = Database.Connect();PreparedStatement pstmt = conn.prepareStatement(sql))
        {
               pstmt.setString(1,status.toUpperCase());
               pstmt.setInt(2,requestId);

               int updated  = pstmt.executeUpdate();
               if (updated > 0)
               {
                   System.out.println("Leave Request Updated to: "+status);
               }
               else
               {
                   System.out.println("Leave Request not found!");
               }
        }catch (SQLException e)
        {
            System.out.println("Error updating leave status: " +e.getMessage());
        }
    }

}
