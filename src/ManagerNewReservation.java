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

public class ManagerNewReservation extends JFrame implements ActionListener{
    
    
    String size[] = {"","1-Bed","2-Bed","3-Bed"};
    String type[] = {"","Smoking","Non-Smoking"};
    
    JComboBox s = new JComboBox(size);

    JComboBox t = new JComboBox(type);
   
   
    
    JLabel LRoom= new JLabel ("Room");
    JLabel LType =new JLabel("Room Type");
    JLabel LSize = new JLabel("Room Size");
    JLabel LFName = new JLabel("First Name");
    JLabel LMName = new JLabel("Middle Name");
    JLabel LLName = new JLabel("Last Name");
    JLabel LCheckin = new JLabel("Check-in");
    JLabel LCheckout = new JLabel("Check-out");
    JLabel LPrice = new JLabel("1-day ($)");
    JLabel LCNum = new JLabel("Confirmation #");
    JLabel LPNum = new JLabel("Phone Number");

    
    JButton book_now= new JButton ("Book");
    JButton ClearAll= new JButton ("Clear");
    JButton ReturnButton= new JButton ("Return");
    JButton searchButton= new JButton ("Search");
    
    JTextField TRoom= new JTextField ();
    JTextField TCheckin= new JTextField ();
    JTextField TCheckout= new JTextField ();
    JTextField TFName= new JTextField ();
    JTextField TMName= new JTextField ();
    JTextField TLName= new JTextField ();	
    JTextField TPNum= new JTextField ();	
    JTextField TPrice= new JTextField ();
    JTextField TCNum= new JTextField ();
    
    Container c = getContentPane();
    ImageIcon background = new ImageIcon("b2.jpg");
    JLabel back = new JLabel(background,JLabel.CENTER);
    
    public ManagerNewReservation(){
        c.setLayout(null);
        
        c.add(LRoom);
        LRoom.setBounds(50,330,100,20);
        c.add(LType);
        LType.setBounds(350,330,100,20);
        c.add(LSize);
        LSize.setBounds(570,330,100,20);
        c.add(LCheckin);
        LCheckin.setBounds(50,370,100,20);
        c.add(LCheckout);
        LCheckout.setBounds(50,410,100,20);
        c.add(LFName);
        LFName.setBounds(50,450,100,20);
        c.add(LMName);
        LMName.setBounds(320,450,100,20);
        c.add(LLName);
        LLName.setBounds(540,450,100,20);
        c.add(LPNum);
        LPNum.setBounds(50,490,100,20);
        c.add(LPrice);
        LPrice.setBounds(50,530,100,20);
        c.add(LCNum);
        LCNum.setBounds(50,570,100,20);
        
        c.add(TRoom);
        TRoom.setBounds(150,330,100,20);
        TRoom.setEditable(false);
        c.add(t);
        t.setBounds(430,330,100,20);
        t.setSelectedIndex(0);
        t.addActionListener(this);
        c.add(s);
        s.setBounds(650,330,100,20);
        s.setSelectedIndex(0);
        s.addActionListener(this);
        c.add(TCheckin);
        TCheckin.setBounds(150,370,100,20);
        c.add(TCheckout);
        TCheckout.setBounds(150,410,100,20);
        c.add(TFName);
        TFName.setBounds(150,450,100,20);
        c.add(TMName);
        TMName.setBounds(420,450,50,20);
        c.add(TLName);
        TLName.setBounds(640,450,100,20);
        c.add(TPNum);
        TPNum.setBounds(150,490,100,20);
        c.add(TPrice);
        TPrice.setBounds(150,570,100,20);
        TPrice.setEditable(false);
        c.add(TCNum);
        TCNum.setBounds(150,530,100,20);
        TCNum.setEditable(false);
        
        c.add(book_now);
    	book_now.setBounds(120,620,100,30);
    	book_now.addActionListener(this);    	
    	c.add(ClearAll);
    	ReturnButton.setBounds(360,620,100,30);
    	ReturnButton.addActionListener(this);
    	c.add(ReturnButton);
    	ClearAll.setBounds(600,620,100,30);
    	ClearAll.addActionListener(this);
    	c.add(searchButton);
    	searchButton.setBounds(250,330,80,20);
    	searchButton.addActionListener(this); 
        
        c.add(back).setBounds(0,0,827,316);
    }
        
