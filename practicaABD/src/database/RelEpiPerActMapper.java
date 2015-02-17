package database;
import model.Episodio;
import model.RelEpiPerAct;
import model.UserKeys;
import model.PersonajeActor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.List;


//Imports para pruebas en main
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//---

public class RelEpiPerActMapper extends AbstractMapper<RelEpiPerAct,UserKeys> {

	public RelEpiPerActMapper(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		return  model.Constants.RELEPIPERACT_TABLE;
	}

	@Override
	protected String[] getColumnNames() {
		// TODO Auto-generated method stub
		return new String[]{"id_per", "id_epi","id_act"};
	}

	@Override
	protected RelEpiPerAct buildObject(ResultSet rs) throws SQLException {
		RelEpiPerAct result = new RelEpiPerAct();
		result.setId_act(rs.getLong("id_act"));
		result.setId_epi(rs.getLong("id_epi"));
		result.setId_per(rs.getLong("id_per"));
		return result;
	}

	@Override
	protected Object[] serializeKey(UserKeys key) {
		// TODO Auto-generated method stub
		return new Object[] {key.getKey(), key.getKey2(),key.getKey3() };
	}

	@Override
	protected Object[] serializeObject(RelEpiPerAct object) {
		// TODO Auto-generated method stub
		return new Object[] {object.getId_act(),object.getId_epi(),object.getId_per()};
	}

	@Override
	protected String[] getKeyColumnNames() {
		String[] keyCol={"id_per","id_epi","id_act"};
		return keyCol;
	}

	@Override
	protected UserKeys getKey(RelEpiPerAct obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void fill(PreparedStatement pst, RelEpiPerAct obj, boolean ins) {
		try {	
			pst.setLong(1,obj.getId_per());// ? id_per
			pst.setLong(2, obj.getId_epi());// ? id_epi
			pst.setLong(3, obj.getId_act());// ? id_act
			if(!ins){//si es update necesitamos rellenar la condicion where con el nick .
				pst.setLong(4,obj.getId_per());// ? id_per
				pst.setLong(5, obj.getId_epi());// ? id_epi
				pst.setLong(6, obj.getId_act());// ? id_act
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected String pharseUpdate() {
		String colNam[] = getColumnNames();//adaptación a update al tener 3 claves
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+ colNam[2]+"= ? where "+colNam[0]+"= ? and "+colNam[1]+"= ? and "+colNam[2]+"= ?";
		return str;
	}

	@Override
	protected String pharseInsert() {
		String colNam[] = getColumnNames();
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+")"
		+ " values " + " ( ?  , ? , ? ) ";
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
	 RelEpiPerActMapper mapper = new RelEpiPerActMapper( mysqlDataSource);
	
	 long idA = 9223372036854775751L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 long idP = 9223372036854775799L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 long idE=9223372036854775801L;//La L "the compiler will try to parse the literal as an int, hence the error message"
	 
	 //Ejemplo Insert
	//RelEpiPerAct cs=new RelEpiPerAct(idP,idE,idA);
	 //mapper.Insert(cs);	
	 
	
	 //Ejemplo Update
	 //RelEpiPerAct cs2=new RelEpiPerAct(idP,idE,idA);
	 //mapper.update(cs2);
	 
	 //obligatorio para utiliar id´s 
	//UserKeys keyPer = new UserKeys(idP,idE,idA);
	  
	 //Ejemplos Delete
	 //mapper.Delete(keyPer);
	 
	 //Ejemplos select
	 //RelEpiPerAct cs3= new RelEpiPerAct();
	 //cs3 = mapper.findById(keyPer);
	
	//System.out.println(cs3.getId_act());
	 
	 //Pruebas 
	 List<PersonajeActor> epis; 
	 PersonajeActor epi =null;
	 long idE2=9223372036854775701L;
	 epis= mapper.listasActPer(idE);
	 //epis= mapper.listasActPer(idE);
	 
	epi = epis.get(0);
	System.out.println(epi.getNomArt());
	System.out.println(epi.getNomPers());
	epi = epis.get(1);
	System.out.println(epi.getNomArt());
	System.out.println(epi.getNomPers());
	   
	    }
	/**
	 * Metodo que devuelve un ArrayList de PersonajeActor (clase auxiliar del modelo que tiene nombre actor y su personaje)
	 * Al pasarle una id de un episodio , el metodo busca los nombres de los actrores  y de sus personajes de dicho episodio y los devuelve
	 * en un ArrayList
	 * @param id de un episodio
	 * @return ArrayList PersonajeActor
	 */
	public List<PersonajeActor> listasActPer(long id){	
		Connection con =null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		PersonajeActor result=null;
		List<PersonajeActor> objList = new ArrayList<PersonajeActor>();
		try{
			con= ds.getConnection();//Attempts to establish a connection with the data source that this DataSource object represents.
			String sql=null;
			
			sql ="select  actores.nom_artistico,personajes.nombre from actores natural join relepiperact natural join personajes where relepiperact.id_epi = "+id+ 
					" and relepiperact.id_act = actores.id_actor  and relepiperact.id_per= personajes.id_personaje " ;
			System.out.println(sql);
			
			pst = con.prepareStatement(sql);//An object that represents a pre-compiled SQL statement. 
			
			rs=pst.executeQuery();//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query
			
			while(rs.next()){	
				result = builtObject2(rs);//build the object obtain from the select
				objList.add(result);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null) rs.close();
				if(pst!=null) pst.close();
				if(con!=null) con.close();
			}catch (Exception e){}
		}
		return objList;
	}	
	/**
	 * Metodo axiliar que recibe un objeto de ResultSet y lo vuelca en 
	 * un objeto PersonajeActor.
	 * @param rs
	 * @return Objeto PersonajeActor
	 * @throws SQLException
	 */
	public PersonajeActor builtObject2(ResultSet rs)throws SQLException{
		PersonajeActor result = new PersonajeActor();
		result.setNomArt(rs.getString("nom_artistico"));//nombre actor 
		result.setNomPers(rs.getString("nombre"));//nombre personaje 	
		return result;
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
