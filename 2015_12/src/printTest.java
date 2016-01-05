import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.*;

public class printTest extends JFrame{
	JButton jbPrint=new JButton("��ӡ");
	private JTable table=null;
	
	public printTest(){
		
		String[][] rows={{"��1-1","��1-2","��1-3"},{"��2-1","��2-2","��2-3"}};
		String [] colums={"��1","��2","��3"};
		
		table=new JTable(rows,colums);
		JPanel tp=new JPanel();
		tp.add(table);
		
		jbPrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					MessageFormat headerFormat = new MessageFormat("Page {0}");  
					MessageFormat footerFormat = new MessageFormat("- {0} -"); 
					table.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		this.add(tp,BorderLayout.CENTER);
		this.add(jbPrint,BorderLayout.SOUTH);
		
		this.setSize(new Dimension(300,300));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	public static void main(String []args){
		printTest pt=new printTest();
	}

}
