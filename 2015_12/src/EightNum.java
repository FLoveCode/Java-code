import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;


public class EightNum {

	public static block getFromOpen(ArrayList<block> open){
		
		return open.get(0);
	}
	
	public static ArrayList<block> getChildList(ArrayList<block> closed){
		ArrayList<block> templist=new ArrayList<block>();
		block temp=closed.get(0);
		
		if(temp.getX()!=0&&temp.getAction()!="DOWN")
			templist.add(new block(temp.getheigth(),"UP",temp.getNums(),temp.MoveUp(temp.getX(), temp.getY())));
		
		if(temp.getX()!=2&&temp.getAction()!="UP")
			templist.add(new block(temp.getheigth(),"DOWN",temp.getNums(),temp.MoveDown(temp.getX(), temp.getY())));
		
		if(temp.getY()!=0&&temp.getAction()!="RIGHT")
			templist.add(new block(temp.getheigth(),"LEFT",temp.getNums(),temp.MoveLeft(temp.getX(), temp.getY())));
		
		if(temp.getY()!=2&&temp.getAction()!="LEFT")
			templist.add(new block(temp.getheigth(),"RIGHT",temp.getNums(),temp.MoveRight(temp.getX(), temp.getY())));
		
		return templist;
		
	}
	
	public  static void main(String [] args){
		String strnum=null;
		int [][]nums=new int[3][3];
		ArrayList<block> openlist=new ArrayList<block>();
		ArrayList<block> closedlist=new ArrayList<block>();
		
		Comparator<block> sortmethod=new Comparator<block>(){
			@Override
			public int compare(block b1,block b2){
				
				return b1.compareTo(b2);
				}
		};
		
		strnum=JOptionPane.showInputDialog("八位难数", "请输入八位数字字符");
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				nums[i][j]=Integer.valueOf(String.valueOf(strnum.charAt(i*3+j)));
		block b=new block(-1,null,null,nums);
		openlist.add(b);
		
		while(openlist!=null){
			
			closedlist.add(openlist.get(0));
			
			closedlist.get(0).PrintIndex();
			//System.out.println(' '+closedlist.get(0).judge());
			
			System.out.println();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(closedlist.get(0).wrongNum()==0)
				break;
				
			
			closedlist=getChildList(closedlist);
			
			for(int i=0;i<closedlist.size();i++)	
				openlist.add(closedlist.get(i));
			
			openlist.remove(0);
			openlist.sort(sortmethod);
			
			closedlist.clear();
		}
		
	}
}

class block implements  Comparable<block>{
	private int[][]nums=new int[3][3];
	private int X;
	private int Y;
	private int [][]parent=new int[3][3];
	private int heigth;
	private String lastaction;
	
	
	public block(int heigth,String action,int [][]parent,int [][]nums){
		this.parent=parent;
		this.nums=nums;
		X=searchX(nums);
		Y=searchY(nums);
		this.heigth=heigth;
		this.heigth++;
		this.lastaction=action;
	}
	
	public int searchX(int [][]nums){
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(nums[i][j]==0)
					return i;
		
		return -1;
	}
	
	public int searchY(int [][]nums){
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(nums[i][j]==0)
					return j;
		
		return -1;
	}
	
	public int [][] getNums(){
		return this.nums;
	}
	
	public void setNums(int [][]nums){
		this.nums=nums;
	}
	
	public int getX(){
		return this.X;
	}
	
	public int getY(){
		return this.Y;
	}
	
	public void setX(int X){
		this.X=X;
	}
	
	public void setY(int Y){
		this.Y=Y;
	}
	
	public int getValueIndex(int X,int Y){
		return getNums()[X][Y];
	}
	
	public void setValueIndex(int X,int Y,int value){
		getNums()[X][Y]=value;
	}
	
	public void PrintIndex(){
		for(int i=0;i<3;i++){
			System.out.println();
			for(int j=0;j<3;j++)
				System.out.print(getNums()[i][j]);
		}
	}
	
	public int[][] getNewNums(){
		int [][]temp=new int[3][3];
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				temp[i][j]=getValueIndex(i,j);
		
		return temp;
	}
	
	public int[][] MoveUp(int X,int Y){
		int [][]temp=getNewNums();
			
			temp[X][Y]=temp[X-1][Y];
			temp[X-1][Y]=0;
			
			return temp;
	}
	
	public int[][] MoveDown(int X,int Y){
		int [][]temp=getNewNums();
		
		temp[X][Y]=temp[X+1][Y];
		temp[X+1][Y]=0;
		
		return temp;
	}
	
	public int[][] MoveLeft(int X,int Y){
		int [][]temp=getNewNums();
		
		temp[X][Y]=temp[X][Y-1];
		temp[X][Y-1]=0;
		
		return temp;
	}
	
	public int[][] MoveRight(int X,int Y){
		int [][]temp=getNewNums();
		
		temp[X][Y]=temp[X][Y+1];
		temp[X][Y+1]=0;
		
		return temp;
	}
	
	public int wrongNum(){
		int wrong=0;
		
		if(nums[0][0]!=1) wrong++;
		if(nums[0][1]!=2) wrong++;
		if(nums[0][2]!=3) wrong++;
		if(nums[1][0]!=8) wrong++;
		if(nums[1][2]!=4) wrong++;
		if(nums[2][0]!=7) wrong++;
		if(nums[2][1]!=6) wrong++;
		if(nums[2][2]!=5) wrong++;
		
		return wrong;
		
	}
	
	@Override
	public int compareTo(block b1){
		
		if(this.judge()>b1.judge())
			return 1;
		else
			if(this.judge()<b1.judge())
				return -1;
			else
				if(this.wrongNum()>b1.wrongNum())
					return 1;
				else
					if(this.wrongNum()<b1.wrongNum())
						return -1;
					else
						return 0;
		
	}
	
	public int getheigth(){
		return heigth;
	}

	public int judge(){
		return this.wrongNum()+this.getheigth();
	}

	public void setAction(String Action){
		this.lastaction=Action;
	}
	
	public String getAction(){
		
		return this.lastaction;
	}

	public int[][] getParent(){
		return this.parent;
	}
}


