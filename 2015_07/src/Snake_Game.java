import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

public class Snake_Game extends JFrame {
	Console panel1 = new Console();
	Snake panel2 = new Snake();
	
	Snake_Game(){
		
		add(panel1,BorderLayout.EAST);
		add(panel2,BorderLayout.CENTER);
		
		setTitle("Snake Game!");
		setSize(600,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public static void main(String [] args){
		Snake_Game game = new Snake_Game();		
	}
	
	//控制
	class Console extends JPanel{
		private JButton jbtStart = new JButton("开始");
		private JButton jbtTemp = new JButton("暂停");
		private JButton jbtDif = new JButton("增加难度");
		
		Console(){
			setLayout(new GridLayout(3,1));
			
			add(jbtStart);
			add(jbtTemp);
			add(jbtDif);
		}
	}
	
	//图形界面
	class Snake extends JPanel {
		private int x,y,len;
		JButton jbtup = new JButton("上");
		JButton jbtdown = new JButton("下");
		JButton jbtleft = new JButton("左");
		JButton jbtright = new JButton("右");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		String direction;
		Runnable executor= new Move();
		
		Snake(){
			x=0;
			y=0;
			len=1;
			
			panel1.setLayout(new GridLayout(1,4));
			panel1.add(jbtup);
			panel1.add(jbtdown);
			panel1.add(jbtleft);
			panel1.add(jbtright);
			
			add(panel2,BorderLayout.CENTER);
			add(panel1,BorderLayout.NORTH);
			
			new Thread(executor).start();
			jbtup.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					direction="up";
					
				}
			});
			
			jbtdown.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					direction="down";
				}
			});
			
			jbtleft.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					direction="left";
				}
			});
			
			jbtright.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					direction="right";
				}
			});
			
			
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.fillOval(getWidth()/2+x, getHeight()/2+y, 20, 20);
			System.out.println("Paint");
		}
		
		
		public void Turn_Right(){
			x++;
		}
		
		public void Turn_Left(){
			x--;
		}
		
		public void Turn_Up(){
			y--;
		}
		
		public void Turn_Down(){
			y++;
		}
		
		class Move implements Runnable{
			public void run(){
				try{
					
					switch(direction){
						case"up":Turn_Up();break;
						case"down":Turn_Down();break;
						case"left":Turn_Left();break;
						case"right":Turn_Right();break;
					}
					
					repaint();
					Thread.sleep(100);
					
				}
				catch(InterruptedException e){
					
				}
			}
		}
		
	}
	
}