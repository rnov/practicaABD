package model;

public class RelVotoSer {

	
	private long id_ser;
	private long id_voto;
	
	
	
	public RelVotoSer() {
		super();
		this.id_ser=0;
		this.id_voto=0;
	}



	public RelVotoSer(long id_ser, long id_voto) {
		super();
		this.id_ser = id_ser;
		this.id_voto = id_voto;
	}



	public long getId_ser() {
		return id_ser;
	}



	public void setId_ser(long id_ser) {
		this.id_ser = id_ser;
	}



	public long getId_voto() {
		return id_voto;
	}



	public void setId_voto(long id_voto) {
		this.id_voto = id_voto;
	}
	
	
}
