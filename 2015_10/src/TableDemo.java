import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class TableDemo extends JFrame {
	Table table = new Table();
	
	public TableDemo(){
		this.setSize(500,500);
		this.add(table,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String [] args) {
		TableDemo td = new TableDemo();
	}
	
	class Table extends JPanel{
		JTable jtTest;
		
		public Table()    {
			ResultSet rs = Link_DB();
			ResultSetMetaData rsm=null;
			
			Vector rows = new Vector();
			Vector columnNames = new Vector();		
			
			try {
				columnNames = show_DB_column(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				rows=show_DB_row(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			jtTest = new JTable(rows,columnNames);
			jtTest.setShowHorizontalLines(true);
			jtTest.setShowVerticalLines(true);
			jtTest.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jtTest.setGridColor(Color.BLACK);
			
			jtTest.addMouseListener(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					
					if(e.getClickCount()==1)
					{
						int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
						int column=((JTable)e.getSource()).columnAtPoint(e.getPoint());
						String no=((JTable)e.getSource()).getValueAt(row, 0).toString().trim();
						String name=((JTable)e.getSource()).getColumnName(column);
						String content=((JTable)e.getSource()).getValueAt(row, column).toString();
						
						System.out.println("Row:"+row+" Column:"+column);
						System.out.println("No:"+no+" Name:"+name+" Content:"+content);
					}
				}
			});
			
			
			JScrollPane scroll = new JScrollPane(jtTest);
			this.add(scroll);
			
			for(int i=1;i<jtTest.getColumnCount();i++)
			{
				jtTest.getColumnModel().getColumn(i).setPreferredWidth(130);
			}
		}
		
		public Vector show_DB_row(ResultSet rs ) throws SQLException{
			ResultSetMetaData rsm;
			rsm = rs.getMetaData();
			Vector rows = new Vector();
			
			while(rs.next()){
				Vector row = new Vector(rsm.getColumnCount());
				for(int i=1;i<=rsm.getColumnCount();i++)
				{
					row.add(rs.getString(i));
					
				}
			rows.addElement(row);	
			}
				
			return rows;
		}
		
		public Vector show_DB_column(ResultSet rs) throws SQLException{
			ResultSetMetaData rsm= null;
			rsm = rs.getMetaData();
			Vector columnNames = new Vector();
			
			for(int i=1;i<=rsm.getColumnCount();i++){
				//System.out.println(rsm.getColumnName(i));
				String test = rsm.getColumnName(i);
				columnNames.add(test);
				//System.out.println(columnNames.get(i-1));
			}
			
			return columnNames;
		
		}
		public  ResultSet Link_DB(){
			ResultSet resultSet =null;
			System.out.println("Start Connection!!");
			try{
				String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String dbURL ="jdbc:sqlserver://localhost:1434; DatabaseName=MediDB"; 
				
					Class.forName(driverName);
					
					Connection dbConn=DriverManager.getConnection(dbURL,"TestServer","21104");
					System.out.println("Connection succee!");
					Statement statement = dbConn.createStatement();
					
					resultSet =statement.executeQuery("select * from client");
					
					
					/*if(resultSet.next())
					System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
					else
						System.out.println("Error");*/
					
				}
				catch(Exception e){
					System.out.println("´íÎó1"+e.getMessage());
				}
			
			finally{
				return resultSet;
			}
			
		}
	
	}	
}
