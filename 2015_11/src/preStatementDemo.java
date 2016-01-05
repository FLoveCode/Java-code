import java.awt.*;

import javax.swing.*;

import java.sql.*;

public class preStatementDemo extends JFrame{
	preStatementDemo(){
		Connection dbConn=null;
		PreparedStatement statement=null;
		ResultSet resultset =null;
		String sql="";
		
		try{
			String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";			
			Class.forName(driverName);
				
			dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");			
			System.out.println("Connection succee!");
			
			sql="insert into medicine(mno,mname,mmode,mefficacy) values(?,?,?,?)";
			
			statement=dbConn.prepareStatement(sql);
			
			statement.setString(1, "med004");
			statement.setString(2, "ÉúÃü1ºÅ");
			statement.setString(3, "ÄÚ·þ");
			statement.setString(4, "");
			//statement.addBatch();
			statement.execute();
			System.out.println(sql);
			//statement.executeBatch();

			resultset.close();
			statement.close();
			dbConn.close();
			
			}
			catch(Exception e){
				System.out.println("´íÎó:"+e.getMessage());
			}
	}
	
	public static void main(String [] args){
		preStatementDemo p = new preStatementDemo();
	}
}
