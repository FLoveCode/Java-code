
public class User {

	private String Number;
	private String Password;
	private boolean au_Search;
	private boolean au_Delete;
	private boolean au_Insert;
	private boolean au_Alter;
	
	public User( String Number,String Password,boolean search,boolean delete, boolean insert,boolean alter){
		this.Number=Number;
		this.Password=Password;
		this.au_Search=search;
		this.au_Insert=insert;
		this.au_Delete=delete;
		this.au_Alter=alter;
	}
	
	public User(){
		this.Number="Admi";
		this.Password="1234";
		this.au_Search=false;
		this.au_Insert=false;
		this.au_Delete=false;
		this.au_Alter=false;
	}
	
	public void setNumber(String Number){
		this.Number=Number;
	}
	public String getNumber(){
		return Number;
	}
	
	public void setPassword(String Password){
		this.Password=Password;
	}
	public String getPassword(){
		return Password;
	}
	
	public void setSearch(boolean search){
		this.au_Search=search;
	}
	public boolean getSearch(){
		return au_Search;
	}
	
	public void setInsert(boolean insert){
		this.au_Insert=insert;
	}
	public boolean getInsert(){
		return au_Insert;
	}
	
	public void setDelete(boolean delete){
		this.au_Delete=delete;
	}
	public boolean getDelete(){
		return au_Delete;
	}
	
	public void setAlter(boolean alter){
		this.au_Alter=alter;
	}
	public boolean getAlter(){
		return au_Alter;
	}
}
