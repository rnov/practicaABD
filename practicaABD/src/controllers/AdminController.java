package controllers;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.List;

import model.Actor;
import model.Comentario;
import model.Episodio;
import model.Personaje;
import model.PersonajeActor;
import model.RelEpiPerAct;
import model.Serie;

public class AdminController {
	private Serie serie;
	private Episodio episodio;
	private Actor actor;
	private Personaje personaje;
	private Comentario comentario;
	private RelEpiPerAct personajesEpisodios;
	
	public AdminController() {
		this.serie = new Serie();
		this.episodio=new Episodio();
		this.personajesEpisodios=new RelEpiPerAct();
		this.actor= new Actor();
		this.personaje=new Personaje();
		this.comentario=new Comentario();
	}
	public List<String> listaComentsSerie(long idSerie){
		return this.comentario.listaComentarios(idSerie);
	}
	public List<Serie> getList(String key){
		return this.serie.buscarSerieString(key);
	}
	public boolean getDataSerie(long idSerie){
		this.serie=this.serie.getSerieFromKey(idSerie);
		if(serie!=null) return true;
		else return false;
	}
	public float mediaSerieVotos(Long idSerie){
		return this.serie.mediaVotosSerie(idSerie);
	}
	public String sinopsis(){
		return this.serie.getSinopsis();
	}
	@SuppressWarnings("deprecation")
	public int getFini(Serie ser){
		int year=-1;
		if(ser.getFech_Est()!=null) year=ser.getFech_Est().getYear();
		return year;
	}
	@SuppressWarnings("deprecation")
	public int getFfin(Serie ser){
		int year=-1;
		if(ser.getFech_fin()!=null) year=ser.getFech_fin().getYear();
		return year;
	}
	public String getNombreSerie(){
		return this.serie.getNombre();
	}
	public String getTitular(){
		return this.serie.getTitular();
	}
	public boolean actualizarSerie(String titutlo, String sinopsis,Long idSerie, String nombre, Date ini, Date fin){
		
		return this.serie.actualizarSerie(idSerie, nombre, sinopsis, titutlo,ini,fin);
	}
	public boolean insertarSerie(String nombre, String sin, String tit, Date fini, Date Ffin){
		return this.serie.insertarSerie(nombre, sin, tit, fini, Ffin);
	}
	public List<Episodio> episodiosXserie(long idSerie){
		return this.episodio.getEpisodiosFromSerieKey(null,idSerie);
	}
	public boolean addEpisodio(long id, int or,int temp, String sinop, String tit,Date fini ){
		return this.episodio.addEpisodio(id, or, temp, sinop, tit, fini);
	}
	public boolean delEpisodio(long id){
		return this.episodio.delEpisodio(id);
	}
	public boolean addActor(String nombre,Date fecha,FileInputStream foto ){
		return this.actor.insertActor(nombre,fecha, foto);
	}
	public boolean addPers(String nombre, String desc){
		return this.personaje.insertActor(nombre, desc);
	}
	public float mediaEpisodioVotos(long idEpisodio) {
		return this.episodio.mediaVotosEpisodio(idEpisodio);
	}
	public List<String> listaComentsEpisodio(long id_episodio) {
		return this.comentario.listaComentariosEpi(id_episodio);
	}
	
	public List<Actor> listaTodosActores() {
		return this.actor.listActores();
	}
	public List<Personaje> listaTodosPersonajes() {
		return this.personaje.listaPersonajes();
	}
	public boolean actualizarEpisodio(long idSerie, long idEpisodio, int orden,int numtemp,String sin,
			String tit, Date fini){
		return this.episodio.actualizaEpisodio(idSerie, idEpisodio, orden, numtemp, sin, tit, fini);
	}
	public boolean insertaEpiActPer(long epi, long act, long per){
		return this.personajesEpisodios.insertaPersonajeActor(epi, act, per);
	}
	public List<PersonajeActor> listaActores(long idepisodio) {
		return this.personajesEpisodios.getActores(idepisodio);
	}
}
