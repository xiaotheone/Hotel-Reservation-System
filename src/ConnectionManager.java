/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainmenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Xiao
 */
public class ConnectionManager {
	private static ConnectionManager instance = null;

	private final String USERNAME = "root";
	//private final String PASSWORD = "a727727727";
        private final String PASSWORD = "xiaoqi8679663520";
	private final String CONN_STRING ="jdbc:mysql://127.0.0.1:3306/hotel";
	private Connection conn = null;
	
	private ConnectionManager() {
	}
	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	private boolean openConnection()
	{
		try {
                     Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		}
		catch (SQLException e) {
			System.err.println(e);
			
		}
                catch (ClassNotFoundException e) {

  System.out.println("Could not find the database driver " + e.getMessage());
    }
                return false;
	}
	public Connection getConnection()
	{
		if (conn == null) {
			if (openConnection()) {
				System.out.println("Connection opened");
				return conn;
			} else {
				return null;
			}
		}
		return conn;
        }
	public void close() {
		System.out.println("Closing connection");
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
		}
	}


}
