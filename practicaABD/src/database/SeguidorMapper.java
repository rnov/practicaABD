package database;
import model.Seguidor;
import model.UserKeys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Imports para pruebas en main
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---


public class SeguidorMapper extends AbstractMapper<Seguidor,UserKeys>{

	public SeguidorMapper(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		return  model.Constants.SEGUIDOR_TABLE;
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{ "id_ser","id_usr"};
	}

	@Override
	protected Seguidor buildObject(ResultSet rs) throws SQLException {
		Seguidor result = new Seguidor();
		result.setId_ser(rs.getLong("id_ser"));
		result.setId_usr(rs.getString("id_usr"));
		return result;
	}

	@Override
	protected Object[] serializeKey(UserKeys key) {
		return new Object[] {key.getKey(), key.getKeyS() };
	}

	@Override
	protected Object[] serializeObject(Seguidor object) {
		return new Object[] {object.getId_ser(),object.getId_usr()};
	}

	@Override
	protected String[] getKeyColumnNames() {
		 String[] keyCol ={ "id_ser","id_usr"};
			return keyCol;
	}

	@Override
	protected UserKeys getKey(Seguidor obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(PreparedStatement pst, Seguidor obj, boolean ins) {
		try {	
			pst.setLong(1,obj.getId_ser());// ? id_ser
			pst.setString(2, obj.getId_usr());// ? id_usr	
			if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
				pst.setLong(3,obj.getId_ser());// ? id_ser
				pst.setString(4, obj.getId_usr());// ? id_usr	
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
	 SeguidorMapper mapSeg = new SeguidorMapper( mysqlDataSource);
	
	 String idU = "Klaus";
	 long idS=9223372036854775000L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 
	 //Ejemplo Insert
	 Seguidor cs=new Seguidor(idS,idU);
	 mapSeg.Insert(cs);	
	 
	
	 //Ejemplo Update
	 //Seguidor cs2=new Seguidor(idS,idU);
	 //mapSeg.update(cs2);
	 
	 //obligatorio para utiliar id´s 
	//UserKeys keyPer = new UserKeys(idS,idU);
	  
	 //Ejemplos Delete
	 //mapSeg.Delete(keyPer);
	 
	 //Ejemplos select
	 //Seguidor cs3= new Seguidor();
	 //cs3 = mapSeg.findById(keyPer);
	
	//System.out.println(cs3.getId_ser());
	   
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
