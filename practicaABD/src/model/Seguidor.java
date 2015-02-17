package model;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.SeguidorMapper;
import database.UsuarioMapper;

public class Seguidor {
	
	private long id_ser;
	private String id_usr;
	private SeguidorMapper maperSeguidor;
	private BBDDmanager bbdd= new BBDDmanager();
	public Seguidor() {
		super();
		this.id_usr=null;
		this.id_ser=0;
	}

	public Seguidor(long id_ser, String id_usr) {
		super();
		this.id_ser = id_ser;
		this.id_usr = id_usr;
	}

	public long getId_ser() {
		return id_ser;
	}

	public void setId_ser(long id_ser) {
		this.id_ser = id_ser;
	}

	public String getId_usr() {
		return id_usr;
	}

	public void setId_usr(String id_usr) {
		this.id_usr = id_usr;
	}
	public void setMapper(MysqlDataSource source){
		this.maperSeguidor=new SeguidorMapper(source);
	}
	public int usuarioSigueSerie(long idserie, String idUser){
		this.setMapper(this.bbdd.getMysqlDataSource());
		Seguidor obj=new Seguidor(idserie, idUser);
		int rs=this.maperSeguidor.Insert(obj);
		if(rs==0){
			UserKeys keyPer = new UserKeys(idserie,idUser);
			rs=3;
			this.maperSeguidor.Delete(keyPer);
		}
		return rs;
	}
	
}
