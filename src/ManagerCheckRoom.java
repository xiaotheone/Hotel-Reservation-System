/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suda
 */
package mainmenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;


public class ManagerCheckRoom extends JFrame implements ActionListener{
    
    
    JTextField s = new JTextField();
    JTextField t = new JTextField();
    
    JLabel l1= new JLabel ("Room");
    JLabel l2 =new JLabel("Room Type");
    JLabel l3 = new JLabel("Room Size");
    JLabel l4 = new JLabel("First Name");
    JLabel l5 = new JLabel("Middle Name");
    JLabel l6 = new JLabel("Last Name");
    JLabel l7 = new JLabel("Check-in");
    JLabel l8 = new JLabel("Check-out");
    JLabel l9 = new JLabel("Status");
    JLabel l10 = new JLabel("Confirm Number");
    JLabel l11 = new JLabel("Phone Number");

    
    JButton BDelete= new JButton ("Cancel");
    JButton checkInButton= new JButton ("Check-in");
    JButton returnButton= new JButton ("Return");
    JButton searchButton= new JButton ("Search");
    JButton updateButton = new JButton ("Update");
    
    JTextField t1= new JTextField ();
    JTextField t2= new JTextField ();
    JTextField t3= new JTextField ();
    JTextField t4= new JTextField ();
    JTextField t5= new JTextField ();
    JTextField t6= new JTextField ();	
    JTextField t7= new JTextField ();	
    JTextField confirmNumber= new JTextField ();
    JTextField t9= new JTextField ();
    
    Container c = getContentPane();
    ImageIcon background = new ImageIcon("k11.jpg");
    JLabel back = new JLabel(background,JLabel.CENTER);
    
    public ManagerCheckRoom(){
        c.setLayout(null);
        
        c.add(l1);
        l1.setBounds(330,90,100,20);
        c.add(l2);
        l2.setBounds(470,90,100,20);
        c.add(l3);
        l3.setBounds(640,90,100,20);
        c.add(l7);
        l7.setBounds(330,140,100,20);
        c.add(l8);
        l8.setBounds(330,190,100,20);
        c.add(l4);
        l4.setBounds(330,240,100,20);
        c.add(l5);
        l5.setBounds(520,240,100,20);
        c.add(l6);
        l6.setBounds(680,240,100,20);
        c.add(l11);
        l11.setBounds(330,290,100,20);
        c.add(l9);
        l9.setBounds(330,340,100,20);
        c.add(l10);
        l10.setBounds(330,40,100,20);
        
        c.add(t1);
        t1.setBounds(370,90,80,20);
        t1.setEditable(false);
        c.add(t);
        t.setBounds(540,90,80,20);
        t.setEditable(false);
        c.add(s);
        s.setBounds(710,90,80,20);
        s.setEditable(false);
        c.add(t2);
        t2.setBounds(400,140,100,20);
        t2.setEditable(false);
        c.add(t3);
        t3.setBounds(400,190,100,20);
        t3.setEditable(false);
        c.add(t4);
        t4.setBounds(400,240,100,20);
        //t4.setEditable(false);
        c.add(t5);
        t5.setBounds(600,240,50,20);
        //t5.setEditable(false);
        c.add(t6);
        t6.setBounds(750,240,100,20);
        //t6.setEditable(false);
        c.add(t7);
        t7.setBounds(430,290,100,20);
        //t7.setEditable(false);
        c.add(confirmNumber);
        confirmNumber.setBounds(430,40,100,20);
        c.add(t9);
        t9.setBounds(400,340,100,20);
        t9.setEditable(false);
        
        c.add(checkInButton);
    	checkInButton.setBounds(307,400,100,30);
    	checkInButton.addActionListener(this);    	
    	c.add(BDelete);
    	BDelete.setBounds(457,400,100,30);
    	BDelete.addActionListener(this);
        c.add(updateButton);
    	updateButton.setBounds(607,400,100,30);
    	updateButton.addActionListener(this);
    	c.add(returnButton);
    	returnButton.setBounds(757,400,100,30);
    	returnButton.addActionListener(this);
    	c.add(searchButton);
    	searchButton.setBounds(530,40,80,20);
    	searchButton.addActionListener(this); 
        
        c.add(back).setBounds(0,0,300,500);
    }
        
