package model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.BBDDmanagerAdmin;
import database.SerieMapper;
import database.UsuarioMapper;


public class Serie {
		
		private long id_serie;
		//private BigInteger id_serie;
		private String nombre;
		private String titular;
		private String sinopsis;
		private Date fech_Est;
		private Date fech_fin;
		private BBDDmanager bbdd= new BBDDmanager();
		private BBDDmanagerAdmin bbdd2= new BBDDmanagerAdmin();
		 private SerieMapper mapSerie;
		public Serie(){
			this.id_serie= 0;
			this.nombre= null;
			this.sinopsis=null;
			this.titular=null;
			this.fech_Est=null;
			this.fech_fin=null;
		}
		public Serie(long id){
			this.id_serie= id;
			this.nombre= null;
			this.sinopsis=null;
			this.titular=null;
			this.fech_Est=null;
			this.fech_fin=null;
		}
		public Serie(String nombre){
			this.id_serie= 0;
			this.nombre= nombre;
			this.sinopsis=null;
			this.titular=null;
			this.fech_Est=null;
			this.fech_fin=null;
		}
		public Serie(long idSer, String nom, String sin, String tit,Date fechEst){
			this.id_serie= idSer;
			this.nombre= nom;
			this.sinopsis=sin;
			this.titular=tit;
			this.fech_Est=fechEst;
			this.fech_fin=null;	
		}
		public Serie(long idSer, String nom, String sin, String tit,Date fechEst,Date fechFin){
			this.id_serie= idSer;
			this.nombre= nom;
			this.sinopsis=sin;
			this.titular=tit;
			this.fech_Est=fechEst;
			this.fech_fin=fechFin;	
		}
		public Serie( String nom, String sin, String tit,Date fechEst,Date fechFin){
			this.id_serie= 0;
			this.nombre= nom;
			this.sinopsis=sin;
			this.titular=tit;
			this.fech_Est=fechEst;
			this.fech_fin=fechFin;	
		}
		public Serie(long idSer, String nom, String sin, String tit){
			this.id_serie= idSer;
			this.nombre= nom;
			this.sinopsis=sin;
			this.titular=tit;	
		}
		
		public Date getFech_Est() {
			return fech_Est;
		}
		public void setFech_Est(Date fech_Est) {
			this.fech_Est = fech_Est;
		}
		public Date getFech_fin() {
			return fech_fin;
		}
		public void setFech_fin(Date fech_fin) {
			this.fech_fin = fech_fin;
		}
		public long getId_serie() {
			return id_serie;
		}
		public void setId_serie(long id_serie) {
			this.id_serie = id_serie;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getTitular() {
			return titular;
		}
		public void setTitular(String titular) {
			this.titular = titular;
		}
		public String getSinopsis() {
			return sinopsis;
		}
		public void setSinopsis(String sinopsis) {
			this.sinopsis = sinopsis;
		}
		public void setMapper(MysqlDataSource source){
			this.mapSerie=new SerieMapper(source);
		}
		public List<Serie> buscarSerieString(String key){
			Serie ob=new Serie(key);
			this.setMapper(this.bbdd.getMysqlDataSource());
			
			return this.mapSerie.listasObje(key, false);
		}
		public List<Serie> buscarSeriesString(String key){
			Serie ob=new Serie(key);
			this.setMapper(this.bbdd.getMysqlDataSource());
			return  this.mapSerie.listasObje(key, true);
		}
		public Serie getSerieFromKey(long idSerie){
			Serie serieOk=new Serie();
			this.setMapper(this.bbdd.getMysqlDataSource());
			List<Serie> serie = this.mapSerie.listaSeries(idSerie, false);
			return serie.get(0);
		}
		public void setMapper2(MysqlDataSource source){
			this.mapSerie=new SerieMapper(source);
		}
		public boolean actualizarSerie(Long idSer,String nom, String sin, String tit,Date fini, Date fFin){
			this.setMapper(this.bbdd2.getMysqlDataSource());
			Serie serieOk=new Serie(idSer, nom, sin, tit,fini,fFin);
			int rs=this.mapSerie.update(serieOk);
			return (rs==1);
		}
		public boolean insertarSerie(String nombre, String sin, String tit, Date fini, Date Ffin){
			this.setMapper(this.bbdd2.getMysqlDataSource());
			Serie serieOk=new Serie( nombre, sin, tit,fini,Ffin);
			int rs=this.mapSerie.Insert(serieOk);
			return (rs==1);
		}
		public float mediaVotosSerie(long id){
			this.setMapper(this.bbdd.getMysqlDataSource());
			Serie serieOk=new Serie( id);
			return this.mapSerie.media(id);
		}
		
}
