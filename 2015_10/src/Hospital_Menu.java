import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Hospital_Menu extends JFrame{

	//Create Menu Bar
	private JMenuBar jmb = new JMenuBar();
	
	//Create Menu
	private JMenu jmUser = new JMenu("�û�����");
	private JMenu jmSearch = new JMenu("��ѯ");
	private JMenu jmInsert = new JMenu("¼��");
	private JMenu jmDelete = new JMenu("ɾ��");
	private JMenu jmAlter = new JMenu("�޸�");
	private JMenu jmScan = new JMenu("���");
	private JMenu jmChart = new JMenu("����");
	
	//Create User Menu Items
	private JMenuItem jmiLogin = new JMenuItem("ע��");
	private JMenuItem jmiRegister = new JMenuItem("ע��");
	private JMenuItem jmiPassword =  new JMenuItem("�޸�����");
	private JMenuItem jmiExit = new JMenuItem("�˳�");
	
	//Create Search Menu Item
	private JMenuItem jmiSclient = new JMenuItem("�˿���Ϣ");
	private JMenuItem jmiSagency = new JMenuItem("��������Ϣ");
	private JMenuItem jmiSmedicine = new JMenuItem("ҩƷ��Ϣ");
	
	//Create Insert Menu Item
	private JMenuItem jmiIclient = new JMenuItem("�ͻ���Ϣ");
	private JMenuItem jmiIagency = new JMenuItem("��������Ϣ");
	private JMenuItem jmiImedicine = new JMenuItem("ҩƷ��Ϣ");
	
	//Create Delete Menu Item
	private JMenuItem jmiDclient = new JMenuItem("�ͻ���Ϣ");
	private JMenuItem jmiDagency = new JMenuItem("��������Ϣ");
	private JMenuItem jmiDmedicine = new JMenuItem("ҩƷ��Ϣ");
	
	//Create Alter Menu Item
	private JMenuItem jmiAclient = new JMenuItem("�ͻ���Ϣ");
	private JMenuItem jmiAagency = new JMenuItem("��������Ϣ");
	private JMenuItem jmiAmedicine = new JMenuItem("ҩƷ��Ϣ");
	
	//Create Scan Menu Item
	private JMenuItem jmiCclient = new JMenuItem("�ͻ���Ϣ");
	private JMenuItem jmiCagency = new JMenuItem("��������Ϣ");
	private JMenuItem jmiCmedicine = new JMenuItem("ҩƷ��Ϣ");
	
	//Create Chart Menu Item
	private JMenuItem jmiHclient = new JMenuItem("�ͻ���Ϣ");
	private JMenuItem jmiHagency = new JMenuItem("��������Ϣ");
	private JMenuItem jmiHmedicine = new JMenuItem("ҩƷ��Ϣ");
	
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
