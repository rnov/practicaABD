package database;
import model.Visto;
import model.UserKeys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Imports para pruebas en main
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---

public class VistoMapper extends AbstractMapper<Visto,UserKeys>{

	public VistoMapper(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return  model.Constants.VISTO_TABLE;
	}

	@Override
	protected String[] getColumnNames() {
		// TODO Auto-generated method stub
		return new String[]{"id_usr", "id_epi"};
	}
	
	//@SuppressWarnings("null")
	@Override
	protected Visto buildObject(ResultSet rs) throws SQLException {
		Visto result = new Visto();
		result.setId_usr(rs.getString("id_usr"));
		result.setId_epi(rs.getLong("id_epi"));
		return result;
	}

	
	@Override
	protected Object[] serializeKey(UserKeys key) {
		// TODO Auto-generated method stub
		return new Object[] {key.getKeyS(), key.getKey() };
	}

	@Override
	protected Object[] serializeObject(Visto object) {
		// TODO Auto-generated method stub
		return new Object[] {object.getId_usr(),object.getId_epi()};
	}

	@Override
	protected String[] getKeyColumnNames() {
		String[] keyCol={"id_usr","id_epi"};
		return keyCol;
	}

	@Override
	protected UserKeys getKey(Visto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(PreparedStatement pst, Visto obj, boolean ins) {
		try {	
			pst.setString(1,obj.getId_usr());// ? id_usr
			pst.setLong(2, obj.getId_epi());// ? id_epi	
			if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
				pst.setLong(3, obj.getId_epi());
				pst.setString(4,obj.getId_usr());
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
	// VistoMapper mapVisto = new VistoMapper( mysqlDataSource);
	
	 //long id = 9223372036854775801L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 //String idUsr="Mark";
	 
	 //Ejemplo Insert
	 //Visto visto=new Visto("Magnus",id);
	 //mapVisto.Insert(visto);	
	 
	
	 //Ejemplo Update
	// Visto visto2=new Visto("Klaus",id);
	// mapVisto.update(visto2);
	 
	 //obligatorio para utiliar id´s 
	 //UserKeys keyPer = new UserKeys(id,idUsr);
	  
	 //Ejemplos Delete
	//mapVisto.Delete(keyPer);
	 
	 //Ejemplos select
	   // Visto visto3 = new Visto();
	   //visto3 = mapVisto.findById(keyPer);
	
	  // System.out.println(visto3.getId_epi());
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
