package model;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.BBDDmanager;
import database.UsuarioMapper;


/*
 *Declaramos pic como input stream para tratar mas facilmente a los datos(img) que queremos cargar. En los constructores Usuarios pasamos un "FileInputStream" porque  
 * al crear un objeto usuario lo crearemos a partir de la img ya hecha, (Un InputStream engloba un FileInputStream), Sin embargo en los setter/getter utilizaremos 
 * InputStreams, ya que los necesitaremos para tratar con los "resultSets" para crear los objetos al cargar de BBDD. 
 * */
public class Usuario {
	private String id_user;
	private String passwd;
    private	InputStream pic;// FileInputStream
    private Date fech;
    private UsuarioMapper mapUsr;
    private BBDDmanager bbdd= new BBDDmanager();
	
    public Usuario(){
		this.id_user=null;
		this.passwd=null;
		this.pic = null;
		this.fech=null;
	}

    public Usuario(String id_user, String passwd,Date fech,FileInputStream pic) {//InputStream has -> FileInputStream
		super();
		this.id_user = id_user;
		this.passwd = passwd;
		this.pic=pic;
		this.fech=fech;
	}
    public Usuario(String id_user, String passwd,Date fech) {//InputStream has -> FileInputStream
		super();
		this.id_user = id_user;
		this.passwd = passwd;
		this.pic=null;
		this.fech=fech;
	}
    
	public Date getFech() {
		return fech;
	}

	public void setFech(Date fech) {
		this.fech = fech;
	}

	public Usuario(String id_user, String passwd,FileInputStream pic) {//InputStream has -> FileInputStream
		super();
		this.id_user = id_user;
		this.passwd = passwd;
		this.pic=pic;//Blob
	}

	public Usuario(String id_user, String passwd) {
		super();
		this.id_user = id_user;
		this.passwd = passwd;
	}
		
	
	public InputStream getPic() {//Blob
		return pic;
	}
	public void setPic(InputStream inputStream) {//Blob
		this.pic = inputStream;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Usuario logado(String name, String passwd){
		
		Usuario temp=new Usuario(name, passwd);
		this.setMapper(this.bbdd.getMysqlDataSource());
		temp=this.mapUsr.findById(name);
		return temp;
		
	}
	public void setMapper(MysqlDataSource source){
		this.mapUsr=new UsuarioMapper(source);
	}
	public boolean insertUsuario(String name, String passwd){
		this.setMapper(this.bbdd.getMysqlDataSource());
		Calendar cale = new GregorianCalendar(1977,0,19);//NOTA: los meses empiezan en 0, es decir Enero=0, en la BBDD será 1.
		Date fecha = new Date(cale.getTimeInMillis());
		Usuario obj=new Usuario(name,passwd,fecha);
		int rs=this.mapUsr.Insert(obj);
		return (rs==1);
	}
	public Usuario actualizaUsuario(Usuario us, String newPasswd, Date newDate){
		this.setMapper(this.bbdd.getMysqlDataSource());
		Usuario obj=new Usuario(us.getId_user(), newPasswd, newDate);
		int rs=this.mapUsr.update(obj);
		if(rs==1) return obj;
		return null;
	}
	public Usuario actualizaUsuario(Usuario us, String newPasswd, Date newDate, FileInputStream foto){
		this.setMapper(this.bbdd.getMysqlDataSource());
		Usuario obj=new Usuario(us.getId_user(), newPasswd, newDate, foto);
		int rs=this.mapUsr.update(obj);
		if(rs==1){
			return obj;
		}
		else{
			return null;
		}
		
	}
}
