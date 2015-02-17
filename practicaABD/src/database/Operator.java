package database;

public enum Operator {
	EQ(" = ");
	
	private String repres;
	
	private Operator(String e){
		this.repres=e;
	}
	public String toString(){
		return repres;
	}
}
