package database;
import model.UserKeys;
import model.RelSerGen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Imports para pruebas en main
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---

public class RelSerGenMapper extends AbstractMapper<RelSerGen,UserKeys>{

	public RelSerGenMapper(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return  model.Constants.RELSERGEN_TABLE;
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{ "id_ser","id_gen"};
	}
	
	
	@Override
	protected RelSerGen buildObject(ResultSet rs) throws SQLException {
		RelSerGen result = new RelSerGen();
		result.setId_gen(rs.getString("id_gen"));
		result.setId_ser(rs.getLong("id_ser"));
		return result;
	}

	@Override
	protected Object[] serializeKey(UserKeys key) {
		// TODO Auto-generated method stub
		return new Object[] {key.getKey(), key.getKeyS() };
	}

	@Override
	protected Object[] serializeObject(RelSerGen object) {
		// TODO Auto-generated method stub
		return new Object[] {object.getId_gen(),object.getId_ser()};
	}

	@Override
	protected String[] getKeyColumnNames() {
		 String[] keyCol ={ "id_ser","id_gen"};
		return keyCol;
	}

	@Override
	protected UserKeys getKey(RelSerGen obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(PreparedStatement pst, RelSerGen obj, boolean ins) {
		try {	
			pst.setLong(1,obj.getId_ser());// ? id_ser
			pst.setString(2, obj.getId_gen());// ? id_gen	
			if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
				pst.setLong(3,obj.getId_ser());// ? id_ser
				pst.setString(4, obj.getId_gen());// ? id_gen	
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
	 RelSerGenMapper mapSerGen = new RelSerGenMapper( mysqlDataSource);
	
	 long idS = 9223372036854775000L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 String idG="Drama";
	 
	 //Ejemplo Insert
	 RelSerGen cs=new RelSerGen(idS,idG);
	 mapSerGen.Insert(cs);	
	 
	
	 //Ejemplo Update
	// RelSerGen cs2=new RelSerGen(idS,idG);
	// mapSerGen.update(cs2);
	 
	 //obligatorio para utiliar id´s 
	 //UserKeys keyPer = new UserKeys(idS,idG);
	  
	 //Ejemplos Delete
	 //mapSerGen.Delete(keyPer);
	 
	 //Ejemplos select
	// RelSerGen cs3= new RelSerGen();
	 //cs3 = mapSerGen.findById(keyPer);
	
	  // System.out.println(cs3.getId_ser());
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
