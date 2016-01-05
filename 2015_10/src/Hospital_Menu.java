import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Hospital_Menu extends JFrame{

	//Create Menu Bar
	private JMenuBar jmb = new JMenuBar();
	
	//Create Menu
	private JMenu jmUser = new JMenu("用户管理");
	private JMenu jmSearch = new JMenu("查询");
	private JMenu jmInsert = new JMenu("录入");
	private JMenu jmDelete = new JMenu("删除");
	private JMenu jmAlter = new JMenu("修改");
	private JMenu jmScan = new JMenu("浏览");
	private JMenu jmChart = new JMenu("报表");
	
	//Create User Menu Items
	private JMenuItem jmiLogin = new JMenuItem("注销");
	private JMenuItem jmiRegister = new JMenuItem("注册");
	private JMenuItem jmiPassword =  new JMenuItem("修改密码");
	private JMenuItem jmiExit = new JMenuItem("退出");
	
	//Create Search Menu Item
	private JMenuItem jmiSclient = new JMenuItem("顾客信息");
	private JMenuItem jmiSagency = new JMenuItem("经办人信息");
	private JMenuItem jmiSmedicine = new JMenuItem("药品信息");
	
	//Create Insert Menu Item
	private JMenuItem jmiIclient = new JMenuItem("客户信息");
	private JMenuItem jmiIagency = new JMenuItem("经办人信息");
	private JMenuItem jmiImedicine = new JMenuItem("药品信息");
	
	//Create Delete Menu Item
	private JMenuItem jmiDclient = new JMenuItem("客户信息");
	private JMenuItem jmiDagency = new JMenuItem("经办人信息");
	private JMenuItem jmiDmedicine = new JMenuItem("药品信息");
	
	//Create Alter Menu Item
	private JMenuItem jmiAclient = new JMenuItem("客户信息");
	private JMenuItem jmiAagency = new JMenuItem("经办人信息");
	private JMenuItem jmiAmedicine = new JMenuItem("药品信息");
	
	//Create Scan Menu Item
	private JMenuItem jmiCclient = new JMenuItem("客户信息");
	private JMenuItem jmiCagency = new JMenuItem("经办人信息");
	private JMenuItem jmiCmedicine = new JMenuItem("药品信息");
	
	//Create Chart Menu Item
	private JMenuItem jmiHclient = new JMenuItem("客户信息");
	private JMenuItem jmiHagency = new JMenuItem("经办人信息");
	private JMenuItem jmiHmedicine = new JMenuItem("药品信息");
	
	public Hospital_Menu(){
		
		
		this.setJMenuBar(jmb);
		
		jmb.add(jmUser);
		jmb.add(jmSearch);
		jmb.add(jmInsert);
		jmb.add(jmDelete);
		jmb.add(jmAlter);
		jmb.add(jmScan);
		jmb.add(jmChart);
		
		jmUser.add(jmiLogin);
		jmUser.add(jmiRegister);
		jmUser.add(jmiPassword);
		jmUser.add(jmiExit);
		
		jmSearch.add(jmiSclient);
		jmSearch.add(jmiSagency);
		jmSearch.add(jmiSmedicine);
		
		jmInsert.add(jmiIclient);
		jmInsert.add(jmiIagency);
		jmInsert.add(jmiImedicine);
		
		jmDelete.add(jmiDclient);
		jmDelete.add(jmiDagency);
		jmDelete.add(jmiDmedicine);
		
		jmAlter.add(jmiAclient);
		jmAlter.add(jmiAagency);
		jmAlter.add(jmiAmedicine);
		
		jmScan.add(jmiCclient);
		jmScan.add(jmiCagency);
		jmScan.add(jmiCmedicine);
		
		jmChart.add(jmiHclient);
		jmChart.add(jmiHagency);
		jmChart.add(jmiHmedicine);
		
		this.setSize(300,300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String [] args){
		Hospital_Menu hm = new Hospital_Menu();
	}
	
}
