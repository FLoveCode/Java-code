import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

public class Threadcooperation {
	private static Account account=new Account();
	
	public static void main(String [] args){
		ExecutorService executor =Executors.newFixedThreadPool(2);
		
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		
		executor.shutdown();
		
		System.out.println("Thread 1\t\tThread2\t\tBalance");
	}
	
	public static class DepositTask implements Runnable{
		public void run(){
			try{
				while(true){
					account.deposit((int)(Math.random()*10)+1);
					Thread.sleep(1000);
				}
			}
			
			catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
	}

	public static class WithdrawTask implements Runnable{
		public void run(){
			while(true){
				account.withdraw((int)(Math.random())+1);
			}
		}
	}
	private static class Account{
		
		private static Lock lock = new ReentrantLock();
		
		private static Condition newDeposit = lock.newCondition();
		
		private int balance=0;
		
		public int getBalance(){
			return balance;
		}
		
		public void withdraw(int amount){
			lock.lock();
			
			try{
				while(balance<amount){
						System.out.println("\t\twait for a deposit");
						newDeposit.await();
				}
				
				balance-=amount;
				System.out.println("\t\tWidthdraw"+amount+"\t\t"+getBalance());
			}
			catch(InterruptedException ex){
				ex.printStackTrace();
			}
			
			finally{
				lock.unlock();
			}
			}
			
			private void deposit(int amount){
				lock.lock();
				
				try{
					balance+=amount;
					System.out.println("Deposit"+amount+"\t\t\t\t\t"+getBalance());
				
				newDeposit.signalAll();
				}
				
				finally{
					lock.unlock();
				}
				
				
			
		}
	}
}
