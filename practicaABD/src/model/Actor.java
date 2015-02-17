package model;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.ActorMapper;
import database.BBDDmanager;
import database.SerieMapper;

/*
 *Declaramos pic como input stream para tratar mas facilmente a los datos(img) que queremos cargar. En los constructores Usuarios pasamos un "FileInputStream" porque  
 * al crear un objeto usuario lo crearemos a partir de la img ya hecha, (Un InputStream engloba un FileInputStream), Sin embargo en los setter/getter utilizaremos 
 * InputStreams, ya que los necesitaremos para tratar con los "resultSets" para crear los objetos al cargar de BBDD. 
 * */
public class Actor {

	private long id_actor;
	private String nom_artistico;
	private	InputStream pic;// FileInputStream
	private Date fech;
	private BBDDmanager bbdd= new BBDDmanager();
	private ActorMapper mapper;
	public Actor() {
		super();
		this.id_actor = 0;
		this.nom_artistico = null;
		this.pic=null;
		this.fech=null;
	}
	public Actor(String nom, Date fecha) {
		super();
		this.id_actor = 0;
		this.nom_artistico = nom;
		this.pic=null;
		this.fech=fecha;
	}
	public Actor(long id_actor, String nom_artistico,Date fech,FileInputStream pic) {
		super();
		this.id_actor = id_actor;
		this.nom_artistico = nom_artistico;
		this.pic= pic;
		this.fech=fech;
	}
	
	public Actor(long id_actor, String nom_artistico,FileInputStream pic) {
		super();
		this.id_actor = id_actor;
		this.nom_artistico = nom_artistico;
		this.pic= pic;
	}
	
	public Actor(long id_actor, String nom_artistico) {
		super();
		this.id_actor = id_actor;
		this.nom_artistico = nom_artistico;
	}
	
	public Date getFech() {
		return fech;
	}

	public void setFech(Date fech) {
		this.fech = fech;
	}

	public InputStream getPic() {
		return pic;
	}

	public void setPic(InputStream pic) {
		this.pic = pic;
	}

	public long getId_actor() {
		return id_actor;
	}
	public void setId_actor(long id_actor) {
		this.id_actor = id_actor;
	}
	public String getNom_artistico() {
		return nom_artistico;
	}
	public void setNom_artistico(String nom_artistico) {
		this.nom_artistico = nom_artistico;
	}
	public void setMapper(MysqlDataSource source){
		this.mapper=new ActorMapper(source);
	}
	
	public boolean insertActor(String nom, java.sql.Date fecha, FileInputStream foto){
		this.setMapper(this.bbdd.getMysqlDataSource());
		long id=this.mapper.getLastIndexInsert()+1;
		Actor obj =new Actor(id, nom,fecha, foto);
		int rs=this.mapper.Insert(obj);
		return rs==1;
	}
	
	public List<Actor> listActores(){
		this.setMapper(this.bbdd.getMysqlDataSource());
		return this.mapper.listaActores();
	}
}
