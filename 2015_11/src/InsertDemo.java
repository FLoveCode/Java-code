import java.awt.*;

import javax.swing.*;

import java.sql.*;

public class InsertDemo extends JFrame{

	JComboBox jcbAno;
	JComboBox jcbMno;
	
	InsertDemo(){
		jcbAno=getComboBox("ano","agency");
		jcbMno=getComboBox("mno","medicine");
		
		JPanel p1= new JPanel();
		p1.add(jcbAno,BorderLayout.NORTH);
		p1.add(jcbMno,BorderLayout.CENTER);
		this.add(p1,BorderLayout.CENTER);
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String [] args){
		InsertDemo insert= new InsertDemo();
	}
	
	public JComboBox getComboBox(String Xno,String s){
		Connection dbConn=null;
		Statement statement=null;
		ResultSet resultset =null;
		String sql="";
		StringBuffer content = new StringBuffer();
		System.out.println("Start Connection!!");
		try{
			String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";			
			Class.forName(driverName);
				
			dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");			
			System.out.println("Connection succee!");
			
			statement=dbConn.createStatement();
			sql="select "+Xno+" from "+s;
			
			resultset=statement.executeQuery(sql);
			
			System.out.println(sql);
			while(resultset.next()){				
				System.out.println(resultset.getString(1));
				content.append(resultset.getString(1).replace(" ", "")+",");
			}
			
			resultset.close();
			statement.close();
			dbConn.close();
			
			}
			catch(Exception e){
				System.out.println("´íÎó:"+e.getMessage());
			}
		
		
		return new JComboBox(content.toString().split(","));
	}
}
