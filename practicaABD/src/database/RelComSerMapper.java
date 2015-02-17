package database;
import model.UserKeys;
import model.RelComSer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Imports para pruebas en main
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---

public class RelComSerMapper extends AbstractMapper<RelComSer,UserKeys> {

	public RelComSerMapper(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return  model.Constants.RELCOMSER_TABLE;
	}

	@Override
	protected String[] getColumnNames() {
		// TODO Auto-generated method stub
		return new String[]{"id_com", "id_ser"};
	}
	
	//@SuppressWarnings("null")
	@Override
	protected RelComSer buildObject(ResultSet rs) throws SQLException {
		RelComSer result = new RelComSer();
		result.setId_com(rs.getLong("id_com"));
		result.setId_ser(rs.getLong("id_ser"));
		return result;
	}

	@Override
	protected Object[] serializeKey(UserKeys key) {
		// TODO Auto-generated method stub
		return new Object[] {key.getKey(), key.getKey2() };
	}

	@Override
	protected Object[] serializeObject(RelComSer object) {
		// TODO Auto-generated method stub
		return new Object[] {object.getId_com(),object.getId_ser()};
	}

	@Override
	protected String[] getKeyColumnNames() {
		String[] keyCol={"id_com","id_ser"};
		return keyCol;
	}

	@Override
	protected UserKeys getKey(RelComSer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(PreparedStatement pst, RelComSer obj, boolean ins) {
		try {	
			pst.setLong(1,obj.getId_com());// ? id_com
			pst.setLong(2, obj.getId_ser());// ? id_ser	
			if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
				pst.setLong(3,obj.getId_com());// ? id_com
				pst.setLong(4, obj.getId_ser());// ? id_ser	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected String pharseUpdate() {
		String colNam[] = getColumnNames();//adaptación a update al tener 2 claves
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? where "+colNam[0]+"= ? and "+colNam[1]+"= ?";
		return str;
	}

	@Override
	protected String pharseInsert() {
		String colNam[] = getColumnNames();
		String str = " ("+colNam[0]+","+colNam[1]+")"
		+ " values " + " ( ?  , ? ) ";
		return str;
	}

	public static void main(String args[])
	        throws Exception
	    {
		//NOTA "GUARNING": Cuando se tienen varias keys, el orden que están en getColumnames() y getKeyColumNames() 
		//tiene que ser el mismo ya que se cogerán en orden para formas las condiciones el resultado de estas dos fun
	 
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
	 RelComSerMapper mapComSer = new RelComSerMapper( mysqlDataSource);
	
	 long id = 9223372036854775778L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 long idS=9223372036854775000L;
	 
	 //Ejemplo Insert
	 RelComSer cs=new RelComSer(id,idS);
	 mapComSer.Insert(cs);	
	 
	
	 //Ejemplo Update
	// RelComSer cs2=new RelComSer(id,idS);
	// mapComSer.update(cs2);
	 
	 //obligatorio para utiliar id´s 
	 //UserKeys keyPer = new UserKeys(id,idS);
	  
	 //Ejemplos Delete
	//mapComSer.Delete(keyPer);
	 
	 //Ejemplos select
	   // RelComSer cs3= new RelComSer();
	   //cs3 = mapComSer.findById(keyPer);
	
	  // System.out.println(cs3.getId_com());
	    }

	@Override
	protected String enlace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String cond(boolean m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String cond2(boolean m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String cond3() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
