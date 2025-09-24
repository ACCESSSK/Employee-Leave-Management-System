package com.example.elms;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class LeavePanel extends JPanel
{
    private LeaveRequestRepository leaverepo;
    private JTextArea displayArea;

    public LeavePanel()
    {
        leaverepo = new LeaveRequestRepository();
        setLayout(new BorderLayout());

        JPanel ApplyForm = new JPanel(new GridLayout(5,2,5,5));
        JTextField empIdField = new JTextField();
        JTextField startField = new JTextField();
        JTextField endField = new JTextField();
        JTextField reasonField = new JTextField();
        //
        JButton Applybtn = new JButton("Apply Leave");

        ApplyForm.add(new JLabel("Empolyee Id: "));
        ApplyForm.add(empIdField);
        ApplyForm.add(new JLabel("Start Date (yyyy-mm-dd):"));
        ApplyForm.add(startField);
        ApplyForm.add(new JLabel("EndDate (yyyy-mm-dd):"));
        ApplyForm.add(endField);
        ApplyForm.add(new JLabel("Reason: "));
        ApplyForm.add(reasonField);
        ApplyForm.add(new JLabel(""));
        ApplyForm.add(Applybtn);

        JPanel ApproveForm = new JPanel(new GridLayout(2,3,5,5));
        JTextField RequestField = new JTextField();
        JButton Approvebtn = new JButton("Approve");
        JButton Rejectbtn = new JButton("Reject");

        ApproveForm.add(new JLabel("Request Id: "));
        ApproveForm.add(RequestField);
        ApproveForm.add(new JLabel(""));
        ApproveForm.add(Approvebtn);
        ApproveForm.add(Rejectbtn);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JPanel topPanel = new JPanel(new GridLayout(2,1));
        topPanel.add(ApplyForm);
        topPanel.add(ApproveForm);

        add(topPanel,BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        Applybtn.addActionListener(e ->{
            try
            {
                int empId = Integer.parseInt(empIdField.getText());
                leaverepo.AddLeave(empId, startField.getText(), endField.getText(), reasonField.getText());
                refreshRequests();

            }catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Invalid Employee Id");
            }
        });

        Approvebtn.addActionListener(e -> {
            try
            {
                int reqId = Integer.parseInt(RequestField.getText());
                leaverepo.updateLeaveStatus(reqId,"APPROVED");
                refreshRequests();
            }catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Invalid Request Id");

            }
        });
        Rejectbtn.addActionListener(e ->
        {
            try
            {
                int reqId = Integer.parseInt(RequestField.getText());
                leaverepo.updateLeaveStatus(reqId,"REJECTED");
                refreshRequests();
            }catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Invalid Request Id");

            }
        });

        refreshRequests();
    }

    private void refreshRequests()
    {
        List<String> requests = leaverepo.getAllLeaveRequests();
        displayArea.setText( "Leave Requests:\n");
        for (String req : requests)
        {
            displayArea.append(req + "\n");
        }
    }
}
