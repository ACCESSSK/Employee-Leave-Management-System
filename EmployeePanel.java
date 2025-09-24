package com.example.elms;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmployeePanel extends JPanel
{
    private EmployeeRepository repo;
    private JTextArea displayArea;

    public EmployeePanel()
    {
        repo = new EmployeeRepository();
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField roleField = new JTextField();
        JButton addButton = new JButton();

        form.add(new JLabel("Name:"));
        form.add(new JLabel("Email:"));
        form.add(new JLabel("Depatment:"));
        form.add(new JLabel("Role:"));

        form.add(nameField);
        form.add(emailField);
        form.add(deptField);
        form.add(roleField);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(displayArea),BorderLayout.CENTER);

        addButton.addActionListener(e ->
        {
            repo.AddEmployee(nameField.getText(), emailField.getText(),deptField.getText(),roleField.getText());
            refreshEmployees();

        });
    }

    private void refreshEmployees()
    {
        List<String> employees = repo.getAllEmployees();
        displayArea.setText("");
        for (String emp : employees)
        {
            displayArea.append(emp + "\n");
        }

    }
    
}
