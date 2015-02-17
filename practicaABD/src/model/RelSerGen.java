package model;

public class RelSerGen {

	private long id_ser;
	private String id_gen;
	
	public RelSerGen() {
		super();
		this.id_ser=0;
		this.id_gen=null;
	}
	public RelSerGen(long id_ser, String id_gen) {
		super();
		this.id_ser = id_ser;
		this.id_gen = id_gen;
	}
	public long getId_ser() {
		return id_ser;
	}
	public void setId_ser(long id_ser) {
		this.id_ser = id_ser;
	}
	public String getId_gen() {
		return id_gen;
	}
	public void setId_gen(String id_gen) {
		this.id_gen = id_gen;
	}
	
	
}
