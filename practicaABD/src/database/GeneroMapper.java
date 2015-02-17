package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Imports para pruebas en main
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---


public class GeneroMapper extends AbstractMapper<String,String>{

	public GeneroMapper(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return  model.Constants.GENERO_TABLE;
	}

	@Override
	protected String[] getColumnNames() {
		// TODO Auto-generated method stub
		return new String[]{"id_gen"};
	}

	@Override
	protected String buildObject(ResultSet rs) throws SQLException {
		String result = rs.getString("id_gen");
		return result;
	}

	@Override
	protected Object[] serializeKey(String key) {
		// TODO Auto-generated method stub
		return new Object[] {key};
	}

	@Override
	protected Object[] serializeObject(String str) {
		return new Object[] {str};
	}

	@Override
	protected String[] getKeyColumnNames() {
		String[] keyCol={"id_gen"};
		return keyCol;
	}

	@Override
	protected String getKey(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(PreparedStatement pst, String str, boolean ins) {
		try {	
			pst.setString(1, str);// ? id_gen
			if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
				pst.setString(2, str);// ? id_gen
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected String pharseUpdate() {
		String colNam[] = getColumnNames();
		String str = colNam[0]+"= ? where "+colNam[0]+"= ?";
		return str;
	}

	@Override
	protected String pharseInsert() {
		String colNam[] = getColumnNames();
		String str = " ("+colNam[0]+")"
		+ " values " + " ( ?  ) ";
		return str;
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
	 GeneroMapper mapper = new GeneroMapper( mysqlDataSource);
	
	 
	 //Ejemplo Insert
	 //String cs="Drama";
	 //mapper.Insert(cs);	
	 
	
	 //Ejemplo Update,Nota: como en genero hay solo de un tipo no contempla la opcion de update, de todas formas el metodo esta implementado.
	 //String cs2="Accion";
	 //mapper.update(cs2);
	 
	 //obligatorio para utiliar id´s 
	 //UserKeys keyPer = new UserKeys(idC,idE);
	  
	 //Ejemplos Delete
	 //mapper.Delete("Drama");
	 
	 //Ejemplos select
		String cs3= "Drama";
	    cs3 = mapper.findById("Drama");
	
	   System.out.println(cs3);
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