    public void actionPerformed (ActionEvent event){	
        
     Connection conn = ConnectionManager.getInstance().getConnection();
        Object source = event.getSource();
        
        WelcomeA H = new WelcomeA();
        if(source == returnButton){
            
            H.setWelcomeA();
            dispose();
        }
        else if (source == searchButton){
            
           if(confirmNumber.getText().length() == 0)
             {
            JOptionPane.showMessageDialog(null, "Please enter a valid confiramtion number");
             }
              else{
   
                try {
         String query = "SELECT * FROM customer WHERE confirmationID = ?";
       PreparedStatement stmt = conn.prepareStatement(query);
       stmt.setString(1,confirmNumber.getText());
       ResultSet rs = stmt.executeQuery();
        
       if(rs.next()){
       
            t1.setText(rs.getString("roomNumber"));
            t.setText(rs.getString("Room_Type"));
            s.setText(rs.getString("Room_Size"));
           t2.setText(rs.getString("start_date"));
           t3.setText(rs.getString("end_date"));
           t4.setText(rs.getString("first_name"));
           t5.setText(rs.getString("middle_name"));
           t6.setText(rs.getString("last_name"));
           t7.setText(rs.getString("phoneNumber"));
            t9.setText(rs.getString("status"));
           
       }
       else{
          JOptionPane.showMessageDialog(null, "sorry,cannot find the reservation,please try again");
        }
   
         }catch (SQLException e1) {
	e1.printStackTrace();
        }
         
         }
        }
        else if(source == updateButton)
        {
         if((confirmNumber.getText()).length()== 0)
           {
        JOptionPane.showMessageDialog(null, "Please enter the reservation number.");
           }
      else if((t9.getText().length()== 0)){
                   
         JOptionPane.showMessageDialog(null, "record is not found" );
                   
           }
         
      else{
         try{
             
        String update = "update customer set first_name =?,middle_name = ?,last_name = ?,"
                + "phoneNumber = ? where confirmationID = ? ";
          PreparedStatement stmt = conn.prepareStatement(update);
          stmt.setString(1,t4.getText());
          stmt.setString(2,t5.getText());
          stmt.setString(3,t6.getText());
          stmt.setString(4,t7.getText());
          stmt.setString(5,confirmNumber.getText());
          
     
          int affected = stmt.executeUpdate();
        
         if(affected == 1){
         JOptionPane.showMessageDialog(null, "Your information has been updated.");
      
         }
       
        }catch (SQLException e1) {
	e1.printStackTrace();
    }
     }
    }
       else if(source == checkInButton){
           
           if((confirmNumber.getText()).length()== 0)
           {
        JOptionPane.showMessageDialog(null, "Please enter the reservation number.");
           }
           else if (!(t9.getText().equals("Reserved")))
           {
           JOptionPane.showMessageDialog(null, "cannot check you in at this time because this reservation is "+ t9.getText());
           }
       
           
           else {
         try
           {
           String checkIn ="update customer set status = ? where confirmationID = ?";
            PreparedStatement stmt = conn.prepareStatement(checkIn);
             stmt.setString(1,"Check-In");
             stmt.setString(2,confirmNumber.getText());
             
            int checkInStatus = stmt.executeUpdate();
             if(checkInStatus == 1){
                 
                t9.setText("Check-In");
           JOptionPane.showMessageDialog(null, "You have successfully checked in.");
           
             }
           }
           catch (SQLException e1) {
	e1.printStackTrace();
           }
       }
       }
         else if(source == BDelete){
             
          if((confirmNumber.getText()).length()== 0)
           {
         JOptionPane.showMessageDialog(null, "Please enter a valid confiramtion number");
           }
    
           else if (!(t9.getText().equals("Reserved")))
           {
           JOptionPane.showMessageDialog(null, "The reservation canno be canceled because this reservation is "+ t9.getText());
           }
          
           else {
         try
           {
           String checkIn ="update customer set status = ? where confirmationID = ?";
            PreparedStatement stmt = conn.prepareStatement(checkIn);
             stmt.setString(1,"Canceled");
             stmt.setString(2,confirmNumber.getText());
             
            int checkOutStatus = stmt.executeUpdate();
             if(checkOutStatus == 1){
                  t9.setText("Canceled");
           JOptionPane.showMessageDialog(null, "You have successfully canceled the reservation.");
             }
           }
           catch (SQLException e1) {
	e1.printStackTrace();
           }
       }
       
       }
    }
     public static void setManagerCheckRoom()
    {
    	ManagerCheckRoom frame = new ManagerCheckRoom();
    	frame.setTitle("Administrator Panel");
        frame.setSize(880,520);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }       
}
