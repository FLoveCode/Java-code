import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class autoData extends JFrame{
	autoPanel auto = new autoPanel();
	
	autoData(){
		this.add(auto);
		this.setLocationRelativeTo(null);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String [] args){
		autoData ad = new autoData();
	}
	
	class autoPanel extends JPanel{
		JTable jtable1 ,jtable2;
		JScrollPane scroll ;
		JButton jbnew = new JButton("更新数据");
		
		autoPanel(){
			String[] column1 = {"列1","列2"};
			String [] [] rows1 ={{"行1","行2"},{"行3","行4"}};
			String [] column2 ={"列3","列4"};
			String [] [] rows2 ={{"行5","行6"},{"行7","行8"}};
			
			jtable1 = new JTable(rows1,column1);
			jtable2 = new JTable(rows2,column2);
			
			scroll = new JScrollPane(jtable1);
			jbnew.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					scroll.setViewportView(jtable2);
				}
			});
			
			this.add(jbnew);
			this.add(scroll,BorderLayout.CENTER);
		}
	}
}
