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

public class addEmployee extends JFrame implements ActionListener{
    
    JLabel l1= new JLabel ("First Name");
    JLabel l2 =new JLabel("Middle Name");
    JLabel l3 = new JLabel("Last Name");
    JLabel l4 = new JLabel("Address");
    JLabel l5 = new JLabel("Phone Number");
    JLabel l6 = new JLabel("SSN");
    JLabel l7 = new JLabel("User Name");
    JLabel l8 = new JLabel("Password");


    
    JButton b1= new JButton ("Create");
    JButton b2= new JButton ("Return");
    
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
    
    public addEmployee(){
        c.setLayout(null);
        
        c.add(l1);
        l1.setBounds(50,50,100,20);
        l1.setForeground(Color.white);
        c.add(l2);
        l2.setBounds(270,50,100,20);
        l2.setForeground(Color.white);
        c.add(l3);
        l3.setBounds(480,50,100,20);
        l3.setForeground(Color.white);
        c.add(l4);
        l4.setBounds(50,100,100,20);
        l4.setForeground(Color.white);
        c.add(l5);
        l5.setBounds(50,150,100,20);
        l5.setForeground(Color.white);
        c.add(l6);
        l6.setBounds(50,200,100,20);
        l6.setForeground(Color.white);
        c.add(l7);
        l7.setBounds(50,250,100,20);
        l7.setForeground(Color.white);
        c.add(l8);
        l8.setBounds(50,300,100,20);
        l8.setForeground(Color.white);
      
        
        c.add(t1);
        t1.setBounds(150,50,100,20);
        c.add(t2);
        t2.setBounds(350,50,100,20);
        c.add(t3);
        t3.setBounds(550,50,100,20);
        c.add(t4);
        t4.setBounds(150,100,400,20);
        c.add(t5);
        t5.setBounds(150,150,100,20);
        c.add(t6);
        t6.setBounds(150,200,100,20);
        c.add(t7);
        t7.setBounds(150,250,100,20);
        c.add(t8);
        t8.setBounds(150,300,100,20);
   

        c.add(b1);
    	b1.setBounds(220,400,100,30);
    	b1.addActionListener(this);    	
    	c.add(b2);
    	b2.setBounds(420,400,100,30);
        b2.addActionListener(this);
        
        c.add(back).setBounds(0,0,700,500);
    }
        
    public void actionPerformed (ActionEvent event){	
        Object source = event.getSource();
        Connection conn = ConnectionManager.getInstance().getConnection();
        if(source == b1){
          
              try{
      
     String SQL = "INSERT into account(FirstName,MiddleName,LastName,address,phoneNumber,SSN,UserName,Password)"
             + "VALUES(?,?,?,?,?,?,?,?)";
      String SQL1 = "SELECT * FROM account WHERE UserName =  ?";
      
      PreparedStatement stmt = conn.prepareStatement(SQL);
      PreparedStatement stmt1 = conn.prepareStatement(SQL1);
              stmt.setString(1, t1.getText());
	      stmt.setString(2, t2.getText()); 
              stmt.setString(3, t3.getText());
	      stmt.setString(4, t4.getText());
              stmt.setString(5, t5.getText());
              stmt.setString(6, t6.getText());
              stmt.setString(7, t7.getText());
              stmt.setString(8, t8.getText());
               
              stmt1.setString(1, t7.getText());
              ResultSet rs = stmt1.executeQuery();
             	if (rs.next()){
				JOptionPane.showMessageDialog(null, "User name has been taken.");
			}
			else{
				int affected = stmt.executeUpdate();
				if (affected == 1) {
					JOptionPane.showMessageDialog(null, "Your account has created and ready to use");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to create the account,please try again");
				}
			}
           }catch (SQLException e1) {
	e1.printStackTrace();
        }
        }
        if(source == b2){
            WelcomeA H = new WelcomeA();
            H.setWelcomeA();
            dispose();
        }
        
    }
    
     public static void setaddEmployee()
    {
    	addEmployee frame = new addEmployee();
    	frame.setTitle("Administrator Panel");
    	
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }       
}
