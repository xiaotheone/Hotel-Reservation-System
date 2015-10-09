package mainmenu;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Xiao
 */
public class verifyLogin {
     
    public String account;
    public String password;
    public String tempPassword;
    public String accountType;
    public String invalid = "invalid";
     MainMenu M= new MainMenu();
    public verifyLogin(String accountName,String accountPassword)
    {
      account = accountName;
      password = accountPassword;
    }
      public String Authenticate() throws SQLException
      {
           ResultSet rs = null;
           
        Connection conn = ConnectionManager.getInstance().getConnection();  
      try{
      String query = "SELECT * FROM account WHERE UserName =  ?";
       PreparedStatement stmt = conn.prepareStatement(query);
       stmt.setString(1,account);
      rs = stmt.executeQuery();
      
      if(account.length() == 0 || password.length()==0)
       JOptionPane.showMessageDialog(null, "Please enter the account name or password");
      else if(rs.next()){
        tempPassword = rs.getString("Password");
        
        if(tempPassword.equals(password)) {
         accountType = rs.getString("AccountType");
         return accountType;
      }
        else{
          JOptionPane.showMessageDialog(null, "Wrong password, please try again");
        }
        }
      else{
          
       JOptionPane.showMessageDialog(null, "Cannot find the username, please try again");
      }
      }
     catch (SQLException e) {
  System.out.println("Could not connect to the database " + e.getMessage());
    }finally{
       ConnectionManager.getInstance().close();
      }
      return invalid;
  }
      
}
