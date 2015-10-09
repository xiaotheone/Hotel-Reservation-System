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


public class ManagerCheckOut extends JFrame implements ActionListener{
       
    JTextField s = new JTextField();
    JTextField t = new JTextField();
    
    JLabel l1= new JLabel ("Room number");
    JLabel l2 =new JLabel("Room Type");
    JLabel l3 = new JLabel("Room Size");
    JLabel l4 = new JLabel("First Name");
    JLabel l5 = new JLabel("Middle Name");
    JLabel l6 = new JLabel("Last Name");
    JLabel l7 = new JLabel("Check-in");
    JLabel l8 = new JLabel("Check-out");
    JLabel l9 = new JLabel("Status");
    JLabel l10 = new JLabel("Amount Due");

    JButton checkOutButton= new JButton ("Check Out");
    JButton b2= new JButton ("Return");
    JButton searchButton= new JButton ("Search");
    
    JTextField t1= new JTextField ();
    JTextField t2= new JTextField ();
    JTextField t3= new JTextField ();
    JTextField t4= new JTextField ();
    JTextField t5= new JTextField ();
    JTextField t6= new JTextField ();	
    JTextField status= new JTextField ();	
    JTextField amount= new JTextField ();
    
    Container c = getContentPane();
    ImageIcon background = new ImageIcon("co.jpg");
    JLabel back = new JLabel(background,JLabel.CENTER);
    
    public ManagerCheckOut(){
        c.setLayout(null);
        
        c.add(l1);
        l1.setBounds(50,50,100,20);
        l1.setForeground(Color.white);
        c.add(l2);
        l2.setBounds(50,100,100,20);
        l2.setForeground(Color.white);
        c.add(l3);
        l3.setBounds(265,100,100,20);
        l3.setForeground(Color.white);
        c.add(l4);
        l4.setBounds(50,150,100,20);
        l4.setForeground(Color.white);
        c.add(l5);
        l5.setBounds(265,150,100,20);
        l5.setForeground(Color.white);
        c.add(l6);
        l6.setBounds(415,150,100,20);
        l6.setForeground(Color.white);
        c.add(l7);
        l7.setBounds(50,200,100,20);
        l7.setForeground(Color.white);
        c.add(l8);
        l8.setBounds(50,250,100,20);
        l8.setForeground(Color.white);
        c.add(l9);
        l9.setBounds(50,300,100,20);
        l9.setForeground(Color.white);
        c.add(l10);
        l10.setBounds(50,350,100,20);
        l10.setForeground(Color.white);
        
        c.add(t1);
        t1.setBounds(150,50,100,20);
        //t1.setEditable(false);
        c.add(t);
        t.setBounds(150,100,100,20);
        t.setEditable(false);
        c.add(s);
        s.setBounds(350,100,100,20);
        s.setEditable(false);
        c.add(t2);
        t2.setBounds(150,150,100,20);
        t2.setEditable(false);
        c.add(t3);
        t3.setBounds(350,150,50,20);
        t3.setEditable(false);
        c.add(t4);
        t4.setBounds(500,150,100,20);
        t4.setEditable(false);
        c.add(t5);
        t5.setBounds(150,200,100,20);
        t5.setEditable(false);
        c.add(t6);
        t6.setBounds(150,250,100,20);
        //t6.setEditable(false);
        c.add(status);
        status.setBounds(150,300,100,20);
        status.setEditable(false);
        c.add(amount);
        amount.setBounds(150,350,100,20);
        amount.setEditable(false);
        
        c.add(checkOutButton);
    	checkOutButton.setBounds(310,400,100,30);
    	checkOutButton.addActionListener(this);    	
    	c.add(b2);
    	b2.setBounds(460,400,100,30);
    	b2.addActionListener(this);
        c.add(searchButton);
    	searchButton.setBounds(250,50,100,20);
    	searchButton.addActionListener(this);
        
        c.add(back).setBounds(0,0,900,500);
    }
        
    public void actionPerformed (ActionEvent event){	
        
    Connection conn = ConnectionManager.getInstance().getConnection();

        Object source = event.getSource();
        if(source == b2){
            WelcomeA B = new WelcomeA();
            B.setWelcomeA();
            dispose();
        }
        else if(source == searchButton)
        {
      if(t1.getText().length() == 0)
      {
     JOptionPane.showMessageDialog(null, "Enter the Check-In room number then click the ssearch button");
      }
      else{
            
            try{
        String query = "SELECT * FROM customer where status = 'Check-In' AND roomNumber = ?";
       PreparedStatement stmt = conn.prepareStatement(query);
       
       stmt.setString(1,t1.getText());
       ResultSet rs = stmt.executeQuery();
         
         if(rs.next()){
       
           t1.setText(rs.getString("roomNumber"));
           t.setText(rs.getString("Room_Type"));
           s.setText(rs.getString("Room_Size"));
           t5.setText(rs.getString("start_date"));
           t6.setText(rs.getString("end_date"));
           t2.setText(rs.getString("first_name"));
           t3.setText(rs.getString("middle_name"));
           t4.setText(rs.getString("last_name"));
           status.setText(rs.getString("status"));
           amount.setText(rs.getString("amount"));
           
       }
       else{
          JOptionPane.showMessageDialog(null, "Sorry,cannot find the room,please try again");
        }
     
        }catch (SQLException e1) {
	e1.printStackTrace();
        }
    }
        
    }
         else if(source == checkOutButton){
           
           
           if (status.getText().equals("Check-Out"))
           {
           JOptionPane.showMessageDialog(null, "You already checked out the room");
           }
                else   if(!status.getText().equals ("Check-In") )
           {
        JOptionPane.showMessageDialog(null, "Please find a Check-In room then click Check Out button.");
           }
      
           else {
         try
           {
           String checkIn ="update customer set status = ? where roomNumber = ? AND status = ?";
            PreparedStatement stmt = conn.prepareStatement(checkIn);
             stmt.setString(1,"Check-Out");
             stmt.setString(2,t1.getText());
             stmt.setString(3,"Check-In");
             
            int checkOutStatus= stmt.executeUpdate();
             if(checkOutStatus == 1){
                 
                status.setText("Check-Out");
           JOptionPane.showMessageDialog(null, "You have successfully checked out the room.");
           
             }
           }
           catch (SQLException e1) {
	e1.printStackTrace();
           }
       }
    }
    }
    
     public static void setManagerCheckOut()
    {
    	ManagerCheckOut frame = new ManagerCheckOut();
    	frame.setTitle("Employee Panel");
    	
        frame.setSize(880,520);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
        
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }       
}