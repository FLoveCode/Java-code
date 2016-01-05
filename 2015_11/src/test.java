import java.awt.*;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String [] args){
		java.util.Date ud=new java.util.Date();
		java.sql.Date sd = new java.sql.Date(ud.getTime());
		System.out.println(sd);
	}
}
