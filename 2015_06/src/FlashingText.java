import javax.swing.*;

public class FlashingText extends JApplet implements Runnable{
	private JLabel jblText= new JLabel("Welcome",JLabel.CENTER);
	
	public FlashingText(){
		add(jblText);
		new Thread(this).start();
	}
	
	public void run(){
		try{
			while(true){
			if(jblText.getText()==null)
			jblText.setText("Welcome");
			else
				jblText.setText(null);
			
			Thread.sleep(200);
		}
		}
		catch(InterruptedException ex){
			
		}
	}
}
