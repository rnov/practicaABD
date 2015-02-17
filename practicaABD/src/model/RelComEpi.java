package model;

public class RelComEpi {

	private long id_com;
	private long id_epi;
	
	
	
	public RelComEpi() {
		super();
		this.id_com=0;
		this.id_epi=0;
	}

	public RelComEpi(long id_com, long id_epi) {
		super();
		this.id_com = id_com;
		this.id_epi = id_epi;
	}

	public long getId_com() {
		return id_com;
	}

	public void setId_com(long id_com) {
		this.id_com = id_com;
	}

	public long getId_epi() {
		return id_epi;
	}

	public void setId_epi(long id_epi) {
		this.id_epi = id_epi;
	}
	
	
	
	
}
