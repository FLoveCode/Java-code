import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class openWeb extends JFrame{
	
	JButton jb=new JButton("´ò¿ªÍøÒ³");
	public openWeb(){
		
		JPanel p1=new JPanel();
		
		p1.add(jb);
		
		this.add(p1);
		
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("http://wwww.baidu.com"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		this.setSize(new Dimension(100,100));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String []args){
		openWeb ow=new openWeb();
	}
}
