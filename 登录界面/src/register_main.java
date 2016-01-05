import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class register_main extends JFrame{
	JButton jbok = new JButton("确定");
	JButton jbcancel = new JButton("取消");
	
	JLabel Laccount = new JLabel("账号：");
	JLabel Lpassword = new JLabel("密码：");
	
	JTextField Taccount = new JTextField(15);
	JTextField Tpassword = new JTextField(15);
	
	JCheckBox jcinsert = new JCheckBox("录入");
	JCheckBox jcdelete = new JCheckBox("删除");
	JCheckBox jcalter = new JCheckBox("修改");
	JCheckBox jcscan = new JCheckBox("浏览");
	JCheckBox jcchart = new JCheckBox("报表");
	
	register_main(){
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.setLayout(new FlowLayout());
		p1.add(Laccount);
		p1.add(Taccount);
		
		p2.setLayout(new FlowLayout());
		p2.add(Lpassword);
		p2.add(Tpassword);
		
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(p1);
		p3.add(p2);
		
		jcinsert.setMnemonic(KeyEvent.VK_I);
		jcdelete.setMnemonic(KeyEvent.VK_D);
		jcalter.setMnemonic(KeyEvent.VK_A);
		jcscan.setMnemonic(KeyEvent.VK_L);
		jcchart.setMnemonic(KeyEvent.VK_C);
		
		JPanel p4=new JPanel();
		p4.setBorder(new TitledBorder("请选择用户权限"));
		p4.add(jcinsert);
		p4.add(jcdelete);
		p4.add(jcalter);
		p4.add(jcscan);
		p4.add(jcchart);
		
		JPanel p5=new JPanel();
		p5.add(jbok);
		p5.add(jbcancel);
		
		JPanel p6=new JPanel();
		p6.add(p4);
		p6.add(p5);
		
		this.setLayout(new GridLayout(2,1));
		this.add(p3);
		this.add(p6);
		
		this.setName("用户注册");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//添加用户注册
		jbok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				String acount=null;
				String password=null;
				boolean au_insert=false;
				boolean au_delete=false;
				boolean au_alter=false;
				boolean au_scan=false;
				boolean au_chart=false;
				String sql="insert into DBuser values(?,?,?,?,?,?,?)";
				String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				Connection dbConn=null;
				PreparedStatement statement=null;
				int result=0;
				
				acount=Taccount.getText().toString().trim();
				password=Tpassword.getText().toString().trim();
				
				if(jcinsert.isSelected())
					au_insert=true;
				if(jcdelete.isSelected())
					au_delete=true;
				if(jcalter.isSelected())
					au_alter=true;
				if(jcscan.isSelected())
					au_scan=true;
				if(jcchart.isSelected())
					au_chart=true;
				
				try {
					try {
						Class.forName(driverName);
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					}
						dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");
						statement=dbConn.prepareStatement(sql);
						
						statement.setString(1, acount);
						statement.setString(2, password);
						statement.setBoolean(3, au_delete);
						statement.setBoolean(4, au_alter);
						statement.setBoolean(5, au_insert);
						statement.setBoolean(6, au_chart);
						statement.setBoolean(7, au_scan);
						
						
						result=statement.executeUpdate();
						
						statement.close();
						dbConn.close();
						JOptionPane.showMessageDialog(null, "注册成功", "注册结果", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						
				} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				} 
				
			
		});
		
		jbcancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	}
	
	
	
	
}
