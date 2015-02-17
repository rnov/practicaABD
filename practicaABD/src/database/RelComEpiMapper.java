package database;

import model.UserKeys;
import model.RelComEpi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Imports para pruebas en main
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---

public class RelComEpiMapper extends AbstractMapper<RelComEpi,UserKeys> {

	public RelComEpiMapper(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return  model.Constants.RELCOMEPI_TABLE;
	}

	@Override
	protected String[] getColumnNames() {
		// TODO Auto-generated method stub
		return new String[]{"id_com", "id_epi"};
	}
	
	//@SuppressWarnings("null")
	@Override
	protected RelComEpi buildObject(ResultSet rs) throws SQLException {
		RelComEpi result = new RelComEpi();
		result.setId_com(rs.getLong("id_com"));
		result.setId_epi(rs.getLong("id_epi"));
		return result;
	}

	@Override
	protected Object[] serializeKey(UserKeys key) {
		// TODO Auto-generated method stub
		return new Object[] {key.getKey(), key.getKey2() };
	}

	@Override
	protected Object[] serializeObject(RelComEpi object) {
		// TODO Auto-generated method stub
		return new Object[] {object.getId_com(),object.getId_epi()};
	}

	@Override
	protected String[] getKeyColumnNames() {
		String[] keyCol={"id_com","id_epi"};
		return keyCol;
	}

	@Override
	protected UserKeys getKey(RelComEpi obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(PreparedStatement pst, RelComEpi obj, boolean ins) {
		try {	
			pst.setLong(1,obj.getId_com());// ? id_com
			pst.setLong(2, obj.getId_epi());// ? id_epi
			if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
				pst.setLong(3,obj.getId_com());// ? id_com
				pst.setLong(4, obj.getId_epi());// ? id_epi
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
	 RelComEpiMapper mapComEpi = new RelComEpiMapper( mysqlDataSource);
	
	 long idC = 9223372036854775778L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 long idE=9223372036854775801L;
	 
	 //Ejemplo Insert
	 RelComEpi cs=new RelComEpi(idC,idE);
	 mapComEpi.Insert(cs);	
	 
	
	 //Ejemplo Update
	// RelComSer cs2=new RelComSer(idC,idE);
	// mapComSer.update(cs2);
	 
	 //obligatorio para utiliar id´s 
	 //UserKeys keyPer = new UserKeys(idC,idE);
	  
	 //Ejemplos Delete
	//mapComEpi.Delete(keyPer);
	 
	 //Ejemplos select
		//RelComEpi cs3= new RelComEpi();
	    //cs3 = mapComEpi.findById(keyPer);
	
	   //System.out.println(cs3.getId_com());
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
