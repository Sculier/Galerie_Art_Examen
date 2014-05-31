package pojoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import outilDAO.Dao;
import outilDAO.MyException;
import pojo.Pays;


public  class PaysDao extends Dao<Pays, Integer> {

	public PaysDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Pays obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdPays().intValue()==0){
			
			String sql = "INSERT INTO pays VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getNom());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdPays(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le pays a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(Pays obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdPays().intValue() != 0){
			
			String sql = "UPDATE Pays SET nom = ? WHERE idPays = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setString(1, obj.getNom());
				pst.setInt(2, obj.getIdPays());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le pays n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("Le pays n'avait pas encore été sauvegardé. La modification "
					+ "d'un Pays, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Pays obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdPays().intValue() != 0){
			
			String sql = "DELETE FROM pays WHERE idPays = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdPays());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le pays n'a pas été supprimé");
			}
				
		}else{
			throw new MyException ("Le pays, dont vous demandez qu'il soit supprimé,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Pays find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM pays WHERE idPays = ?;";
			ResultSet rs = null;
			Pays pays = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				pays = new Pays();
				pays.setIdPays(rs.getInt(1));
				pays.setNom(rs.getString(2));
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le pays recherché n'a pas été trouvé.");
			} 
			return pays;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Pays> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM pays;";
		ResultSet rs = null;
		List <Pays> listePays = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listePays = new ArrayList<Pays>();
			Pays pays = null;
			
			while (rs.next()){
				pays = new Pays();
				pays.setIdPays(rs.getInt(1));
				pays.setNom(rs.getString(2));
				listePays.add(pays);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des pays");
		}		
		return listePays;
	}

	public List<Pays> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM pays WHERE idPays BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Pays> listePays = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listePays = new ArrayList<Pays>();
				Pays cat = null;
				
				while (rs.next()){
					cat=new Pays();
					cat.setIdPays(rs.getInt(1));
					cat.setNom(rs.getString(2));
					listePays.add(cat);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des pays "
																	+ "entre les deux PK");
			}
			return listePays;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	public void insertTabObj (Pays[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO pays VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdPays().intValue()==0){
						pst.setString(1, tabObj[i].getNom());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();
						tabObj[i].setIdPays(rsKey.getInt(1));
					}else{
						throw new SQLException ("Ce Pays a déjà été sauvegardé");
					}
				}
				conn.commit();
				conn.setAutoCommit(true);
			}catch (SQLException e){
				try {
					conn.rollback();
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				throw new MyException (e.getMessage());
			}
		}else{
			throw new MyException("Problème avec le tableau d'objets Pays");
		}
		
	}
	
	public void insertCollObj (Collection<Pays> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Pays[] tabPays = (Pays[]) collObj.toArray(new Pays[collObj.size()]);
		this.insertTabObj(tabPays);
		
	}
	
	public Integer insertPerso(Pays obj) throws MyException {
		// TODO Auto-generated method stub
		Integer valpk;
		if(obj.getIdPays().intValue()==0){
			
			String sql = "INSERT INTO pays VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getNom());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				valpk=rsKey.getInt(1);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le pays a déjà été sauvegardé");
		}
		return valpk;
		
	}
	
	public void updatePerso(Pays obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdPays().intValue() != 0){
			
			String sql = "UPDATE Pays SET nom = ? WHERE idPays = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setString(1, obj.getNom());
				pst.setInt(2, obj.getIdPays());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le pays n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("Le pays n'avait pas encore été sauvegardé. La modification "
					+ "d'un Pays, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	
	
	
	
	
	
}
