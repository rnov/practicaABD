package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.ComentarioMapper;
import database.RelComEpiMapper;
import database.RelComSerMapper;
import database.SerieMapper;

public class Comentario {
	
	
	private long id_comentario;
	private String id_usr;
	private long id_epi;
	private String comentario;
	private Date fecha;
	private BBDDmanager bbdd= new BBDDmanager();
	private ComentarioMapper mapper;
	private RelComEpiMapper mapperComEpi;
	private RelComSerMapper mapperComSerie;
	public Comentario() {
		super();
		this.id_comentario= 0;
		this.id_usr = null;
		this.comentario=null;
		this.fecha=null;
		this.id_epi=0;
	}
	/**
	 * Podemos prescindir del los contructores que son fechas, al trabajar con fecha-hora exacta, en el momento que se hace el comentario
	 * esta se obtiene dentro de la función fill que se encarga de inserta/modificar un comentario.
	 * */
	public Comentario(long id_comentario, String id_usr, String comentario, Date fecha) {
		super();
		this.id_comentario = id_comentario;
		this.id_usr = id_usr;
		this.comentario = comentario;
		this.fecha=fecha;
	}
	public Comentario(long idEpi, String comentario){
		this.id_comentario= 0;
		this.id_usr = null;
		this.comentario=comentario;
		this.fecha=null;
		this.id_epi=idEpi;
	}
	public Comentario(long id_comentario, String id_usr, String comentario) {
		super();
		this.id_comentario = id_comentario;
		this.id_usr = id_usr;
		this.comentario = comentario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public long getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(long id_comentario) {
		this.id_comentario = id_comentario;
	}

	public String getId_usr() {
		return id_usr;
	}

	public void setId_usr(String id_usr) {
		this.id_usr = id_usr;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public List<String> listaComentarios(long idSe){
		 //Lista de comentarios 
		 List<Comentario> coments; 
		 this.setMapper(this.bbdd.getMysqlDataSource());
		 coments = this.mapper.listasComent(idSe, false);
			
			List<String> slist = new ArrayList<String> ();
			for(int i=0; i<coments.size();i++){
				slist.add(coments.get(i).getComentario());
			}
			return slist;
	}
	public List<String> listaComentariosEpi(long id_epi){
		 //Lista de comentarios 
		 List<Comentario> coments; 
		 this.setMapper(this.bbdd.getMysqlDataSource());
		 coments = this.mapper.listasComent(id_epi, true);
			
			List<String> slist = new ArrayList<String> ();
			for(int i=0; i<coments.size();i++){
				slist.add(coments.get(i).getComentario());
			}
			return slist;
	}
	public void setMapper(MysqlDataSource source){
		this.mapper=new ComentarioMapper(source);
		this.mapperComEpi= new RelComEpiMapper(source);
		this.mapperComSerie= new RelComSerMapper(source);
	}
	public boolean comentarEpisodio(long idEpi, String coment, String idUsr){
		this.setMapper(this.bbdd.getMysqlDataSource());
		long nextId=this.mapper.getLastIndexInsert()+1;
		Comentario obj=new Comentario(nextId, idUsr, coment);
		int rs=this.mapper.Insert(obj);
		if(rs==1){
			RelComEpi cs=new RelComEpi(nextId,idEpi);
			rs=mapperComEpi.Insert(cs);
		}
		
		return rs==1;
	}
	public boolean comentarSerie(Long idSerie, String coment, String id_user) {
		this.setMapper(this.bbdd.getMysqlDataSource());
		long nextId=this.mapper.getLastIndexInsert()+1;
		Comentario obj=new Comentario(nextId, id_user, coment);
		int rs=this.mapper.Insert(obj);
		if(rs==1){
			RelComSer cs=new RelComSer(nextId, idSerie);
			rs=this.mapperComSerie.Insert(cs);
		}
		return rs==1;
	}
	

}
