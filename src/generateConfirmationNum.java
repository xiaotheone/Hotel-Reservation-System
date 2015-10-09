package mainmenu;

/*
this class Generate a unique random number between 10000 - 999999 for the future reference of customer data
 */
/**
 *
 * @author Xiao
 */

import java.util.*;
import java.sql.*;

public class generateConfirmationNum {
    
    public int getNumber() throws SQLException
    {
      int randomNumber;
      Random randomGenerator = new Random();
      randomNumber = 10000+randomGenerator.nextInt(90000);
      //generate a five digits random number
      while(checkNumber(randomNumber)!= true)
      {
          //random number range from 10000 to 99999
       randomNumber = 10000+randomGenerator.nextInt(90000);
      }
      return randomNumber;
    }
   public boolean checkNumber(int number)throws SQLException
    {
        ResultSet rs = null;
         
      Connection conn = ConnectionManager.getInstance().getConnection();  
      try{
    
   String query = "SELECT confirmationID from Customer WHERE confirmationID = ?";
   PreparedStatement stmt = conn.prepareStatement(query);
   stmt.setInt(1,number);
   rs = stmt.executeQuery();
    if(rs.next())
        return false;
    else
        return true;
    }
    catch (SQLException e) {
  System.out.println("Could not connect to the database " + e.getMessage());
    }
  return false;
  }
}
  