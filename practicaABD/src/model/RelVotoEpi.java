package model;

public class RelVotoEpi {

	private long id_epi;
	private long id_voto;
	
	public RelVotoEpi() {
		super();
		this.id_epi=0;
		this.id_voto=0;
	}

	public RelVotoEpi(long id_epi, long id_voto) {
		super();
		this.id_epi = id_epi;
		this.id_voto = id_voto;
	}

	public long getId_epi() {
		return id_epi;
	}

	public void setId_epi(long id_epi) {
		this.id_epi = id_epi;
	}

	public long getId_voto() {
		return id_voto;
	}

	public void setId_voto(long id_voto) {
		this.id_voto = id_voto;
	}
	
	
	
	
}
