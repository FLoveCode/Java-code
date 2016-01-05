import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class MoveMessageDemo extends JFrame{
	public MoveMessageDemo(){
		MovableMessagePanel p=new MovableMessagePanel("Welcome to Java");
		
		setLayout(new BorderLayout());
		p.setBorder(new TitledBorder("Movable area"));
		add(p);
	}
	
	public static void main(String [] args){
		MoveMessageDemo frame=new MoveMessageDemo();
		frame.setTitle("MoveMessageDemo");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	static class MovableMessagePanel extends JPanel{
		private String message ="Welcome to Java";
		private int x=20;
		private int y=20;
		
		public MovableMessagePanel(String s){
			message=s;
			addMouseMotionListener(new MouseMotionAdapter(){
				public void MouseMoved(MouseEvent e){
					System.out.println("Bingo");
					x=e.getX();
					y=e.getY();
					repaint();
				}
			});
		}
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString(message, x, y);
		}
	}
}
