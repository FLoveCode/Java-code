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
			public void windowDeiconified(WindowEvent event){//������С��
				System.out.println("Window deiconified");
			}
			
			public void windowIconified(WindowEvent event){
				System.out.println("Window iconified");		//��ԭ����
			}
			
			public void windowActivated(WindowEvent event){	//�����
				System.out.println("Window activated");
			}
			
			public void windowDeactivated(WindowEvent event){	//�ǻ�Ĵ���
				System.out.println("Window deactivated");
			}
			
			public void windowOpened(WindowEvent event){		//�򿪴���
				System.out.println("Widnow Opened");
			}
			
			public void windowClosing(WindowEvent event){		//���ڹرմ���
				System.out.println("Window closing");
			}
			
			public void windowClosed(WindowEvent event){
				System.out.println("Window closed");
			}
		});
	}

}
