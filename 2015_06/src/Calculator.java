import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame{
	private JButton [] num=new JButton[10];
	private JButton jbCancel=new JButton("C");
	private JButton jbMul=new JButton("*");
	private JButton jbDIV=new JButton("/");
	private JButton jbADD=new JButton("+");
	private JButton jbSUB=new JButton("-");
	private JButton jbPoint=new JButton(".");
	private JButton jbADSU=new JButton("+/-");
	private JButton jbequal=new JButton("=");
	private JButton jbrefresh=new JButton("<¡ª¡ª");
	private JButton jbbra=new JButton("()");
	private JTextArea t=new JTextArea();
	
	public Calculator(){
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		
		for(int i=0;i<10;i++){
			num[i]=new JButton(""+i);
		}
		p1.add(t);
		
		p2.setLayout(new GridLayout(5,4,5,5));
		p2.add(jbCancel);
		p2.add(jbDIV);
		p2.add(jbMul);
		p2.add(jbrefresh);
		p2.add(num[7]);
		p2.add(num[8]);
		p2.add(num[9]);
		p2.add(jbADD);
		p2.add(num[4]);
		p2.add(num[5]);
		p2.add(num[6]);
		p2.add(jbSUB);
		p2.add(num[1]);
		p2.add(num[2]);
		p2.add(num[3]);
		p2.add(jbbra);
		p2.add(num[0]);
		p2.add(jbPoint);
		p2.add(jbADSU);
		p2.add(jbequal);
		
		add(p1,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
	}
	
	public static void main(String [] args){
		JFrame frame=new Calculator();
		frame.setSize(300, 300);
		frame.setTitle("Calculator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
