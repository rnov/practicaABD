package model;

public class PersonajeActor {
	
	private String nomPers;
	private String nomArt;
	
	public PersonajeActor(){
		this.nomPers = null;
		this.nomArt = null;
		
	}
	public PersonajeActor(String nomPers, String nomArt) {
		super();
		this.nomPers = nomPers;
		this.nomArt = nomArt;
	}

	public String getNomPers() {
		return nomPers;
	}

	public void setNomPers(String nomPers) {
		this.nomPers = nomPers;
	}

	public String getNomArt() {
		return nomArt;
	}

	public void setNomArt(String nomArt) {
		this.nomArt = nomArt;
	}
	
	
	
}
