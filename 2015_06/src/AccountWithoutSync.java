import java.util.concurrent.*;

public class AccountWithoutSync {

private static Account account=new Account();

public static void main(String [] args){
	ExecutorService excutor=Executors.newCachedThreadPool();
	
	for(int i=0;i<100;i++){
		excutor.execute(new AddPennyTask());
	}
	excutor.shutdown();
	
	while(!excutor.isTerminated()){
	}
	
	System.out.println("what is balance?"+account.getBalance());
}	
	
	private static class AddPennyTask implements Runnable{
		public void run(){
			account.deposit(1);
		}
	}

	private static class Account{
		private int balance=0;
		
		public int getBalance(){
			return balance;
		}
		
		public synchronized  void deposit(int amount){
			int newBalance=balance+amount;
			
			try{
				Thread.sleep(5);
			}
			
			catch(InterruptedException ex){
				
			}
			
			balance=newBalance;
		}
	}
}
