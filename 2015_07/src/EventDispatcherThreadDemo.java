import javax.swing.*;

public class EventDispatcherThreadDemo extends JApplet{
	public EventDispatcherThreadDemo(){
		add(new JLabel("Hi, it runs from an event dispatch thread"),JLabel.CENTER);
	}
	
	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame=new JFrame("EventDispatherThreadDemo");
				frame.add(new EventDispatcherThreadDemo());
				frame.setSize(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			}
		});
	}
	
	
}
