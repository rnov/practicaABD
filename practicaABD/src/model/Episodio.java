package model;
import java.sql.Date;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.BBDDmanagerAdmin;
import database.EpisodioMapper;
import database.SerieMapper;



public class Episodio {

	private long id_episodio;
	private long id_ser;
	private int num_orden ;
	private int num_temp;
	private String sinopsis;
	private String titulo;
	private Date fech;
	private BBDDmanager bbdd= new BBDDmanager();
	private BBDDmanagerAdmin bbdd2= new BBDDmanagerAdmin();
	private EpisodioMapper maperEpisodio;

	private SerieMapper serieMapper;
	public Episodio() {
		super();
		this.id_episodio = 0;
		this.id_ser=0;
		this.num_orden = 0;
		this.num_temp = 0;
		this.sinopsis = null;
		this.titulo = null;
		this.fech=null;
	}
	public Episodio(long id_episodio,long id_ser, int num_orden, int num_temp,
			String sinopsis, String titulo, Date fech) {
		super();
		this.id_episodio = id_episodio;
		this.id_ser=id_ser;
		this.num_orden = num_orden;
		this.num_temp = num_temp;
		this.sinopsis = sinopsis;
		this.titulo = titulo;
		this.fech=fech;
	}
	public Episodio(long id_ser, int num_orden, int num_temp,
			String sinopsis, String titulo, Date fech) {
		
		this.id_episodio = 0;
		this.id_ser=id_ser;
		this.num_orden = num_orden;
		this.num_temp = num_temp;
		this.sinopsis = sinopsis;
		this.titulo = titulo;
		this.fech=fech;
	}
	
	
	public Episodio(long id_episodio, int num_orden, int num_temp,
			String sinopsis, String titulo) {
		super();
		this.id_episodio = id_episodio;
		this.num_orden = num_orden;
		this.num_temp = num_temp;
		this.sinopsis = sinopsis;
		this.titulo = titulo;
	}

	public long getId_ser() {
		return id_ser;
	}
	public void setId_ser(long id_ser) {
		this.id_ser = id_ser;
	}
	public Date getFech() {
		return fech;
	}

	public void setFech(Date fech) {
		this.fech = fech;
	}

	public long getId_episodio() {
		return id_episodio;
	}

	public void setId_episodio(long id_episodio) {
		this.id_episodio = id_episodio;
	}

	public int getNum_orden() {
		return num_orden;
	}

	public void setNum_orden(int num_orden) {
		this.num_orden = num_orden;
	}

	public int getNum_temp() {
		return num_temp;
	}

	public void setNum_temp(int num_temp) {
		this.num_temp = num_temp;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setMapper(MysqlDataSource source){
		this.maperEpisodio=new EpisodioMapper(source);
	}
	public void setMapperV(MysqlDataSource source){
		this.serieMapper=new SerieMapper(source);
	
	}
	public List<Episodio> getEpisodiosFromSerieKey(String key,long idSerie ){
		this.setMapper(this.bbdd.getMysqlDataSource());
		this.setMapperV(this.bbdd.getMysqlDataSource());
		List<Episodio> n=this.maperEpisodio.listasObje(key, idSerie);
		return n;
	}
	public boolean delEpisodio(long id){
		this.setMapper(this.bbdd2.getMysqlDataSource());
		
		UserKeys keyEpi = new UserKeys(id);
		int rs=this.maperEpisodio.Delete(keyEpi);
		return rs==1;
	}
	public boolean actualizaEpisodio(long idSerie, long idEpi, int ord, int temp, String sin,
			String tit, Date fecha){
		this.setMapper(this.bbdd.getMysqlDataSource());
		this.setMapperV(this.bbdd.getMysqlDataSource());
		Episodio epi2=new Episodio(idEpi,idSerie,ord,temp,sin,tit,fecha);
		int rs=this.maperEpisodio.update(epi2);
		
		return rs==1;
	}
	public boolean addEpisodio(long idS, int or, int temp, String sinop, String tit, Date fini){
		Episodio epi=new Episodio(idS, or, temp, sinop, tit,fini);
		this.setMapper(this.bbdd2.getMysqlDataSource());
		int rs=this.maperEpisodio.Insert(epi);
		return rs==1;
	}
	
	public float mediaVotosEpisodio(long id){
		this.setMapper(this.bbdd.getMysqlDataSource());
		Serie serieOk=new Serie( id);
		return this.maperEpisodio.media(id);
	}
	
	
	
	
	
	
}
