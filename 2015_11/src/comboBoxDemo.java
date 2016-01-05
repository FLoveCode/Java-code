import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class comboBoxDemo extends JFrame{
	JComboBox jcb;
	
	comboBoxDemo(){
		JPanel p1= new JPanel();
		JButton jb= new JButton("Click");
		String[] s={"1","2","3","4","5"};
		jcb= new JComboBox(s);
		
		jcb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(jcb.getSelectedItem());
			}
		});
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(jcb.getSelectedItem().toString());
			}
		});
		p1.add(jb,BorderLayout.NORTH);
		p1.add(jcb,BorderLayout.CENTER);
		this.add(p1);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args){
		comboBoxDemo cb= new comboBoxDemo();
		
	}
}
