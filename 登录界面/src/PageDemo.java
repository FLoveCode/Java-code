import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class PageDemo extends JFrame{
	JButton jbPre=new JButton("上一页");
	JButton jbNext=new JButton("下一页");
	JButton jbfirst=new JButton("首页");
	JButton jblast=new JButton("末页");
	JButton jbprint=new JButton("打印");
	
	JButton jbJump=new JButton("跳转");
	JLabel jlD=new JLabel("第");
	JLabel jlY=new JLabel("页");
	JTextField jtfpageNo=new JTextField(3);
	private JScrollPane scroll;
	String queryTotal=null;
	String queryPage=null;
	static int pageNo=1;
	final int pageSize=20;
	
	public PageDemo(String total,String page){
		JPanel p3=new JPanel();
		final PageTable pt=new PageTable(page);
		
		p3.add(jlD);
		p3.add(jtfpageNo);
		p3.add(jlY);
		
		JPanel p1=new JPanel();
		p1.add(p3);
		p1.add(jbJump);
		
		JPanel p2=new JPanel();
		p2.add(jbfirst);
		p2.add(jbPre);
		p2.add(p1);
		p2.add(jbNext);
		p2.add(jblast);
		p2.add(jbprint);
		
		jbNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				pageNo++;
				if(pageNo>getpageTotal()){
					JOptionPane.showMessageDialog(null, "目前是最后一页");
					pageNo=getpageTotal();
				}
				changeTable();
				
				jtfpageNo.setText(String.valueOf(pageNo));
			}
		});
		
		jbPre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pageNo--;
				
				if(pageNo<1){
					JOptionPane.showMessageDialog(null, "目前是第一页");
					pageNo=1;
				}
				changeTable();
				
				jtfpageNo.setText(String.valueOf(pageNo));
			}
		});
		
		jbJump.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(Integer.valueOf(jtfpageNo.getText())<=getpageTotal()&&Integer.valueOf(jtfpageNo.getText())>0){
					pageNo=Integer.valueOf(jtfpageNo.getText());
					changeTable();
					jtfpageNo.setText(String.valueOf(pageNo));
				}
				else
					JOptionPane.showMessageDialog(null, "页数不在范围之内");
				
				
			}
		});
		
		jbfirst.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pageNo=1;
				changeTable();
				
				jtfpageNo.setText(String.valueOf(pageNo));
			}
		});
		
		jblast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pageNo=getpageTotal();
				changeTable();
				
				jtfpageNo.setText(String.valueOf(pageNo));
			}
		});
		
		jbprint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					pt.getTable().print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		jtfpageNo.setText(String.valueOf(pageNo));
		this.setqueryTotal(total);
		this.setqueryPage(page);
		this.add(pt,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		this.setSize(630, 450);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public String getqueryTotal(){
		return queryTotal;
	}
	
	public void setqueryTotal(String q){
		queryTotal=q;
	}
	
	public String getqueryPage(){
		return queryPage;
	}
	
	public void setqueryPage(String q){
		queryPage=q;
	}
	
	public int getpageTotal(){
		int rowTotal=getTrow();
		int pageTotal;
		
		if(rowTotal%10==0)
			pageTotal=rowTotal/pageSize;
			
		else
			pageTotal=rowTotal/pageSize+1;
		
		return pageTotal;
	}
	
	public int getTrow(){
		Connection con=null;
		CallableStatement callstatement=null;
		int result=0;
				con=getConnection();
				try {
					callstatement=con.prepareCall(getqueryTotal());
					callstatement.registerOutParameter(1, Types.INTEGER);
					
					callstatement.execute();
					
					result= Integer.valueOf(callstatement.getString(1));
					callstatement.close();
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
			return result;
		} 
		
	public Connection getConnection(){
		Connection dbConn=null;
		try{
			String driverName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			
			Class.forName(driverName);
				
			dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;integratedSecurity=true;DatabaseName=MediDB");
			System.out.println("Connection succee!");
			}
			catch(Exception e){
				System.out.println("错误1"+e.getMessage());
			}
			finally{
				return dbConn;
			}
		
	}
	
	public CallableStatement getStatement(Connection dbConn,String query){
		CallableStatement callstatement=null;
		try {
			callstatement= dbConn.prepareCall(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			return callstatement;
		}
		
	}
	
	public ResultSet getResultSet(CallableStatement callstatement){
		ResultSet rs=null;
		
		try {
			callstatement.setInt(1, (pageNo-1)*pageSize+1);
			callstatement.setInt(2, pageNo*pageSize);
			rs=callstatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			return rs;
		}
		
	}
	
	public JTable createTable(ResultSet rs){
		Vector rows = new Vector();
		Vector columnNames = new Vector();
		
		try {
			columnNames = show_DB_column(rs);
			rows=show_DB_row(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new JTable(rows,columnNames);
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
			String test = rsm.getColumnName(i);
			columnNames.add(test);
		}
		
		return columnNames;
	
	}
	
	
	public void closeDB(ResultSet rs,CallableStatement callstatement,Connection conn){
		try {
			rs.close();
			callstatement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void changeTable(){
		JTable table;

		Connection dbConn=null;
		CallableStatement callstatement=null;
		ResultSet rs=null;
		
		
		dbConn=getConnection();
		callstatement=getStatement(dbConn,getqueryPage());
		rs=getResultSet(callstatement);
		table=createTable(rs);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setFont(new Font(getFont().getName(),Font.PLAIN,13));
		
		
		for(int i=0;i<table.getColumnCount();i++)
		{
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		table.setPreferredScrollableViewportSize(new Dimension(590,300));
		
		scroll.setViewportView(table);
		closeDB(rs,callstatement,dbConn);
	}


	
	class PageTable extends JPanel{
		private JTable table;
		
		public PageTable(String page){
			Connection dbConn=null;
			CallableStatement callstatement=null;
			ResultSet rs=null;
			
			dbConn=getConnection();
			callstatement=getStatement(dbConn,page);
			rs=getResultSet(callstatement);
			table=createTable(rs);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			table.setFont(new Font(getFont().getName(),Font.PLAIN,13));
			
			scroll = new JScrollPane(table);
			this.add(scroll,BorderLayout.CENTER);
			
			for(int i=0;i<table.getColumnCount();i++)
			{
				table.getColumnModel().getColumn(i).setPreferredWidth(150);
			}
			table.setPreferredScrollableViewportSize(new Dimension(590,300));
			
			closeDB(rs,callstatement,dbConn);
			
		}
		
		public JTable getTable(){
			return table;
		}
		
		
	}
}		

