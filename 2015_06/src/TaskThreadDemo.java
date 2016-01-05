
public class TaskThreadDemo {
	public static void main(String []  args){
		Runnable printA=new PrintChar('a',10);
		Runnable printB=new PrintChar('b',10);
		Runnable printC=new PrintNum(10);
		
		Thread thread1=new Thread(printA);
		Thread thread2=new Thread(printB);
		Thread thread3=new Thread(printC);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
	}
}

class PrintChar implements Runnable{
	private char charToPrint;
	private int times;
	
	public PrintChar(char c,int t){
		charToPrint =c;
		times=t;
	}
	
	public void run(){
		for(int i=0;i<times;i++){
			System.out.print(charToPrint+" ");
		}
	}
}

class PrintNum implements Runnable{
	private int lastnum;
	
	public PrintNum(int n){
		lastnum=n;
	}
	
	public void run(){
		Thread thread4=new Thread(new PrintChar('c',10));
		
		thread4.start();
		
		try{
			for(int i=0;i<=lastnum;i++){
				System.out.print(""+i+" ");
				if(i==5) thread4.join();
			}
		}
		
		catch(InterruptedException ex){
		}
		
	}
}
