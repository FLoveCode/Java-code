import java.awt.event.*;
import javax.swing.JFrame;

public class TestwindowEvent extends JFrame{
	public static void main(String [] args){
		TestwindowEvent frame=new TestwindowEvent();
		frame.setSize(220,80);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TestWidnowEvent");
		frame.setVisible(true);
	}
	
	public TestwindowEvent(){
		addWindowListener(new WindowListener(){
			public void windowDeiconified(WindowEvent event){//窗口最小化
				System.out.println("Window deiconified");
			}
			
			public void windowIconified(WindowEvent event){
				System.out.println("Window iconified");		//还原窗口
			}
			
			public void windowActivated(WindowEvent event){	//激活窗口
				System.out.println("Window activated");
			}
			
			public void windowDeactivated(WindowEvent event){	//非活动的窗口
				System.out.println("Window deactivated");
			}
			
			public void windowOpened(WindowEvent event){		//打开窗口
				System.out.println("Widnow Opened");
			}
			
			public void windowClosing(WindowEvent event){		//正在关闭窗口
				System.out.println("Window closing");
			}
			
			public void windowClosed(WindowEvent event){
				System.out.println("Window closed");
			}
		});
	}

}
