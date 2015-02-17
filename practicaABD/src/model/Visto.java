package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.VistoMapper;

public class Visto {
		
		private String id_usr;
		private long id_epi;
		private BBDDmanager bbdd= new BBDDmanager();
		private VistoMapper mapper;
		public Visto() {
			super();
			this.id_usr = null;
			this.id_epi= 0;
		}

		public Visto(String id_usr, long id_epi) {
			super();
			this.id_usr = id_usr;
			this.id_epi = id_epi;
		}

		public String getId_usr() {
			return id_usr;
		}

		public void setId_usr(String id_usr) {
			this.id_usr = id_usr;
		}

		public long getId_epi() {
			return id_epi;
		}

		public void setId_epi(long id_epi) {
			this.id_epi = id_epi;
		}
		public void setMapper(MysqlDataSource source){
			this.mapper=new VistoMapper(source);
		}
		public boolean marcarComoVisto(String idUser, long idEpi){
			this.setMapper(this.bbdd.getMysqlDataSource());
			Visto visto=new Visto(idUser,idEpi);
			int rs=this.mapper.Insert(visto);
			return true;
		}
	
		
}
