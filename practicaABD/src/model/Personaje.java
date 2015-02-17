package model;

import java.sql.Date;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.ActorMapper;
import database.BBDDmanager;
import database.PersonajeMapper;
import database.SerieMapper;

public class Personaje {
	
		private long id_personaje;
		private String nombre ;
		private String descr;
		private BBDDmanager bbdd= new BBDDmanager();
		private PersonajeMapper mapper;
		public Personaje() {
			super();
			this.id_personaje = 0;
			this.nombre = null;
			this.descr = null;
		}
		
		public Personaje(long id_personaje, String nombre, String descr) {
			super();
			this.id_personaje = id_personaje;
			this.nombre = nombre;
			this.descr = descr;
		}
		public Personaje( String nombre, String descr) {
			super();
			this.id_personaje = 0;
			this.nombre = nombre;
			this.descr = descr;
		}
		public long getId_personaje() {
			return id_personaje;
		}

		public void setId_personaje(long id_personaje) {
			this.id_personaje = id_personaje;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDescr() {
			return descr;
		}

		public void setDescr(String descr) {
			this.descr = descr;
		}
		public void setMapper(MysqlDataSource source){
			this.mapper=new PersonajeMapper(source);
		}
		public boolean insertActor(String nom,String desc){
			this.setMapper(this.bbdd.getMysqlDataSource());
			long id=this.mapper.getLastIndexInsert()+1;
			Personaje obj=new Personaje(id,nom, desc);
			int rs=this.mapper.Insert(obj);
			return rs==1;
		}
	
		public List<Personaje> listaPersonajes(){
			this.setMapper(this.bbdd.getMysqlDataSource());
			return this.mapper.listaPersonajes();
		}
	
}
