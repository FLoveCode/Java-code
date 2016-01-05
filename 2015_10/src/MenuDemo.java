import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class MenuDemo extends JFrame{
	
	//Text fields for Number1.Number2.Number3
	private JTextField jtfNum1,jtfNum2,jtfNum3;
	
	//Button 'Add','Subtract','Multiply' and 'Divide'
	private JButton jbtAdd,jbtSub,jbtMul,jbtDiv;
	
	//Menu items 'Add'....
	private JMenuItem jmiAdd,jmiSub,jmiMul,jmiDiv,jmiClose;
	
	public MenuDemo(){
		//Create menu bar
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JMenu operationMenu = new JMenu("Operation");
		operationMenu.setMnemonic('O');
		jmb.add(operationMenu);
		
		JMenu exitMenu = new JMenu("Exit");
		exitMenu.setMnemonic('E');
		jmb.add(exitMenu);
		
		operationMenu.add(jmiAdd= new JMenuItem("Add",'A'));
		operationMenu.add(jmiSub= new JMenuItem("Sub",'S'));
		operationMenu.add(jmiMul= new JMenuItem("Mul",'M'));
		operationMenu.add(jmiDiv= new JMenuItem("Div",'D'));
		exitMenu.add(jmiClose= new JMenuItem("Close",'C'));
		
		jmiAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		jmiSub.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		jmiMul.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
		jmiDiv.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		
		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(new JLabel("Number 1:"));
		p1.add(jtfNum1= new JTextField(3));
		p1.add(new JLabel("Number 2:"));
		p1.add(jtfNum2= new JTextField(3));
		p1.add(new JLabel("Result:"));
		p1.add(jtfNum3= new JTextField(4));
		jtfNum3.setEditable(false);
		
		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(jbtAdd= new JButton("Add"));
		p2.add(jbtSub= new JButton("Sub"));
		p2.add(jbtMul= new JButton("Mul"));
		p2.add(jbtDiv= new JButton("Div"));
		
		setLayout(new BorderLayout());
		add(p1,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		
		jbtAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('+');
			}
		});
		
		jbtSub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('-');
			}
		});
		
		jbtMul.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('*');
			}
		});
		
		jbtDiv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('/');
			}
		});
		
		jmiAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('+');
			}
		});
		
		jmiSub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('-');
			}
		});
		
		jmiMul.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('*');
			}
		});
		
		jmiDiv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate('/');
			}
		});
		
		jmiClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		
		
	}
	
	private void calculate(char operator){
		int num1 = (Integer.parseInt(jtfNum1.getText().trim()));
		int num2 = (Integer.parseInt(jtfNum2.getText().trim()));
		int result = 0;
		switch(operator){
		case '+':result = num1+num2;break;
		case '-':result = num1-num2;break;
		case '*':result = num1*num2;break;
		case '/':result = num1/num2;break;
		}
		
		jtfNum3.setText(String.valueOf(result));
		
	}
	
	public static void main(String [] args){
		MenuDemo me= new MenuDemo();
	}
}
