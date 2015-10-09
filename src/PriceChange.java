package mainmenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class PriceChange extends JFrame implements ActionListener{
    
    
    String size[] = {"","1-Bed","2-Bed","3-Bed"};
    String type[] = {"","Smoking","Non-Smoking"};
    
    JComboBox s = new JComboBox(size);
    JComboBox t = new JComboBox(type);
   
    JLabel LType =new JLabel("Room Type");
    JLabel LSize = new JLabel("Room Size");
    JLabel LCPrice = new JLabel("Current Price $");
    JLabel LNPrice = new JLabel("New Price $");
   
    JButton change= new JButton ("Change");
    JButton ReturnButton= new JButton ("Return");
    JButton searchButton= new JButton ("Search");
    	
    JTextField TCPrice= new JTextField ();
    JTextField TNPrice= new JTextField ();
    
    Container c = getContentPane();
    ImageIcon background = new ImageIcon("price.jpg");
    JLabel back = new JLabel(background,JLabel.CENTER);
    
    public PriceChange(){
        c.setLayout(null);
        
        c.add(LType);
        LType.setBounds(100,100,100,50);
        c.add(LSize);
        LSize.setBounds(350,100,100,50);
        c.add(LCPrice);
        LCPrice.setBounds(100,250,100,50);
        c.add(LNPrice);
        LNPrice.setBounds(100,300,100,50);
      
        c.add(t);
        t.setBounds(100,150,120,30);
        t.setSelectedIndex(0);
        t.addActionListener(this);
        c.add(s);
        s.setBounds(350,150,120,30);
        s.setSelectedIndex(0);
        s.addActionListener(this);
        c.add(TCPrice);
        TCPrice.setBounds(200,260,100,30);
        TCPrice.setEditable(false);
        c.add(TNPrice);
        TNPrice.setBounds(200,310,100,30);
        
        c.add(ReturnButton);
    	ReturnButton.setBounds(400,400,100,30);
    	ReturnButton.addActionListener(this);
        c.add(change);
    	change.setBounds(150,400,100,30);
    	change.addActionListener(this);
    	c.add(searchButton);
    	searchButton.setBounds(550,150,100,30);
    	searchButton.addActionListener(this); 
        
        c.add(back).setBounds(0,0,700,500);
    }
        
    public void actionPerformed (ActionEvent event){	
        
  Connection conn = ConnectionManager.getInstance().getConnection();
  
        Object source = event.getSource();
        WelcomeA H = new WelcomeA();
        if(source == ReturnButton){
            H.setWelcomeA();
            dispose();
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
         
                try {
       String query = "SELECT * FROM room WHERE roomType = ? AND roomSize = ?";
       PreparedStatement stmt = conn.prepareStatement(query);
       
         stmt.setString(1,(String)t.getSelectedItem());
         stmt.setString(2,(String)s.getSelectedItem());
       ResultSet rs = stmt.executeQuery();
        
       if(rs.next()){
            TCPrice.setText(rs.getString("pricePerDay"));
       }
         }catch (SQLException e1) {
	e1.printStackTrace();
        }
         
         }
     
        else if (source == change)
         {
         DecimalFormat df = new DecimalFormat("###.##");
          boolean flag = false;
          flag = isNumeric(TNPrice.getText()); 
         
          
             if(((String)t.getSelectedItem()).length() == 0)
            {
           JOptionPane.showMessageDialog(null, "You must choose a room type.");
            }
             else if(((String)s.getSelectedItem()).length() == 0)
            {
            JOptionPane.showMessageDialog(null, "You must choose a room size.");
            }
             else if(TCPrice.getText().length() == 0)
            {
            JOptionPane.showMessageDialog(null, "Hit search button to find the current price");
            }
             else if(TCPrice.getText().equals(TNPrice.getText()))
            {
            JOptionPane.showMessageDialog(null, "You have updated the price");
            }

             else if(flag == false)
            {
            JOptionPane.showMessageDialog(null, "Please enter only numeric values at New Price text field");
            } 
            else if(flag == true)
            {
               if(TNPrice.getText().substring(0,1).equals(" "))
               {
              JOptionPane.showMessageDialog(null, "New Price cannot start with a empty space");
               }
               else if(TNPrice.getText().contains(" "))
               {
              JOptionPane.showMessageDialog(null, "New Price cannot contains any space");
               }
              else
                try {
                  String number = TNPrice.getText();
                  double newNumber = Double.parseDouble(number);
                  number = df.format(newNumber);
       String query = "UPDATE room set pricePerDay = ? where roomType = ? AND roomSize = ?;";
       PreparedStatement stmt = conn.prepareStatement(query);
       
               
         stmt.setString(1,number);
         stmt.setString(2,(String)t.getSelectedItem());
         stmt.setString(3,(String)s.getSelectedItem());
          int update = stmt.executeUpdate();
        
       if(update != 0){
            TCPrice.setText(number);
            TNPrice.setText(number);
        JOptionPane.showMessageDialog(null, "You have updated the price.");
       }
       
         }catch (SQLException e1) {
	e1.printStackTrace();
         }
         }
       }
    }

     public static void setPriceChange()
    {
    	PriceChange frame = new PriceChange();
    	frame.setTitle("Administrator Panel");
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }       
       public static boolean isNumeric(String str)  
  {  
    try  
    {  
      double d = Double.parseDouble(str);  
    }
    catch(NumberFormatException nfe)  
    { 
      return false;  
    }
    return true;  
  }  

}