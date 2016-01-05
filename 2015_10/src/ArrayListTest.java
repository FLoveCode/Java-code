import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

public class ArrayListTest {
	ArrayList<String> altest = new ArrayList<String>();
	Vector vtest = new Vector();
	
	public ArrayListTest(){
	
		altest.add("Test1");
		vtest.add("Test2");
		altest.add("Test3");
		vtest.add("Test4");
		System.out.println(altest.get(1));
		System.out.println(vtest.get(1));
	}
	
	public static void main(String [] args){
		ArrayListTest altTest = new ArrayListTest();
	}
}
