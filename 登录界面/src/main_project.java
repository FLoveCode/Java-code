import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

import jxl.write.biff.File;

public class main_project extends JFrame{
	
		//Create Menu Bar
		private JMenuBar jmb = new JMenuBar();
		private String sql= "select * from client";
		private JScrollPane scroll;
		
		//Create Menu
		private JMenu jmUser = new JMenu("用户管理");
		private JMenu jmScan = new JMenu("浏览");
		private JMenu jmChart = new JMenu("报表");
		
		//Create User Menu Items
		private JMenuItem jmiLogin = new JMenuItem("登录/注销");
		private JMenuItem jmiRegister = new JMenuItem("注册用户");
		private JMenuItem jmiPassword =  new JMenuItem("修改密码");
		private JMenuItem jmidelUser = new JMenuItem("删除用户");
		private JMenuItem jmiExit = new JMenuItem("退出");
		
		//Create Scan Menu Item
		private JMenuItem jmiCclient = new JMenuItem("客户信息");
		private JMenuItem jmiCagency = new JMenuItem("经办人信息");
		private JMenuItem jmiCmedicine = new JMenuItem("药品信息");
		
		//Create Chart Menu Item
		private JMenuItem jmiHclient = new JMenuItem("客户报表");
		private JMenuItem jmiHagency = new JMenuItem("经办人报表");
		private JMenuItem jmiHmedicine = new JMenuItem("药品报表");
		
		Login_main lm = new Login_main();
		private Text_info jpShow = new Text_info();
		private Data jpData = new Data();
		
