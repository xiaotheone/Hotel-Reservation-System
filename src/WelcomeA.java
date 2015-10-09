/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This is the Admin Panel;
 * @author Suda
 *
 */
package mainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class WelcomeA extends JFrame implements ActionListener{
    
    JFrame HomePage = new JFrame("HRS");
    JLabel welcome = new JLabel("WELCOME");
    JLabel hotel = new JLabel("TO ALPHA HOTEL");
    
    JButton check = new JButton(new ImageIcon("s.png"));
    JButton order = new JButton(new ImageIcon("B.jpg"));
    JButton bill = new JButton(new ImageIcon("P.png"));
    JButton exchange = new JButton("Price Change");

    JButton exit = new JButton("Log Out");
    JButton add = new JButton("Add Employee");
    JButton del = new JButton("Delete Empolyee");
    Container c = getContentPane();
    ImageIcon background = new ImageIcon("b1.jpg");
    JLabel back = new JLabel(background,JLabel.CENTER);
    Font font1 = new Font("Castellar", Font.BOLD + Font.ITALIC,60);
    Font font2 = new Font("AR DECODE",Font.BOLD, 40);
    
    public WelcomeA(){
        c.setLayout(null);
        
        c.add(welcome);
        welcome.setBounds(250,100,600,50);
        welcome.setFont(font1);
        welcome.setForeground(Color.black);
        
        c.add(hotel);
        hotel.setBounds(350,170,700,50);
        hotel.setFont(font2);
        hotel.setForeground(Color.black);
        
        c.add(add);
        add.setBounds(40,500,130,20 );
        add.addActionListener(this);
        
        c.add(del);
        del.setBounds(40,550,130,20);
        del.addActionListener(this);
       
        c.add(check);
        check.setBounds(450,350,250,50);
        check.addActionListener(this);
       
        c.add(order);
        order.setBounds(150,350,250,50);
        order.addActionListener(this);
        
        c.add(bill);
        bill.setBounds(750,350,250,50);
        bill.addActionListener(this);
        
        c.add(exchange);
        exchange.setBounds(40,450,130,20 );
        exchange.addActionListener(this);


        c.add(exit);
        exit.setBounds(40,600,130,20);
        exit.addActionListener(this);
        
        c.add(back).setBounds(0,0,1800,1000);
    }
        
    public void actionPerformed (ActionEvent e){
            MainMenu M= new MainMenu();
            Object source = e.getSource();
            ManagerNewReservation o = new ManagerNewReservation();
            ManagerCheckRoom c = new ManagerCheckRoom();
            addEmployee a = new addEmployee();
            deleteEmployee d = new deleteEmployee();
            ManagerCheckOut b = new ManagerCheckOut();
            PriceChange p = new PriceChange();
            
            if(source == order){
    		o.setManagerNewReservation();
    		dispose();	
            }
            if(source == exit){
                M.setLoginPage();
		dispose();
            }
            if(source == check){
                c.setManagerCheckRoom();
                dispose();
            }
            if(source == add)
            {
                a.setaddEmployee();
                dispose();
            } 
            if(source == del)
            {
                d.setdeleteEmployee();
                dispose();
            } 
            if(source == bill)
            {
                b.setManagerCheckOut();
                dispose();
            }
            if(source == exchange)
            {
                p.setPriceChange();
                dispose();
            }
    }
    
    public static void setWelcomeA ()
    {
    	WelcomeA frame = new WelcomeA();
    	frame.setTitle("Administrator Panel");
    	frame.setSize(1400,750);
        frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
