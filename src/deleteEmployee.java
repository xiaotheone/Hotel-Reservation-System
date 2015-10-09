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

public class deleteEmployee extends JFrame implements ActionListener{
    
    JLabel l1= new JLabel ("First Name");
    JLabel l2 =new JLabel("Middle Name");
    JLabel l3 = new JLabel("Last Name");
    JLabel l4 = new JLabel("Address");
    JLabel l5 = new JLabel("Phone Number");
    JLabel l6 = new JLabel("SSN");
    JLabel l7 = new JLabel("User Name");
    JLabel l8 = new JLabel("Password");


    
    JButton deleteButton= new JButton ("Delete");
    JButton returnButton= new JButton ("Return");
    JButton searchButton= new JButton ("Search");
    
    JTextField t1= new JTextField ();
    JTextField t2= new JTextField ();
    JTextField t3= new JTextField ();
    JTextField t4= new JTextField ();
    JTextField t5= new JTextField ();
    JTextField t6= new JTextField ();	
    JTextField t7= new JTextField ();	
    JTextField t8= new JTextField ();
   
    
    Container c = getContentPane();
    ImageIcon background = new ImageIcon("b4.jpg");
    JLabel back = new JLabel(background,JLabel.CENTER);
    
    public deleteEmployee(){
        c.setLayout(null);
        
        c.add(l1);
        l1.setBounds(50,100,100,20);
        l1.setForeground(Color.white);
        c.add(l2);
        l2.setBounds(270,100,100,20);
        l2.setForeground(Color.white);
        c.add(l3);
        l3.setBounds(480,100,100,20);
        l3.setForeground(Color.white);
        c.add(l4);
        l4.setBounds(50,150,100,20);
        l4.setForeground(Color.white);
        c.add(l5);
        l5.setBounds(50,200,100,20);
        l5.setForeground(Color.white);
        c.add(l6);
        l6.setBounds(50,250,100,20);
        l6.setForeground(Color.white);
        c.add(l7);
        l7.setBounds(50,50,100,20);
        l7.setForeground(Color.white);
        c.add(l8);
        l8.setBounds(50,300,100,20);
        l8.setForeground(Color.white);
      
        
        c.add(t1);
        t1.setBounds(150,100,100,20);
        c.add(t2);
        t2.setBounds(350,100,100,20);
        c.add(t3);
        t3.setBounds(550,100,100,20);
        c.add(t4);
        t4.setBounds(150,150,400,20);
        c.add(t5);
        t5.setBounds(150,200,100,20);
        c.add(t6);
        t6.setBounds(150,250,100,20);
        c.add(t7);
        t7.setBounds(150,50,100,20);
        c.add(t8);
        t8.setBounds(150,300,100,20);
   

        c.add(deleteButton);
    	deleteButton.setBounds(220,400,100,30);
    	deleteButton.addActionListener(this);    	
    	c.add(returnButton);
    	returnButton.setBounds(420,400,100,30);
        returnButton.addActionListener(this);
        c.add(searchButton);
    	searchButton.setBounds(252,50,100,20);
    	searchButton.addActionListener(this);
        
        c.add(back).setBounds(0,0,700,500);
    }
        
    public void actionPerformed (ActionEvent event){	
        Object source = event.getSource();
     Connection conn = ConnectionManager.getInstance().getConnection();
     
        if(source == returnButton){
            WelcomeA H = new WelcomeA();
            H.setWelcomeA();
            dispose();
        }
        else if (source == searchButton){
            
            if(t7.getText().length() == 0)
            {
    JOptionPane.showMessageDialog(null, "Enter an user name,then hit the search button");
            }
            else{
            try{
       String query = "SELECT * FROM account WHERE userName= ?";
       PreparedStatement stmt = conn.prepareStatement(query);
       stmt.setString(1,t7.getText());
       ResultSet rs = stmt.executeQuery();
             
         if(rs.next())
         {
         t1.setText(rs.getString("FirstName"));
         t2.setText(rs.getString("MiddleName"));
         t3.setText(rs.getString("LastName"));
         t4.setText(rs.getString("address"));
         t5.setText(rs.getString("PhoneNumber"));
         t6.setText(rs.getString("SSN"));
         t8.setText(rs.getString("Password"));
         }
         else{
         JOptionPane.showMessageDialog(null, "Cannot find the account name "
                 + t7.getText()+", please try again");
         }
            
            }catch (SQLException e1) {
	e1.printStackTrace();
        }
      
        }
        }
         else if (source ==  deleteButton){
             
             if(t7.getText().length()==0)
             {
           JOptionPane.showMessageDialog(null, "You have not selected an account to delete");
             }
             else if(t7.getText().equals("admin"))
             {
             JOptionPane.showMessageDialog(null, "You can not delete the admin account" );
             }
             else
             {
             try{
                 
           String find = "SELECT userName from account WHERE userName= ?";
          PreparedStatement stmt1 = conn.prepareStatement(find);
           stmt1.setString(1,t7.getText());
           ResultSet rs = stmt1.executeQuery();     
            if(rs.next()) 
            {
        String delete ="update account set userName = ? where userName  = ?";
            PreparedStatement stmt2 = conn.prepareStatement(delete);
             stmt2.setString(1,"");
             stmt2.setString(2,t7.getText());
             
            int checkInStatus = stmt2.executeUpdate();
             if(checkInStatus == 1){
           JOptionPane.showMessageDialog(null, "You have successfully deleted the account: "+t7.getText());
             }
           }
            else{
             JOptionPane.showMessageDialog(null, "User name: "+t7.getText()+ " does not exist");
            }
         }
           catch (SQLException e1) {
	e1.printStackTrace();
           }
       }
    
    }
    }
     public static void setdeleteEmployee()
    {
    	deleteEmployee frame = new deleteEmployee();
    	frame.setTitle("Administrator Panel");
        
    	frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
        
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }       
}
