package database;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;


//Imports para pruebas en main
import javax.sql.DataSource;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---
public class UsuarioMapper extends AbstractMapper<Usuario,String>{//UserKeys

	private Usuario usr; 

	public UsuarioMapper(DataSource dataSource) {
		super(dataSource);
	
	}
	public UsuarioMapper(DataSource dataSource, Usuario _usr) {
		super(dataSource);
		this.usr= _usr;
	}


	@Override
	protected String getTableName() {
		
		return model.Constants.USER_TABLE ;
	}

	//@SuppressWarnings("null")
	@Override
	protected Usuario buildObject(ResultSet rs) throws SQLException {
		
		 Usuario result = new Usuario();
		 result.setId_user(rs.getString("nick"));
		 result.setPasswd(rs.getString("passwd"));
		 //TODO 
		 result.setPic(rs.getBinaryStream("img"));//pic
		 result.setFech(rs.getDate("fecha_nac"));//date
		 return result;
	}

	@Override
	protected Object[] serializeObject(Usuario object) {
		
		
		return new Object[] {object.getId_user(),object.getPasswd(),object.getFech(),object.getPic()};
	}
	
	@Override
	protected String[] getColumnNames() {
		
		 	return new String[]{"nick", "passwd", "fecha_nac", "img"};
	}

	@Override
	protected String pharseUpdate() {//NOTA: el nick no habría que pasarlo para hacerle update ya que no tiene sentido sobreescribirlo, pero si se pasa no pasa nada
		//column1=value1,column2=value2,...+WHERE some_column=some_value solo rellenamos por ahora nick y passwd, aunque sea nuestra clave el nick, el usuario no lo podra modificar asi que lo machacamos, volvemos a escribirlo.
		String colNam[] = getColumnNames();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ? ,"+colNam[3]+"= ?  where "+colNam[0]+"= ? ";
		
		return str;
	}

	@Override
	protected String pharseInsert() {
		String colNam[] = getColumnNames();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+","+colNam[3]+")"+ " values " + " ( ?  , ? , ? , ? ) ";//" ( " + " ? ," + " ? ,"  +" ? ,"  +" ?  "  + " )", no se puede insertar si no se rellena todos los ?
		return str;
	}
	@Override
	protected String[] getKeyColumnNames() {
		
		String[] keyCol={"nick"};
		return keyCol;
	}
	
	 @Override
	protected void fill(PreparedStatement pst, Usuario obj,boolean ins) {
			//el objeto que ya recibe ha de ser con todo actualizado, si es insert o modifica los datos han de ser los ultimos, se basa en "machacar" todo lo anterior
			try {
				
				pst.setString(1, obj.getId_user());//? de nick
				pst.setString(2,obj.getPasswd());// ? de passwd
				pst.setDate(3, obj.getFech());
				pst.setBinaryStream(4, obj.getPic());// ? de pic	
				//pst.setBinaryStream(3, fis,(int)file.length());
				if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
					pst.setString(5, obj.getId_user());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	 @Override
	protected Object[] serializeKey(String key) {
			
			return new Object[] { key };
		}
	 
	 //No se necesita este metodo en está clase por eso no se implementan
	@Override
	protected String getKey(Usuario obj) {
		
		return null;
	}
	
	 public static void main(String args[])
		        throws Exception
		    {
		 
		 //creamos y configuramos un objeto data source:
		 MysqlDataSource mysqlDataSource = new MysqlDataSource();
		 mysqlDataSource.setServerName("localhost");
		 mysqlDataSource.setDatabaseName("practica11");
		 mysqlDataSource.setPort(3306);
		 mysqlDataSource.setUser("root");
		 mysqlDataSource.setPassword("");
		 //establecemos la conexión con la BBDD, esto ya se hace dentro de cada una de las funciones antes listadas por eso prescindimos
		 //Connection con = mysqlDataSource .getConnection();
		
		 //creamos un usuarioMapper, pasandole un objeto DataSource
		 UsuarioMapper mapUsr = new UsuarioMapper( mysqlDataSource);
		
		//--------------FECHA
		 Calendar cale = new GregorianCalendar(1977,0,19);//NOTA: los meses empiezan en 0, es decir Enero=0, en la BBDD será 1.
		 Date fecha = new Date(cale.getTimeInMillis());
		 
		//--------------IMG
		 //File file = new File ("C:/al.jpg");//path , File
		 //FileInputStream fis=null;//fileInputStream
		// fis = new FileInputStream(file);
			
		 File file2 = new File ("C:/Mark.jpg");//path , File
		 FileInputStream fis2=new FileInputStream(file2);//fileInputStream 
		 
		 //Ejemplo Insert
		 //Usuario us=new Usuario("Ceco","pole23",fecha,fis2);//el usuario ya hecho que le pasa el controller con los datos a la ultima
		// mapUsr.Insert(us);	
		 
		
		 //Ejemplo Update
		//Usuario uss=new Usuario("Mark","GoreGora",fecha,fis2);
		//mapUsr.update(uss);
		 
		  //Ejemplos Delete
		  // mapUsr.Delete("rano") ;
		  // mapUsr.Delete("Mark") ;
		 
		 //Ejemplos select
		 //Usuario sr = new Usuario();
		 //sr = mapUsr.findById("Mark");
		
		 //System.out.println(sr.getFech());
		    }
	@Override
	protected String enlace() {
		
		return null;
	}
	@Override
	protected String cond(boolean m) {
		
		return null;
	}
	@Override
	protected String cond2(boolean m) {
		
		return null;
	}
	@Override
	protected String cond3() {
		
		return null;
	}

	 
	
	
	
	

	

	
	

	

}
