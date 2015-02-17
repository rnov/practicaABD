package model;

import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.RelEpiPerActMapper;
import database.SerieMapper;

public class RelEpiPerAct {
	
	private long id_per;
	private long id_epi;
	private long id_act;
	private BBDDmanager bbdd= new BBDDmanager();
	private RelEpiPerActMapper mapper;
	public RelEpiPerAct() {
		super();
		this.id_act=0;
		this.id_epi=0;
		this.id_per=0;
	}

	public RelEpiPerAct(long id_per, long id_epi, long id_act) {
		super();
		this.id_per = id_per;
		this.id_epi = id_epi;
		this.id_act = id_act;
	}

	public long getId_per() {
		return id_per;
	}

	public void setId_per(long id_per) {
		this.id_per = id_per;
	}

	public long getId_epi() {
		return id_epi;
	}

	public void setId_epi(long id_epi) {
		this.id_epi = id_epi;
	}

	public long getId_act() {
		return id_act;
	}

	public void setId_act(long id_act) {
		this.id_act = id_act;
	}
	public void setMapper(MysqlDataSource source){
		this.mapper=new RelEpiPerActMapper(source);
	}
	public List<PersonajeActor> getActores(long idEp){
		this.setMapper(this.bbdd.getMysqlDataSource());
		return this.mapper.listasActPer(idEp);
	}
	public boolean insertaPersonajeActor(long epi, long act, long per){
		this.setMapper(this.bbdd.getMysqlDataSource());
		RelEpiPerAct obj = new RelEpiPerAct(per, epi, act);
		int rs=this.mapper.Insert(obj);
		return rs==1;
	}
}
