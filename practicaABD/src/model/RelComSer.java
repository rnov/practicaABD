package model;

public class RelComSer {
	
	private long id_com;
	private long id_ser;
	
	
	
	public RelComSer() {
		super();
		this.id_com= 0;
		this.id_ser= 0;
	}

	public RelComSer(long id_com, long id_ser) {
		super();
		this.id_com = id_com;
		this.id_ser = id_ser;
	}


	public long getId_com() {
		return id_com;
	}

	public void setId_com(long id_com) {
		this.id_com = id_com;
	}

	public long getId_ser() {
		return id_ser;
	}

	public void setId_ser(long id_ser) {
		this.id_ser = id_ser;
	}
	
	

}
