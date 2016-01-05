import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Test_Snake extends Canvas implements KeyListener{
	private int x,y;
	String direction;
	Runnable m = new Move();
	boolean movable;
	int T;
	
	Test_Snake(){
		x=0;
		y=0;
		T=200;
		this.addKeyListener(this);
		direction="right";
		movable = false;
		new Thread(m).start();
		this.requestFocus(true);
	}
	
	public void keyPressed(KeyEvent e){
		//this.requestFocus();
		
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:direction="up";break;
			case KeyEvent.VK_DOWN:direction="down";break;
			case KeyEvent.VK_LEFT:direction="left";break;
			case KeyEvent.VK_RIGHT:direction="right";break;
			case KeyEvent.VK_ENTER:if(movable==true) movable=false;
								else {movable=true;new Thread(m).start();}break;
			case KeyEvent.VK_PAGE_UP:T=T+10;
			case KeyEvent.VK_PAGE_DOWN:T=T-10;
		}
		
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void keyReleased(KeyEvent e){
		
	}
	
	
	
	public void Turn_Up(){
		y--;
	}
	
	public void Turn_Down(){
		y++;
	}
	
	public void Turn_Left(){
		x--;
	}
	
	public void Turn_Right(){
		x++;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.BLUE);
		g.fillOval(getWidth()/2+x, getHeight()/2+y, 20, 20);
		System.out.println("Paint");
		if(false) g.fillRect(20, 20, 20, 20);
	}
	
	class Move implements Runnable{
		public void run(){
			while(movable){
				switch(direction){
				case "up":Turn_Up();break;
				case "down":Turn_Down();break;
				case "left":Turn_Left();break;
				case "right":Turn_Right();break;
				}
				repaint();
				try{
					Thread.sleep(T);
				}
				catch(InterruptedException e){
					
				}
			}
		}
	}
	
	public static void main(String [] args){
		Test_Snake ts = new Test_Snake();
		JFrame frame = new JFrame();
		
		//frame.requestFocus();
		frame.getContentPane().add(ts);
		frame.setTitle("Snake Game!");
		frame.setSize(600,300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}

