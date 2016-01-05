
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import jxl.*;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class CreateXLS {
	
	public CreateXLS(File file,String query){
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement pre=null;
		WritableWorkbook book=null;
		WritableSheet sheet=null;
		
		con=getConnection();
		pre=getStatement(con,query);
		rs=getResultSet(pre);
		
		try {
			book=Workbook.createWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.createSheet("µÚÒ»Ò³", 0);
		
		fillContent(rs,sheet);
		
		SheetSettings sheetset=sheet.getSettings();
		
		try {
			book.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
			try {
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
		close_DB(con,pre,rs);
	}
	
	public Connection getConnection(){
		String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con=null;
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public PreparedStatement getStatement(Connection con,String query){
		PreparedStatement pre=null;
		
		try {
			pre=con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pre;
	}
	
	public ResultSet getResultSet(PreparedStatement pre){
		ResultSet rs=null;
		
		try {
			rs=pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void close_DB(Connection con,PreparedStatement pre,ResultSet rs){
		try {
			rs.close();
			pre.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fillContent(ResultSet rs,WritableSheet sheet){
		ResultSetMetaData rsm;
		int rows=0;
		
		WritableFont font1= new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD);
		WritableCellFormat format1=new WritableCellFormat(font1);
		WritableFont font2= new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD);
		WritableCellFormat format2=new WritableCellFormat(font2);	
		
		try {
			format1.setAlignment(jxl.format.Alignment.CENTRE);
			format2.setAlignment(jxl.format.Alignment.CENTRE);
		} catch (WriteException e1) {
			e1.printStackTrace();
		} 
		
		try {
			rsm = rs.getMetaData();
				for(int i=1;i<=rsm.getColumnCount();i++){
					sheet.setColumnView(i-1, 16);
					Label label = new Label(i-1, 0, rsm.getColumnName(i),format1);
					try {
						sheet.addCell(label);
					} catch (WriteException e) {
						e.printStackTrace();
					}
				}
				
				while(rs.next()){
					rows++;
				for(int i=1;i<=rsm.getColumnCount();i++){
					Label label = new Label(i-1,rows,rs.getString(i),format2);
					
					try {
						sheet.addCell(label);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
				}	
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
