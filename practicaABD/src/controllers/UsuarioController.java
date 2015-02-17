package controllers;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;






import java.util.List;

import gui.Login;
import model.Comentario;
import model.Episodio;
import model.PersonajeActor;
import model.RelEpiPerAct;
import model.Seguidor;
import model.Serie;
import model.Usuario;
import model.Visto;
import model.Voto;

public class UsuarioController {
	private Usuario user;
	private Login winLog;
	private Serie serie;
	private Seguidor seguidor;
	private Episodio episodio;
	private Visto visto;
	private Voto voto;
	private RelEpiPerAct personajesEpisodios;
	private Comentario comentario;
	public UsuarioController(Login login) {
		this.winLog=login;
		this.user=new Usuario();
		this.serie=new Serie();
		this.seguidor=new Seguidor();
		this.episodio= new Episodio();
		this.visto=new Visto();
		this.voto=new Voto();
		this.personajesEpisodios=new RelEpiPerAct();
		this.comentario=new Comentario();
	}
	public Date getEdadUsuario(){
		return this.user.getFech();
	}
	/**
	 * Loga a un usuario en la aplicación.
	 * @param name
	 * @param passwd
	 * @return
	 */
	public boolean logarse(String name, String passwd){
		this.user=this.user.logado(name, passwd);
		if(this.user==null)return false;
		else return true;
	}
	public String getNombre(){
		return this.user.getId_user();
	}
	public InputStream getFoto(){
		return this.user.getPic();
	}
	public boolean newUser(String name, String passwd){
		return this.user.insertUsuario(name, passwd);
	}
	public Date getFechaNa(){
		return this.user.getFech();
	}
	public boolean updateData(String passwd, Date fecha, FileInputStream foto){
		this.user=this.user.actualizaUsuario(this.user, passwd,fecha, foto);
		if(this.user==null) return false;
		else{
			return true;
		}
		
	}
	
	public boolean updateData(String passwd, Date fecha){
		this.user=this.user.actualizaUsuario(this.user, passwd,fecha);
		if(this.user==null) return false;
		else{
			return true;
		}
		
	}
	public List<Serie> getList(String key){
		return this.serie.buscarSerieString(key);
	}
	public boolean getDataSerie(long key){
		this.serie=this.serie.getSerieFromKey(key);
		if(serie!=null) return true;
		else return false;
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
	public int altaBajaUsuarioSerie(Long idSerie){
		int rs=this.seguidor.usuarioSigueSerie(idSerie, this.user.getId_user());
		return rs;
	}
	public List<Serie> getListSeriesByUser() {
		return this.serie.buscarSeriesString(this.user.getId_user());
	}
	public List<Episodio> episodiosXserie(long key){
		return this.episodio.getEpisodiosFromSerieKey(null,key);
	}
	public List<Episodio> episodiosXserieV(long key){
		return this.episodio.getEpisodiosFromSerieKey(this.user.getId_user(),key);
	}
	public List<Episodio> episodiosNoVistos(long key){
		return this.episodio.getEpisodiosFromSerieKey(this.user.getId_user(),key);
	}
	public boolean marcarComoVisto(long idEpi){
		return this.visto.marcarComoVisto(this.user.getId_user(), idEpi);
	}
	public float mediaSerieVotos(Long idSerie){
		return this.serie.mediaVotosSerie(idSerie);
	}
	public boolean votarSerie(long idSerie,int valor){
		return this.voto.votarSerie(idSerie, this.user.getId_user(), valor);
	}
	public boolean votarEpisodio(long idEpi,int valor){
		return this.voto.votarEpisodio(idEpi, this.user.getId_user(), valor);
	}
	public float mediaEpisodioVotos(long idEpi){
		return this.episodio.mediaVotosEpisodio(idEpi);
	}
	public List<PersonajeActor> listaActores(long idEpi){
		return this.personajesEpisodios.getActores(idEpi);
	}
	public List<String> listaComentsSerie(long idSerie){
		return this.comentario.listaComentarios(idSerie);
	}
	public boolean comentarSerie(Long idSerie,String Coment){
		return this.comentario.comentarSerie(idSerie, Coment, this.user.getId_user());
	}
	public List<String> listaComentsEpisodio(long idEpi){
		return this.comentario.listaComentariosEpi(idEpi);
	}
	public boolean comentarEpisodio(Long idEpi,String coment){
		return this.comentario.comentarEpisodio(idEpi, coment, this.user.getId_user());
	}
}
