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
import pojo.Adresse;
import pojo.Pays;


public  class AdresseDao extends Dao<Adresse, Integer> {

	public AdresseDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Adresse obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdAdresse().intValue()==0){
			
			String sql = "INSERT INTO adresse VALUES (NULL,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getCodePostal());
				pst.setString(2, obj.getLocalite());
				pst.setString(3, obj.getNumero());
				pst.setString(4, obj.getRue());
				pst.setInt(5, ((Pays)((Adresse)obj).getPays()).getIdPays());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdAdresse(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("L'adresse a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(Adresse obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdAdresse().intValue() != 0){
			
			
			String sql = "UPDATE adresse SET codePostal = ?, localite = ?, numero = ?, rue = ?, "
													+ "idPays = ?  WHERE idAdresse = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setString(1, obj.getCodePostal());
				pst.setString(2, obj.getLocalite());
				pst.setString(3, obj.getNumero());
				pst.setString(4, obj.getRue());
				pst.setInt(5, ((Pays)((Adresse)obj).getPays()).getIdPays());
				pst.setInt(6, obj.getIdAdresse());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'adresse n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("L'adresse n'avait pas encore été sauvegardé. La modification "
					+ "d'une adresse, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Adresse obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdAdresse().intValue() != 0){
			
			String sql = "DELETE FROM adresse WHERE idAdresse = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdAdresse());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'adresse n'a pas été supprimé");
			}
				
		}else{
			throw new MyException ("L'adresse, dont vous demandez qu'il soit supprimé,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Adresse find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM adresse WHERE idAdresse = ?;";
			ResultSet rs = null;
			Adresse adresse = null;
			PaysDao paysDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				paysDao = new PaysDao(conn);
				adresse = new Adresse();
				
				adresse.setIdAdresse(rs.getInt(1));
				adresse.setCodePostal(rs.getString(2));
				adresse.setLocalite(rs.getString(3));
				adresse.setNumero(rs.getString(4));
				adresse.setRue(rs.getString(5));
				adresse.setPays(paysDao.find(rs.getInt(6)));
				
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'adresse recherché n'a pas été trouvé.");
			} 
			return adresse;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Adresse> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM Adresse;";
		ResultSet rs = null;
		List <Adresse> listeAdresse = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeAdresse = new ArrayList<Adresse>();
			Adresse adresse = null;
			PaysDao paysDao = null;
			
			while (rs.next()){
				
				paysDao = new PaysDao(conn);
				adresse = new Adresse();
				
				adresse.setIdAdresse(rs.getInt(1));
				adresse.setCodePostal(rs.getString(2));
				adresse.setLocalite(rs.getString(3));
				adresse.setNumero(rs.getString(4));
				adresse.setRue(rs.getString(5));
				adresse.setPays(paysDao.find(rs.getInt(6)));
				listeAdresse.add(adresse);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des Adresse");
		}		
		return listeAdresse;
	}

	
	public List<Adresse> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM adresse WHERE idAdresse BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Adresse> listeAdresses = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeAdresses = new ArrayList<Adresse>();
				Adresse adresse = null;
				PaysDao paysDao = null;
				
				while (rs.next()){
					
					paysDao = new PaysDao(conn);
					adresse = new Adresse();
					
					adresse.setIdAdresse(rs.getInt(1));
					adresse.setCodePostal(rs.getString(2));
					adresse.setLocalite(rs.getString(3));
					adresse.setNumero(rs.getString(4));
					adresse.setRue(rs.getString(5));
					adresse.setPays(paysDao.find(rs.getInt(6)));
					listeAdresses.add(adresse);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des adresses "
																	+ "entre les deux PK");
			}
			return listeAdresses;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (Adresse[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO adresse VALUES (NULL,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdAdresse().intValue()==0){
						pst.setString(1, tabObj[i].getCodePostal());
						pst.setString(2, tabObj[i].getLocalite());
						pst.setString(3, tabObj[i].getNumero());
						pst.setString(4, tabObj[i].getRue());
						pst.setInt(5, ((Pays)((Adresse)tabObj[i]).getPays()).getIdPays());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();

					}else{
						throw new SQLException ("Cette adresse a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets Adresse");
		}
		
	}
	

	public void insertCollObj (Collection<Adresse> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Adresse[] tabAdresses = (Adresse[]) collObj.toArray(new Adresse[collObj.size()]);
		this.insertTabObj(tabAdresses);
		
	}
	
	public Integer insertPerso(Adresse obj) throws MyException {
		// TODO Auto-generated method stub
		Integer valpk=0;
		if(obj.getIdAdresse().intValue()==0){
			
			String sql = "INSERT INTO adresse VALUES (NULL,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getCodePostal());
				pst.setString(2, obj.getLocalite());
				pst.setString(3, obj.getNumero());
				pst.setString(4, obj.getRue());
				pst.setInt(5, ((Pays)((Adresse)obj).getPays()).getIdPays());
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
			throw new MyException ("L'adresse a déjà été sauvegardé");
		}
		return valpk;
	}
	
	public void updatePerso(Adresse obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdAdresse().intValue() != 0){
			
			
			String sql = "UPDATE adresse SET codePostal = ?, localite = ?, numero = ?, rue = ? "
					+ "WHERE idAdresse = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setString(1, obj.getCodePostal());
				pst.setString(2, obj.getLocalite());
				pst.setString(3, obj.getNumero());
				pst.setString(4, obj.getRue());
				pst.setInt(5, obj.getIdAdresse());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'adresse n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("L'adresse n'avait pas encore été sauvegardé. La modification "
					+ "d'une adresse, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	
	
	
	
}
