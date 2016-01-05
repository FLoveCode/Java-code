import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Login_main extends JFrame{
	
	JLabel lAccount = new JLabel("ÕËºÅ£º");
	JLabel lPassword = new JLabel("ÃÜÂë£º"); 
	JTextField tAccount = new JTextField(15);
	JTextField tPassword = new JTextField(15);
	JButton loginbn= new JButton("µÇÂ¼");
	JButton cancelbn = new JButton("È¡Ïû");
	JLabel welcome = new JLabel("»¶Ó­");
	boolean [] authority=new boolean[5];
	String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	Connection dbConn=null;
	PreparedStatement pre=null;
	ResultSet rs=null;
	String Name=null;
	String Password=null;
	boolean Super=false;
	
	boolean au_insert=false;
	boolean au_delete=false;
	boolean au_alter=false;
	boolean au_chart=false;
	boolean au_scan=false;
	
	Login_main(){
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(lAccount);
		p1.add(tAccount);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(lPassword);
		p2.add(tPassword);
		
		JPanel p3= new JPanel();
		p3.setLayout(new GridLayout(1,2));
		p3.add(loginbn);
		p3.add(cancelbn);
		
		JPanel p4= new JPanel();
		p4.add(welcome);
		
		JPanel p5= new JPanel();
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		
		this.add(p4,BorderLayout.NORTH);
		this.add(p5,BorderLayout.CENTER);
		
		cancelbn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		loginbn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Name=tAccount.getText().toString();
				Password=tPassword.getText().toString();
				
				if(tAccount.getText().toString().equals("SuperIP")&&
						tPassword.getText().toString().equals("395201314"))
				{
					Super=true;
					au_insert=true;
					au_delete=true;
					au_alter=true;
					au_scan=true;
					au_chart=true;
					dispose();
					JOptionPane.showMessageDialog(null, "»¶Ó­³¬¼¶¹ÜÀíÔ±µÇÂ¼","µÇÂ¼³É¹¦",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
				try {
					Class.forName(driverName);
					try {
						String sql="select au_delete,au_alter,au_insert,au_chart,au_scan from DBuser where account=? and mpassword=?";
						dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");
						pre=dbConn.prepareStatement(sql);
						
						pre.setString(1, tAccount.getText());
						pre.setString(2, tPassword.getText());
						
						rs=pre.executeQuery();
						
						if(rs.next()){
							au_delete=rs.getBoolean(1);
							au_alter=rs.getBoolean(2);
							au_insert=rs.getBoolean(3);
							au_chart=rs.getBoolean(4);
							au_scan=rs.getBoolean(5);
							Super=false;
							dispose();
							JOptionPane.showMessageDialog(null, "»¶Ó­µÇÂ¼","µÇÂ¼³É¹¦",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							dispose();
							JOptionPane.showMessageDialog(null, "ÃÜÂë»òÕËºÅ´íÎó","µÇÂ¼Ê§°Ü", JOptionPane.ERROR_MESSAGE);
						}	
							rs.close();
							pre.close();
							dbConn.close();
						
					} catch (SQLException e1) {
						//
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			}
		 }
		});
		
		this.setAlwaysOnTop(true);
		this.setName("ÓÃ»§µÇÂ¼");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public String getAccount(){
		return Name;
	}
	public String getPassword(){
		return Password;
	}
	public boolean getalter(){
		return au_alter;
	}
	public boolean getdelete(){
		return au_delete;
	}
	public boolean getinsert(){
		return au_insert;
	}
	public boolean getscan(){
		return au_scan;
	}
	public boolean getchart(){
		return au_chart;
	}
	public boolean getSuper(){
		return Super;
	}
	public void setPassword(String p){
		Password=p;
	}
	public void setSuper(boolean s){
		Super=s;
	}
	
}
