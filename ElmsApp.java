package com.example;

import com.example.elms.*;

import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ElmsApp extends JFrame
{
    public ElmsApp()
    {
        setTitle("Employee Leave Management System");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Employees",new EmployeePanel());
        tabs.add("Leave Requests",new LeavePanel());

        add(tabs, BorderLayout.CENTER);
    }

    public  static void main(String[] A)
    {
        Database.intialize();

        SwingUtilities.invokeLater(() ->
        {
            new ElmsApp().setVisible(true);
        });
    }
}