    public void actionPerformed (ActionEvent event){	
        Object source = event.getSource();
        WelcomeA H = new WelcomeA();
     Connection conn = ConnectionManager.getInstance().getConnection();
        if(source == ReturnButton){
            
            H.setWelcomeA();
             dispose();
        }
        else if(source == ClearAll){
            clear();
    	}
        else if(source == book_now){
            if((TRoom.getText()).length() ==0 ){
       JOptionPane.showMessageDialog(null,"You have not seleted a room. please find a room then continue");
            }
       else if((TCheckin.getText().length() == 0))
           {
           JOptionPane.showMessageDialog(null, "You must choose a start date.");
           }
           else if((TCheckout.getText().length() == 0))
           {
           JOptionPane.showMessageDialog(null, "You must choose a end date.");
           }
           else if((TFName.getText().length() == 0))
           {
           JOptionPane.showMessageDialog(null, "You must enter first name.");
           }
           else  if((TLName.getText().length() == 0))
           {
           JOptionPane.showMessageDialog(null, "You must enter last name.");
           }
           else if((TPNum.getText().length() == 0))
           {
           JOptionPane.showMessageDialog(null, "You must enter a phone number.");
           }
   
            else{    
         try{
     
   String SQL = "INSERT into customer(roomNumber,start_date,end_date,first_name,middle_name,last_name,phoneNumber,"
           + "amount,confirmationID,Room_size,Room_type)"
           + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        generateConfirmationNum random = new generateConfirmationNum();
         int number = random.getNumber();
         TPrice.setText(Integer.toString(number));
         PreparedStatement stmt = conn.prepareStatement(SQL);
         stmt.setString(1,TRoom.getText());
         stmt.setString(2, TCheckin.getText());
         stmt.setString(3,TCheckout.getText());
         stmt.setString(4,TFName.getText());
         stmt.setString(5,TMName.getText());
         stmt.setString(6,TLName.getText());
         stmt.setString(7,TPNum.getText());
         //t9 is the pay text field
         stmt.setString(8,TCNum.getText());
         //t8 is the confirmation Number text field
         stmt.setString(9,TPrice.getText());
         stmt.setString(10,(String)s.getSelectedItem());
         stmt.setString(11,(String)t.getSelectedItem());
         int affected = stmt.executeUpdate();
         if(affected == 1){
       JOptionPane.showMessageDialog(null, "You have successfully booked a room. Your confirmation # is "+ number);
        H.setWelcomeA();
         }
           }catch (SQLException e1) {
	e1.printStackTrace();
        }
     }
    }
        else if (source == searchButton)
        {
          if(((String)t.getSelectedItem()).length() == 0)
                  {
                  JOptionPane.showMessageDialog(null, "You must choose a room type.");
                  }
          else if(((String)s.getSelectedItem()).length() == 0)
          {
           JOptionPane.showMessageDialog(null, "You must choose a room size.");
          }
          else if(TCheckin.getText().length() == 0){
             JOptionPane.showMessageDialog(null, "You must choose a start date.");
          }
          else if(TCheckout.getText().length() == 0){
             JOptionPane.showMessageDialog(null, "You must choose a end date.");
          }
            else{
        
      String SQL1 = "SELECT roomNumber,pricePerDay FROM room WHERE roomSize =? AND roomType = ? AND "
              + "roomNumber NOT IN (SELECT roomNumber FROM customer "
            + "WHERE status ='Reserved' OR status = 'Check-In' AND NOT (end_date   < '2012-12-14'OR start_date > '2012-12-15')) LIMIT 1";
       try{
          PreparedStatement stmt = conn.prepareStatement(SQL1);
         stmt.setString(1,(String)s.getSelectedItem());
         stmt.setString(2,(String)t.getSelectedItem());
         //stmt.setString(3, TCheckin.getText());
         //stmt.setString(4, TCheckout.getText());
                
         ResultSet rs = stmt.executeQuery();
          if(rs.next())
          {
          TRoom.setText(rs.getString("roomNumber"));
          TCNum.setText(rs.getString("pricePerDay"));
          }
          else
          {
          JOptionPane.showMessageDialog(null, "No available room for given interval date. ");
          }
       }catch (SQLException e1) {
	e1.printStackTrace();
        }
    }
    
    }
    }
    private void clear(){
        TRoom.setText("");
	TCheckin.setText(""); 
	TCheckout.setText(""); 
	TFName.setText("");
	TMName.setText(""); 
	TLName.setText(""); 
        TPNum.setText(""); 
        TPrice.setText(""); 
	s.setSelectedItem(size[0]);
        t.setSelectedItem(type[0]);
	TCNum.setText("");
    }
     public static void setManagerNewReservation()
    {
    	ManagerNewReservation frame = new ManagerNewReservation();
    	frame.setTitle("Administrator Panel");
    	frame.setSize(827,700);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }       
}