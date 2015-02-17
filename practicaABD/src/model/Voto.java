package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.EpisodioMapper;
import database.RelVotoEpiMapper;
import database.RelVotoSerMapper;
import database.VotoMapper;

public class Voto {

	private long id_voto;
	private String id_usr;
	private int valor;
	private BBDDmanager bbdd= new BBDDmanager();
	private VotoMapper maperVoto;
	private RelVotoSerMapper relMapper;
	private RelVotoEpiMapper relEpiMapper;
	public Voto() {
		super();
		this.id_voto= 0;
		this.id_usr= null;
		this.valor=0;
	}

	public Voto(long id_voto, String id_usr, int valor) {
		super();
		this.id_voto = id_voto;
		this.id_usr = id_usr;
		this.valor = valor;
	}

	public Voto(String idUs, int valor2) {
		
		this.id_usr=idUs;
		this.valor=valor2;
	}

	public long getId_voto() {
		return id_voto;
	}

	public void setId_voto(long id_voto) {
		this.id_voto = id_voto;
	}

	public String getId_usr() {
		return id_usr;
	}

	public void setId_usr(String id_usr) {
		this.id_usr = id_usr;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	public void setMapper(MysqlDataSource source){
		this.maperVoto=new VotoMapper(source);
		this.relMapper=new RelVotoSerMapper(source);
		this.relEpiMapper=new RelVotoEpiMapper(source);
	}
	public boolean votarSerie(long idS, String idUs, int valor){
		this.setMapper(this.bbdd.getMysqlDataSource());
		Voto vot=new Voto(this.maperVoto.getLastIndexInsert()+1,idUs, valor);
		int rs= this.maperVoto.Insert(vot);
		if(rs==1){
			RelVotoSer obj=new RelVotoSer(idS,this.relMapper.getLastIndexInsert());
			rs=this.relMapper.Insert(obj);
		}
		return rs==1;
		
	}
	public boolean votarEpisodio(long idE, String idUs, int valor){
		this.setMapper(this.bbdd.getMysqlDataSource());
		long id=this.maperVoto.getLastIndexInsert()+1;
		Voto vot=new Voto(id, idUs, valor);
		int rs= this.maperVoto.Insert(vot);
		if(rs==1){
			RelVotoEpi obj=new RelVotoEpi( id,idE);
			rs=this.relEpiMapper.Insert(obj);
		}
		return rs==1;
	}
}
