/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//This is the employee 
/**
 *
 * @author Suda
 */
package mainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class Welcome extends JFrame implements ActionListener{
    
    JFrame HomePage = new JFrame("HRS");
    JLabel welcome = new JLabel("WELCOME");
    JLabel hotel = new JLabel("TO ALPHA HOTEL");
    
    JButton check = new JButton(new ImageIcon("s.png"));
    JButton order = new JButton(new ImageIcon("B.jpg"));
    JButton bill = new JButton(new ImageIcon("P.png"));
    
    JButton exit = new JButton("Log Out");
    Container c = getContentPane();
    ImageIcon background = new ImageIcon("b1.jpg");
    JLabel back = new JLabel(background,JLabel.CENTER);
    Font font1 = new Font("Castellar", Font.BOLD + Font.ITALIC,60);
    Font font2 = new Font("AR DECODE",Font.BOLD, 40);
    
    public Welcome(){
        c.setLayout(null);
        
        c.add(welcome);
        welcome.setBounds(250,100,600,50);
        welcome.setFont(font1);
        welcome.setForeground(Color.black);
        
        c.add(hotel);
        hotel.setBounds(350,170,700,50);
        hotel.setFont(font2);
        hotel.setForeground(Color.black);
       
         c.add(check);
        check.setBounds(450,350,250,50);
        check.addActionListener(this);
       
        c.add(order);
        order.setBounds(150,350,250,50);
        order.addActionListener(this);
        
        c.add(bill);
        bill.setBounds(750,350,250,50);
        bill.addActionListener(this);
        
        c.add(exit);
        exit.setBounds(40,600,130,20);
        exit.addActionListener(this);
        
        c.add(back).setBounds(0,0,1800,1000);
    }
        
    public void actionPerformed (ActionEvent e){
            MainMenu M = new MainMenu();
            Object source = e.getSource();
           EmployeeNewReservation o = new EmployeeNewReservation();
            EmployeeCheckRoom c = new EmployeeCheckRoom();
            EmployeeCheckOut b = new EmployeeCheckOut();
            if(source == order){
    		o.setEmployeeNewReservation();
    		dispose();	
            }
            if(source == exit){
                M.setLoginPage();
		dispose();
            }
            if(source == check){
                c.setEmployeeCheckRoom();
                dispose();
            }
            if(source == bill){
                b.setEmployeeCheckOut();
                dispose();
            }
    }
    
    public static void setWelcomepage ()
    {
    	Welcome frame = new Welcome();
    	frame.setTitle("Employee Panel");
    	frame.setSize(1400,750);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
