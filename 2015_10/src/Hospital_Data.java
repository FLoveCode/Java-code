import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;

public class Hospital_Data extends JFrame{
	Text_info ti = new Text_info();
	Data da = new Data();
	
	Hospital_Data(){
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(ti,BorderLayout.WEST);
		this.add(da,BorderLayout.CENTER);
	}
	
	
	public static void main(String [] args){
		Hospital_Data hd = new Hospital_Data();
	}
	
	class Text_info extends JPanel{
		private JTextArea info = new JTextArea();
		
		
		Text_info(){
			JScrollPane scroll = new JScrollPane(info);
			
			info.setSize(200,500);
			info.setLineWrap(true);
			info.setWrapStyleWord(true);
			info.setEditable(false);
			info.setBorder(new LineBorder(Color.RED));
			add(info);
			this.setBorder(new LineBorder(Color.BLACK));
		}
		
	}
	
	
	class Data extends JPanel{
		
		private JRadioButton jrbClient = new JRadioButton("顾客");
		private JRadioButton jrbAgency = new JRadioButton("经办人");
		private JRadioButton jrbMedicine = new JRadioButton("医药");
		private JRadioButton jrbMale = new JRadioButton("男");
		private JRadioButton jrbFemale = new JRadioButton("女");
		
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
			
			JPanel p1= new JPanel(new FlowLayout());
			ButtonGroup g1 = new ButtonGroup();
			g1.add(jrbClient);
			g1.add(jrbAgency);
			g1.add(jrbMedicine);
			p1.add(jrbClient);
			p1.add(jrbAgency);
			p1.add(jrbMedicine);
			this.add(p1,BorderLayout.SOUTH);
			
			JPanel p2 = new JPanel(new FlowLayout());
			ButtonGroup g2 = new ButtonGroup();
			g2.add(jrbMale);
			g2.add(jrbFemale);
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
		}
	}
	
}
