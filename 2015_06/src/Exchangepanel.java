import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Exchangepanel extends JFrame{
	public Exchangepanel(){
		messagepanel p1=new messagepanel("Java is fun");
		add(p1,BorderLayout.CENTER);
		//this.setContentPane(p1);
	}
	
	public static void main(String [] args){
		Exchangepanel frame=new Exchangepanel();
		frame.setSize(300, 300);
		frame.setTitle("ExchangPanel");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	static class messagepanel extends JPanel{
		private String message1;
		private Color c;
		public messagepanel(String s1){
			message1=s1;
			
			addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					message1="Java is powerful";
					c=Color.BLACK;
					repaint();
				}
				
				public void mouseReleased(MouseEvent e){
					message1="Java is fun";
					c=Color.WHITE;
					repaint();
				}
			});
			
		}
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString(message1, 100, 100);
			setBackground(c);
		}
		
	}
}
