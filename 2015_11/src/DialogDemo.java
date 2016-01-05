import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class DialogDemo {
	
	JLabel jlage= new JLabel("年龄");
	JTextField jtfage = new JTextField(10);
	
	JLabel jladdress = new JLabel("地址");
	JTextField jtfaddress = new JTextField(10);
	
	JLabel jlMno = new JLabel("已购药品");
	JComboBox jcMno=null;
	
	JLabel jlAno = new JLabel("经办人");
	JComboBox jcAno =null;
	
	JLabel jlRemark = new JLabel("备注");
	JTextField jtfRemark = new JTextField(10);
	
	public DialogDemo(){
		jcMno=getComboBox("mno","medicine");
		jcAno=getComboBox("ano","agency");
		
		JPanel p1= new JPanel();
		p1.add(jlage);
		p1.add(jtfage);
		
		JPanel p2= new JPanel();
		p2.add(jladdress);
		p2.add(jtfaddress);
		
		JPanel p3= new JPanel();
		p3.add(jlMno);
		p3.add(jcMno);
		
		JPanel p4 = new JPanel();
		p4.add(jlAno);
		p4.add(jcAno);
		
		JPanel p5= new JPanel();
		p5.add(jlRemark);
		p5.add(jtfRemark);
		
		JPanel p6= new JPanel();
		p6.setLayout(new GridLayout(5,1));
		p6.add(p1);
		p6.add(p2);
		p6.add(p3);
		p6.add(p4);
		p6.add(p5);
		
		JOptionPane.showMessageDialog(null,p6,"完善用户信息",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String [] args){
		DialogDemo d = new DialogDemo();
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
				System.out.println("错误:"+e.getMessage());
			}
		
		
		return new JComboBox(content.toString().split(","));
	}
}
