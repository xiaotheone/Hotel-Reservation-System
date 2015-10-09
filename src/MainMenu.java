/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainmenu;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
    public static void main(String[] args) {
        setLoginPage();
    }
  
    JFrame LoginPage = new JFrame("User Login");
    JLabel user = new JLabel("Username");
    JLabel password = new JLabel("Password");
    JTextField userField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton Enter = new JButton(new ImageIcon("ok.jpeg"));
    JButton Exit = new JButton(new ImageIcon("eks.jpeg"));
    public String loginname="";
    public String loginpass="";
    public String users="";
    public String pass="";
        
    public static void setLoginPage(){
        MainMenu A = new MainMenu();
        A.setSize(300,150);
        //A.setLocation(500,300);
         
         A.setLocationRelativeTo(null);
        A.setVisible(true);
        A.setResizable(false);
    }
    
    public MainMenu() 
    {
        super("User Login");
        JPanel p = new JPanel();
        p.setLayout(null);
        
        p.setBackground(Color.blue);
        user.setForeground(Color.white);
        password.setForeground(Color.white);
        
        p.add(user);
        user.setBounds(40,10,80,20);
        p.add(userField);
        userField.setBounds(120,10,100,20);
        
        p.add(password);
        password.setBounds(40,35,80,20);
        p.add(passwordField);
        passwordField.setBounds(120,35,100,20);
        
        p.add(Enter);
        Enter.setBounds(100,65,40,35);
        Enter.addActionListener(this);
        
        p.add(Exit);
        Exit.setBounds(165,65,40,35);
        Exit.addActionListener(this);
        
        Enter.setToolTipText("Log-in");
        Exit.setToolTipText("Exit");
        
        setContentPane(p);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ""));
    }
    
    public void actionPerformed(ActionEvent e) {
    	Object source=e.getSource();
    	 if(source==Enter){
            String userName=userField.getText();
            String password=new String(passwordField.getPassword());
         try{
            verifyLogin verify = new verifyLogin(userName,password);
            String UserType = verify.Authenticate();
            //String UserType = Authenticate(userName,password);
            
            if(UserType.equals("Admin")){
                WelcomeA w = new WelcomeA();
                w.setWelcomeA();
                JOptionPane.showMessageDialog(null, "Login as an admin ");
                dispose();
            }
            else if(UserType.equals("Employee")){  
                Welcome wel = new Welcome();
                wel.setWelcomepage();
                JOptionPane.showMessageDialog(null, "Login as an employee ");
                dispose();
            }
    }
   catch (SQLException e1) {
   System.out.println("Could not connect to the database " + e1.getMessage());
                 }
         }
            if(source == Exit){
           dispose();
        }
    }
}



