import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.*;

public class printTest extends JFrame{
	JButton jbPrint=new JButton("打印");
	private JTable table=null;
	
	public printTest(){
		
		String[][] rows={{"行1-1","行1-2","行1-3"},{"行2-1","行2-2","行2-3"}};
		String [] colums={"列1","列2","列3"};
		
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