	main_project(){
		this.setJMenuBar(jmb);
		
		jmb.add(jmUser);
		jmb.add(jmScan);
		jmb.add(jmChart);
		
		jmUser.add(jmiLogin);
		jmUser.add(jmiPassword);
		jmUser.add(jmiRegister);
		jmUser.add(jmidelUser);
		jmUser.add(jmiExit);
		
		
		jmScan.add(jmiCclient);
		jmScan.add(jmiCagency);
		jmScan.add(jmiCmedicine);
		
		jmChart.add(jmiHclient);
		jmChart.add(jmiHagency);
		jmChart.add(jmiHmedicine);
		
		jmiLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lm = new Login_main();
			}
		});
		
		jmiRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(lm.getSuper()){
				register_main rm= new register_main();
				lm.setSuper(false);
				}
				else
					JOptionPane.showMessageDialog(null, "无法注册", "注册失败", JOptionPane.ERROR_MESSAGE);
			}
		});
		jmiPassword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(!lm.getSuper()){
				if(lm.getAccount()!=null){
				String oldPassword=null;
				String newPassword=null;
				String sql="update DBuser set mpassword=? where account=?";
				Connection con=null;
				PreparedStatement pre=null;
				
				
				oldPassword=JOptionPane.showInputDialog(null,"请输入旧密码","更改密码",JOptionPane.INFORMATION_MESSAGE);
				if(oldPassword.equals(lm.getPassword().trim())){
					newPassword=JOptionPane.showInputDialog(null,"请输入新密码","更改密码",JOptionPane.INFORMATION_MESSAGE);
					con=Link_DB();
					try {
						pre=con.prepareStatement(sql);
						pre.setString(1,newPassword);
						pre.setString(2, lm.getAccount().toString());
						lm.setPassword(newPassword);
						if(pre.executeUpdate()==1)
						{
							JOptionPane.showMessageDialog(null, "修改成功");
						}
						else
							JOptionPane.showMessageDialog(null, "修改失败");
						pre.close();
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
					
				else
					JOptionPane.showMessageDialog(null, "密码错误", "更改密码", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "请先登录!!");
				}
				else
					JOptionPane.showMessageDialog(null, "管理员密码无法修改", "操作失败", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		jmidelUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(lm.getSuper()){
				String delID=null;
				Connection con=null;
				PreparedStatement pre=null;
				sql="delete from DBuser where account=?";
				
				if(lm.getSuper()){
				delID=JOptionPane.showInputDialog(null,"删除用户","请输入删除ID",JOptionPane.INFORMATION_MESSAGE);
				con=Link_DB();
				try {
					pre=con.prepareStatement(sql);
					pre.setString(1, delID);
					if(pre.executeUpdate()==1)
						JOptionPane.showMessageDialog(null, "成功删除用户："+delID);
					else
						JOptionPane.showMessageDialog(null, "删除失败 ");
					
					pre.close();
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}else
					JOptionPane.showMessageDialog(null, "无法删除用户", "删除异常", JOptionPane.ERROR_MESSAGE);
			}else
				JOptionPane.showMessageDialog(null, "非管理员用户无法执行删除");
				}
					
		});
		
		jmiExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		jmiCmedicine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(lm.getscan()){
				PageDemo pd=new PageDemo("{call Prototal_medicine(?)}","{call pro_page_medicine(?,?)}");
				}
				else
					JOptionPane.showMessageDialog(null, "没有浏览药品权限", "浏览异常", JOptionPane.ERROR_MESSAGE);
				}
		});
		
		jmiCagency.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(lm.getscan()){
				PageDemo pd=new PageDemo("{call Prototal_agency(?)}","{call pro_page_agency(?,?)}");
				}
				else
					JOptionPane.showMessageDialog(null, "没有浏览经办人权限", "浏览异常", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		jmiCclient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(lm.getscan()){
				PageDemo pd=new PageDemo("{call Prototal_client(?)}","{call pro_page_client(?,?)}");
				}
				else
					JOptionPane.showMessageDialog(null, "没有浏览客户权限", "浏览异常", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		jmiHmedicine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			if(lm.au_chart){
				JFileChooser fileChooser =new JFileChooser();
				if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					CreateXLS cXLS=new CreateXLS(fileChooser.getSelectedFile(),"select *from medicine");
					JOptionPane.showMessageDialog(null, "成功生成药品报表");
				}
				else
					JOptionPane.showMessageDialog(null, "生成药品报表失败", "生成报表", JOptionPane.ERROR_MESSAGE);
			
			}else
				JOptionPane.showMessageDialog(null, "没有生成药品报表权限", "权限异常", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		jmiHagency.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			if(lm.au_chart){
				JFileChooser fileChooser =new JFileChooser();				
				
				if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					CreateXLS cXLS=new CreateXLS(fileChooser.getSelectedFile(),"select *from agency");
					JOptionPane.showMessageDialog(null, "成功生成经办人报表");
				}
				else
					JOptionPane.showMessageDialog(null, "生成经办人报表失败", "生成报表", JOptionPane.ERROR_MESSAGE);
			
			}else
				JOptionPane.showMessageDialog(null, "没有生成经办人报表权限", "权限异常", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		jmiHclient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			if(lm.au_chart){
				JFileChooser fileChooser =new JFileChooser();
				if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					CreateXLS cXLS=new CreateXLS(fileChooser.getSelectedFile(),"select *from client");
					JOptionPane.showMessageDialog(null, "成功生成顾客报表");
				}
				else
					JOptionPane.showMessageDialog(null, "生成报表失败", "生成报表", JOptionPane.ERROR_MESSAGE);
			
			}else
				JOptionPane.showMessageDialog(null, "没有生成顾客报表权限", "权限异常", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		this.setName("医院医药系统");
		this.setSize(630, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.add(jpShow,BorderLayout.NORTH);
		this.add(jpData,BorderLayout.CENTER);
	}
	
	
	//修改sql
	public String getsql(){
		return sql;
	}
	
	public void setsql(String s){
		sql=s;
	}
	
	public  Connection Link_DB(){
		Connection dbConn=null;
		
		try{
			String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			
			Class.forName(driverName);
				
			dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");
			System.out.println("Connection succee!");
				
			}
			catch(Exception e){
				System.out.println("错误1"+e.getMessage());
			}
		
		finally{
			return dbConn;
		}
		
	}
	
	public Statement DB_result (Connection dbConn){
		Statement statement =null;
		try {
			statement= dbConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statement;
	}
	
	public ResultSet createResult (Statement statement,String s){
		ResultSet rs=null;
		try {
			rs = statement.executeQuery(s);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return rs;
	}
	
	public JTable createTable(ResultSet rs){
		Vector rows = new Vector();
		Vector columnNames = new Vector();
		
		try {
			columnNames = show_DB_column(rs);
			rows=show_DB_row(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new JTable(rows,columnNames);
	}
	
	public Vector show_DB_row(ResultSet rs ) throws SQLException{
		ResultSetMetaData rsm;
		rsm = rs.getMetaData();
		Vector rows = new Vector();
		
		while(rs.next()){
			Vector row = new Vector(rsm.getColumnCount());
			for(int i=1;i<=rsm.getColumnCount();i++)
			{
				row.add(rs.getString(i));				
			}
		rows.addElement(row);	
		}
			
		return rows;
	}
	
	public Vector show_DB_column(ResultSet rs) throws SQLException{
		ResultSetMetaData rsm= null;
		rsm = rs.getMetaData();
		Vector columnNames = new Vector();
		
		for(int i=1;i<=rsm.getColumnCount();i++){
			String test = rsm.getColumnName(i);
			columnNames.add(test);
		}
		
		return columnNames;
	
	}
	
	
	public void closeDB(ResultSet rs,Statement statement,Connection conn){
		try {
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void changeTable(){
		Connection dbConn  = Link_DB();
		Statement statement = DB_result(dbConn);
		ResultSet rs= createResult(statement,getsql());
		
		
		
		JTable Table = createTable(rs);
		Table.setShowHorizontalLines(true);
		Table.setShowVerticalLines(true);
		
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for(int i=0;i<Table.getColumnCount();i++)
		{
			Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		Table.setPreferredScrollableViewportSize(new Dimension(590,190));
		
		scroll.setViewportView(Table);
		
		closeDB(rs,statement,dbConn);
	}
	
	
//-----------------主程序入口---------------------------------------------------------------//
public static void main(String [] args){
		
		main_project mp = new main_project();
		
	}
//------------------------显示表格----------------------------------------------------------//	
	class Text_info extends JPanel{
		private JTable info;		

		Text_info(){
			Connection dbConn  = Link_DB();
			Statement statement = DB_result(dbConn);
			ResultSet rs= createResult(statement,getsql());
			
			info = createTable(rs);
			info.setShowHorizontalLines(true);
			info.setShowVerticalLines(true);
			info.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scroll = new JScrollPane(info);
			this.add(scroll,BorderLayout.CENTER);
			
			for(int i=0;i<info.getColumnCount();i++)
			{
				info.getColumnModel().getColumn(i).setPreferredWidth(150);
			}
			info.setPreferredScrollableViewportSize(new Dimension(590,190));
			
			closeDB(rs,statement,dbConn);
		}
	}	
	
//-----------------------数据处理---------------------------------------------------//	
	class Data extends JPanel{
		
		private JRadioButton jrbClient = new JRadioButton("顾客");
		private JRadioButton jrbAgency = new JRadioButton("经办人");
		private JRadioButton jrbMedicine = new JRadioButton("医药");
		private JRadioButton jrbMale = new JRadioButton("男");
		private JRadioButton jrbFemale = new JRadioButton("女");
		private JRadioButton jrbutton = new JRadioButton();
		
		private JLabel jlNo = new JLabel("编号");
		private JLabel jlName = new JLabel("姓名");
		private JLabel jlSex = new JLabel("性别");
		private JLabel jlTele = new JLabel("电话");
		
		private JTextField jtfNo = new JTextField(13);
		private JTextField jtfName = new JTextField(13);
		private JTextField jtfTele = new JTextField(13);
		
		private JButton jbSearch = new JButton("查询");
		private JButton jbInsert = new JButton("录入");
		private JButton jbDelete = new JButton("删除");
		private JButton jbAlter = new JButton("修改");
		
		public Data(){
			
			JPanel p1= new JPanel(new GridLayout(3,1));
			ButtonGroup g1 = new ButtonGroup();
			g1.add(jrbClient);
			g1.add(jrbAgency);
			g1.add(jrbMedicine);
			jrbClient.setSelected(true);
			p1.add(jrbClient);
			p1.add(jrbAgency);
			p1.add(jrbMedicine);
			this.add(p1,BorderLayout.WEST);
			
			JPanel p2 = new JPanel(new GridLayout(1,2));
			ButtonGroup g2 = new ButtonGroup();
			g2.add(jrbMale);
			g2.add(jrbFemale);
			g2.add(jrbutton);
			p2.add(jrbMale);
			p2.add(jrbFemale);
			
			JPanel p3 = new JPanel(new GridLayout(4,2,10,0));
			JPanel p5 = new JPanel(new FlowLayout());
			p5.add(jlNo);
			p5.add(jtfNo);
			JPanel p6 = new JPanel(new FlowLayout());
			p6.add(jlName);
			p6.add(jtfName);
			JPanel p7 = new JPanel(new FlowLayout());
			p7.add(jlSex);
			p7.add(p2);
			JPanel p8 = new JPanel(new FlowLayout());
			p8.add(jlTele);
			p8.add(jtfTele);
			
			p3.add(p5);
			p3.add(p6);
			p3.add(p7);
			p3.add(p8);
			this.add(p3,BorderLayout.CENTER);
			
			JPanel p4 = new JPanel(new FlowLayout(50));
			p4.add(jbSearch);
			p4.add(jbInsert);
			p4.add(jbDelete);
			p4.add(jbAlter);
			this.add(p4,BorderLayout.NORTH);
			
			ItemListener ilistener = new ItemListener(){
				public void itemStateChanged(ItemEvent e){
					if(jrbMedicine.isSelected())
					{
						jlSex.setText("服用方法");
						jrbMale.setText("外用");
						jrbFemale.setText("内服");
						jlName.setText("名称");
						jtfTele.setEnabled(false);
						setsql("select * from medicine");
					}
					else
						if(jrbClient.isSelected())
						{
							jlSex.setText("性别");
							jrbMale.setText("男");
							jrbFemale.setText("女");
							jlName.setText("姓名");
							jtfTele.setEnabled(true);
							setsql("select * from client");
						}
						else
							if(jrbAgency.isSelected())
							{
								jlSex.setText("性别");
								jrbMale.setText("男");
								jrbFemale.setText("女");
								jlName.setText("姓名");
								jtfTele.setEnabled(true);
								setsql("select * from agency");
							}
					
					changeTable();
					
					//清空输入框
					jtfNo.setText("");
					jtfName.setText("");
					jtfTele.setText("");
						
				}
			};
			
			jrbMedicine.addItemListener(ilistener);
			jrbClient.addItemListener(ilistener);
			jrbAgency.addItemListener(ilistener);
			
			
			//查询功能
			jbSearch.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					StringBuffer source = new StringBuffer();
					int flag =0;  //帮助标记
					String type="";
					String chart="";
					
					if(jrbClient.isSelected()){
						type="c";
						chart="client";
						}
					else
						if(jrbAgency.isSelected()){
							type="a";
							chart="agency";
						}
						else{
							type="m";
							chart="medicine";
						}
					source.append("select * from "+chart);
					if(!jtfNo.getText().equals(""))
					{
						source=change_text(source,flag,jtfNo.getText(),type+"no=");
						flag++;
					}
					
					if(!jtfName.getText().equals(""))
					{
						source=change_text(source,flag,jtfName.getText(),type+"name=");
						flag++;		
					}
					
					if(jrbMedicine.isSelected()){
						if(jrbMale.isSelected()){
							source=change_text(source,flag,"外用",type+"mode=");
							flag++;
						}
						if(jrbFemale.isSelected()){
							source=change_text(source,flag,"内服",type+"mode=");
							flag++;
						}
						
						jrbutton.doClick();
					}
					else
					{
						if(jrbMale.isSelected()){
							source=change_text(source,flag,"男",type+"sex=");
							flag++;
						}
						if(jrbFemale.isSelected()){
							source=change_text(source,flag,"女",type+"sex=");
							flag++;
						}
						
						jrbutton.doClick();
					}
					
					
					if(!jrbMedicine.isSelected()&&!jtfTele.getText().equals(""))
					{
						source=change_text(source,flag,jtfTele.getText(),type+"phone=");
						flag++;
					}
					
					
					setsql(source.toString());
					changeTable();
					
					//清空输入框
					jtfNo.setText("");
					jtfName.setText("");
					jtfTele.setText("");
				}
				
				
			});
						
			//录入功能
			jbInsert.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
				if(lm.getinsert()){
					Connection dbConn=null;
					PreparedStatement pre=null;
					String s=null;							
					int result=0;
					
					if(jrbMedicine.isSelected()){
						s="insert into medicine(mno,mname,mmode,mefficacy) values(?,?,?,?)";
						try {
							dbConn=Link_DB();
							String efficacy = null;
							
							pre=dbConn.prepareStatement(s);
							
							if(jtfNo.getText().equals(""))
								pre.setString(1, null);
							else
							pre.setString(1, jtfNo.getText());
							pre.setString(2, jtfName.getText());
							if(jrbMale.isSelected())
								pre.setString(3, "外用");
							else
								pre.setString(3, "内服");
							
							efficacy= JOptionPane.showInputDialog(null,"请填写功效","完善用户信息",JOptionPane.INFORMATION_MESSAGE);
							pre.setString(4, efficacy);
							
							result=pre.executeUpdate();
						
							
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(), "异常", JOptionPane.ERROR_MESSAGE);
						  }
						finally{
							try {
								pre.close();
								dbConn.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							if(result!=0)
								JOptionPane.showMessageDialog(null, "录入成功", "录入数据",JOptionPane.INFORMATION_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "录入失败", "录入数据", JOptionPane.ERROR_MESSAGE);
							
						}
					}
					else
						if(jrbAgency.isSelected()){
							s ="insert into agency(ano,aname,asex,aphone,aremark) values(?,?,?,?,?)";
							try {
								dbConn=Link_DB();
								String remark=null;
								
								pre=dbConn.prepareStatement(s);
								
								if(jtfNo.getText().equals(""))
									pre.setString(1, null);
								else
								pre.setString(1, jtfNo.getText());
								pre.setString(2, jtfName.getText());
								
								if(jrbMale.isSelected())
									pre.setString(3, "男");
								else
									pre.setString(3, "女");
								
								pre.setString(4,jtfTele.getText());
								
								remark=JOptionPane.showInputDialog(null,"请填写备注","完善用户信息",JOptionPane.INFORMATION_MESSAGE);
								pre.setString(5, remark);
								
								result=pre.executeUpdate();
								
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, e1.getMessage(), "异常", JOptionPane.ERROR_MESSAGE);
							  }
							finally{
								try {
									pre.close();
									dbConn.close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								
								if(result!=0)
									JOptionPane.showMessageDialog(null, "录入成功", "录入数据",JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "录入失败", "录入数据", JOptionPane.ERROR_MESSAGE);
							}
						}
						else
							if(jrbClient.isSelected()){
								
								s="insert into client(cno,cname,csex,cage,caddress,cphone,csympton,mno,ano,cdate,cremark) "
										+ "values(?,?,?,?,?,?,?,?,?,?,?)";
								java.util.Date ud=new java.util.Date();
								java.sql.Date sd = new java.sql.Date(ud.getTime());
								
								try {
									JLabel jlage= new JLabel("年龄");
									JTextField jtfage = new JTextField(10);
									
									JLabel jladdress = new JLabel("地址");
									JTextField jtfaddress = new JTextField(10);
									
									JLabel jlsym= new JLabel("症状");
									JTextField jtfsym = new JTextField(10);
									
									JLabel jlMno = new JLabel("已购药品");
									JComboBox jcMno=null;
									
									JLabel jlAno = new JLabel("经办人");
									JComboBox jcAno =null;
									
									JLabel jlRemark = new JLabel("备注");
									JTextField jtfRemark = new JTextField(10);
									
									jcMno=getComboBox("mno","medicine");
									jcAno=getComboBox("ano","agency");
									
									dbConn=Link_DB();
									
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
									
									JPanel p7 = new JPanel();
									p7.add(jlsym);
									p7.add(jtfsym);
									
									JPanel p6= new JPanel();
									p6.setLayout(new GridLayout(6,1));
									p6.add(p1);
									p6.add(p2);
									p6.add(p7);
									p6.add(p3);
									p6.add(p4);
									p6.add(p5);
									
									pre=dbConn.prepareStatement(s);
									
									if(jtfNo.getText().equals(""))
										pre.setString(1, null);
									else
									pre.setString(1, jtfNo.getText());
									pre.setString(2, jtfName.getText());
									
									if(jrbMale.isSelected()){
										pre.setString(3, "男");
									}
									else
										if(jrbFemale.isSelected())
											pre.setString(3, "女");
										else
											pre.setString(3, null);
									jrbutton.doClick();

									JOptionPane.showMessageDialog(null,p6,"完善用户信息",JOptionPane.INFORMATION_MESSAGE);
									
									if(!jtfage.getText().equals(""))
									pre.setInt(4, Integer.valueOf(jtfage.getText()));
									else
										pre.setInt(4, 0);
									pre.setString(5, jtfaddress.getText());
									pre.setString(6, jtfTele.getText());
									pre.setString(7, jtfsym.getText());
									pre.setString(8,jcMno.getSelectedItem().toString());
									pre.setString(9, jcAno.getSelectedItem().toString());
									pre.setDate(10, sd);
									pre.setString(11, jtfRemark.getText());
									
									result=pre.executeUpdate();
									
								} catch (SQLException e1) {
									e1.printStackTrace();
									
									JOptionPane.showMessageDialog(null, e1.getMessage(), "异常", JOptionPane.ERROR_MESSAGE);
								}
								finally{
									try {
										pre.close();
										dbConn.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									
									if(result!=0)
										JOptionPane.showMessageDialog(null, "录入成功", "录入数据",JOptionPane.INFORMATION_MESSAGE);
									else
										JOptionPane.showMessageDialog(null, "录入失败", "录入数据", JOptionPane.ERROR_MESSAGE);
									
								}
								
							}
					changeTable();
				}
				else
					JOptionPane.showMessageDialog(null, "没有录入权限","录入异常", JOptionPane.WARNING_MESSAGE);
				
					//清空输入框
					jtfNo.setText("");
					jtfName.setText("");
					jtfTele.setText("");
			}
				
					
			});
		
			//删除功能
			jbDelete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
				if(lm.getdelete()){
					StringBuffer source = new StringBuffer();
					int flag =0;  //帮助标记
					String type="";
					String chart="";
					Connection con=null;
					Statement statement=null;
					int result=0;
					
					
					if(jrbClient.isSelected()){
						type="c";
						chart="client";
						}
					else
						if(jrbAgency.isSelected()){
							type="a";
							chart="agency";
						}
						else{
							type="m";
							chart="medicine";
						}
					
					source.append("delete from "+chart);
					
					if(!jtfNo.getText().equals("")){
						source=change_text(source,flag,jtfNo.getText(),type+"no=");
						flag++;
					}
					
					if(!jtfName.getText().equals("")){
						source=change_text(source,flag,jtfName.getText(),type+"name=");
						flag++;		
					}
					
					if(jrbMedicine.isSelected()){
						if(jrbMale.isSelected()){
							source=change_text(source,flag,"外用",type+"mode=");
							flag++;
						}
						if(jrbFemale.isSelected()){
							source=change_text(source,flag,"内服",type+"mode=");
							flag++;
						}						
						jrbutton.doClick();
					}
					else
					{
						if(jrbMale.isSelected()){
							source=change_text(source,flag,"男",type+"sex=");
							flag++;
						}
						if(jrbFemale.isSelected()){
							source=change_text(source,flag,"女",type+"sex=");
							flag++;
						}
						
						jrbutton.doClick();
					}
					
					
					if(!jrbMedicine.isSelected()&&!jtfTele.getText().equals(""))
					{
						source=change_text(source,flag,jtfTele.getText(),type+"phone=");
						flag++;
					}
					
					if(flag!=0){
						if(type=="m")
							source.append(" and buy='false'");
						if(type=="a")
							source.append(" and work='false'");						
					
					try {
						con=Link_DB();
						statement=con.createStatement();
					
						result=statement.executeUpdate(source.toString());
						
					} catch (SQLException e1) {
						e1.printStackTrace();
						
					}
					finally{
						if(result==0)
							JOptionPane.showMessageDialog(null, "删除失败", "删除结果", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "成功删除"+result+"条记录", "删除结果",JOptionPane.INFORMATION_MESSAGE);
						
						try {
							statement.close();
							con.close();
							System.out.println("Test");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						changeTable();	
							
					}
					
					}
					else
						JOptionPane.showMessageDialog(null, "请输入删除条件", "删除异常", JOptionPane.ERROR_MESSAGE);
					
				}
				else
					JOptionPane.showMessageDialog(null, "没有删除权限", "删除异常", JOptionPane.WARNING_MESSAGE);
					//清空输入框
					jtfNo.setText("");
					jtfName.setText("");
					jtfTele.setText("");
				}
			});

			//修改功能
			jbAlter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				if(lm.getalter()){
					StringBuffer source = new StringBuffer();
					int flag=0;//帮助标记
					String type="";
					String chart="";
					Connection con=null;
					Statement statement=null;
					int result=0;
				
					if(jrbMedicine.isSelected()){
						String efficacy=null;
						
						type="m";
						chart="medicine";
						
						source.append("update "+chart);
						
						if(!jtfName.getText().equals(""))
						{
							source=change_text2(source,flag,jtfName.getText(),type+"name=");
							flag++;		
						}
						
						if(jrbMale.isSelected()){
							source=change_text2(source,flag,"外用",type+"mode=");
							flag++;
						}
						if(jrbFemale.isSelected()){
							source=change_text2(source,flag,"内服",type+"mode=");
							flag++;
						}						
						jrbutton.doClick();
						
						efficacy= JOptionPane.showInputDialog(null,"请填写功效","修改药品信息",JOptionPane.INFORMATION_MESSAGE);
						
						if(!efficacy.equals(""))
							source=change_text2(source,flag,efficacy,type+"efficacy=");
						
						source.append(" where "+type+"no ="+"'"+jtfNo.getText()+"'");
						
						
						con=Link_DB();
						try {
							statement=con.createStatement();
							result=statement.executeUpdate(source.toString());
							
							statement.close();
							con.close();
							
							if(result!=0)
								JOptionPane.showMessageDialog(null, "已成功修改记录", "修改结果", JOptionPane.INFORMATION_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "修改失败", "修改结果", JOptionPane.ERROR_MESSAGE);
							
							changeTable();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
					else
						if(jrbAgency.isSelected()){
							String remark=null;
							
							type="a";
							chart="agency";
							
							source.append("update "+chart);
							
							if(!jtfName.getText().equals(""))
							{
								source=change_text2(source,flag,jtfName.getText(),type+"name=");
								flag++;		
							}
							
							if(jrbMale.isSelected()){
								source=change_text2(source,flag,"男",type+"sex=");
								flag++;
							}
							if(jrbFemale.isSelected()){
								source=change_text2(source,flag,"女",type+"sex=");
								flag++;
							}
							
							jrbutton.doClick();
							
							if(!jtfTele.getText().equals("")){
								source=change_text2(source,flag,jtfTele.getText(),type+"phone=");
								flag++;
							}
							remark=JOptionPane.showInputDialog(null,"请填写备注","修改用户信息",JOptionPane.INFORMATION_MESSAGE);
							if(!remark.equals(""))
							source=change_text2(source,flag,remark,type+"remark=");
							
							source.append(" where "+type+"no ="+"'"+jtfNo.getText()+"'");
							con=Link_DB();
							
							try {
								statement=con.createStatement();
								result=statement.executeUpdate(source.toString());
								
								statement.close();
								con.close();
								
								if(result!=0)
									JOptionPane.showMessageDialog(null, "已成功修改记录", "修改结果", JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "修改失败", "修改结果", JOptionPane.ERROR_MESSAGE);
								
								changeTable();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						else
						{
							type="c";
							chart="client";
							
							source.append("update "+chart);
							
							if(!jtfName.getText().equals(""))
							{
								source=change_text2(source,flag,jtfName.getText(),type+"name=");
								flag++;		
							}
							
							if(jrbMale.isSelected()){
								source=change_text2(source,flag,"男",type+"sex=");
								flag++;
							}
							if(jrbFemale.isSelected()){
								source=change_text2(source,flag,"女",type+"sex=");
								flag++;
							}
							
							jrbutton.doClick();
							
							if(!jtfTele.getText().equals("")){
								source=change_text2(source,flag,jtfTele.getText(),type+"phone=");
								flag++;
							}
							
							JLabel jlage= new JLabel("年龄");
							JTextField jtfage = new JTextField(10);
							
							JLabel jladdress = new JLabel("地址");
							JTextField jtfaddress = new JTextField(10);
							
							JLabel jlsym= new JLabel("症状");
							JTextField jtfsym = new JTextField(10);
							
							JLabel jlMno = new JLabel("已购药品");
							JComboBox jcMno=null;
							
							JLabel jlAno = new JLabel("经办人");
							JComboBox jcAno =null;
							
							JLabel jlRemark = new JLabel("备注");
							JTextField jtfRemark = new JTextField(10);
							
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
							
							JPanel p7 = new JPanel();
							p7.add(jlsym);
							p7.add(jtfsym);
							
							JPanel p6= new JPanel();
							p6.setLayout(new GridLayout(6,1));
							p6.add(p1);
							p6.add(p2);
							p6.add(p7);
							p6.add(p3);
							p6.add(p4);
							p6.add(p5);
							
							JOptionPane.showMessageDialog(null,p6,"修改用户信息",JOptionPane.INFORMATION_MESSAGE);
							
							if(!jtfage.getText().equals("")){
								source=change_text2(source,flag,jtfage.getText(),type+"age=");
								flag++;
							}
							
							if(!jtfaddress.getText().equals("")){
								source=change_text2(source,flag,jtfaddress.getText(),type+"age=");
								flag++;
							}
							
							if(!jtfsym.getText().equals("")){
								source=change_text2(source,flag,jtfsym.getText(),type+"age=");
								flag++;
							}
							if(flag==0)
								source.append(" set ano="+"'"+jcAno.getSelectedItem().toString()+"'");
							else
							source.append(", ano="+"'"+jcAno.getSelectedItem().toString()+"'");
							source.append(" , mno="+"'"+jcMno.getSelectedItem().toString()+"'");
							
							if(!jtfRemark.getText().equals("")){
								source=change_text2(source,flag,jtfRemark.getText(),type+"remark=");
								flag++;
							}
							
							source.append(" where "+type+"no ="+"'"+jtfNo.getText()+"'");
							con=Link_DB();
							
							try {
								statement=con.createStatement();
								result=statement.executeUpdate(source.toString());
								
								statement.close();
								con.close();
								
								if(result!=0)
									JOptionPane.showMessageDialog(null, "已成功修改记录", "修改结果", JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "修改失败", "修改结果", JOptionPane.ERROR_MESSAGE);
								
								changeTable();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					System.out.println(source.toString());
					
				}
				else
					JOptionPane.showMessageDialog(null, "没有修改权限", "修改异常", JOptionPane.WARNING_MESSAGE);
					//清空输入框
					jtfNo.setText("");
					jtfName.setText("");
					jtfTele.setText("");
				}
			});
		}
		
		public StringBuffer change_text2(StringBuffer s,int flag,String app,String t){
			if(flag!=0)
				s.append(" , ");
			else
				s.append(" set ");
			
			app="'"+app+"'";
			s.append(t);
			s.append(app);
			return s;
		}
		
		public StringBuffer change_text(StringBuffer s ,int flag ,String app,String t){
			if(flag!=0)
				s.append(" and ");
			else
				s.append(" where ");
			app="'"+app+"'";
			s.append(t);
			s.append(app);
			return s;
		}
		
		public JComboBox getComboBox(String Xno,String s){
			Connection dbConn=null;
			Statement statement=null;
			ResultSet resultset =null;
			
			String sq="";
			StringBuffer content = new StringBuffer();
			try{
				String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";			
				Class.forName(driverName);
					
				dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");			
				
				statement=dbConn.createStatement();
				sq="select "+Xno+" from "+s;
				
				resultset=statement.executeQuery(sq);
			
				while(resultset.next()){				
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
	
}

