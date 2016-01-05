import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventDemo extends JFrame{
private keyboardPanel ky=new keyboardPanel();

public KeyEventDemo(){
add(ky);
ky.setFocusable(true);
}

public static void main(String [] args){
	KeyEventDemo frame=new KeyEventDemo();
	frame.setTitle("KeyEventDemo");
	frame.setSize(300, 300);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}

static class keyboardPanel extends JPanel{
	private int x=100;
	private int y=100;
	private char keyChar='A';
	
	public keyboardPanel(){
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				switch(e.getKeyCode()){
				case KeyEvent.VK_DOWN:y+=10;break;
				case KeyEvent.VK_UP:y-=10;break;
				case KeyEvent.VK_LEFT:x-=10;break;
				case KeyEvent.VK_RIGHT:x+=10;break;
				default: keyChar=e.getKeyChar();
				}
				repaint();
			}
		});
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//System.out.println(x);
		//System.out.println(y);
		//System.out.println(keyChar);
		g.setFont(new Font("TimeRoman",Font.PLAIN,24));
		g.drawString(String.valueOf(keyChar),x,y);
	}
}

}
