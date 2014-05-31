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
import pojo.Localexpo;



public  class LocalexpoDao extends Dao<Localexpo, Integer> {

	public LocalexpoDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Localexpo obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdAdresseLocal()==0){
			
			String sql = "INSERT INTO localexpo VALUES (?,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setInt(1,((Adresse)((Localexpo)obj).getAdresse()).getIdAdresse());
				pst.setString(2, obj.getNomLocal());
				pst.setDouble(3, obj.getSuperficieExpo());
				pst.setString(4, obj.getTelephoneLocal());
				pst.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le local d'exposition a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(Localexpo obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdAdresseLocal() != 0){
			
			
			String sql = "UPDATE localexpo SET nomLocal = ?, superficieExpo = ?, "
											+ "telephoneLocal = ? WHERE idAdresseLocal = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setString(1, obj.getNomLocal());
				pst.setDouble(2, obj.getSuperficieExpo());
				pst.setString(3, obj.getTelephoneLocal());
				pst.setInt(4, obj.getIdAdresseLocal());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le local d'exposition n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("Le local d'exposition n'avait pas encore été sauvegardé. "
					+ "La modification d'un local d'exposition, n'ayant pas encore été sauvegardé, "
					+ "n'est pas autorisée");
		}
	}

	@Override
	public void delete(Localexpo obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdAdresseLocal() != 0){
			
			String sql = "DELETE FROM localexpo WHERE idAdresseLocal = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdAdresseLocal());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le local d'exposition n'a pas été supprimé");
			}
				
		}else{
			throw new MyException ("Le local d'exposition, dont vous demandez qu'il soit"
					+ "supprimé, n'existe pas");
		}
	}
	
	

	@Override
	public Localexpo find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM localexpo WHERE idAdresseLocal = ?;";
			ResultSet rs = null;
			Localexpo localexpo = null;
			AdresseDao adresseDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				adresseDao = new AdresseDao(conn);
				localexpo = new Localexpo();				
				
				localexpo.setIdAdresseLocal(rs.getInt(1));
				localexpo.setNomLocal(rs.getString(2));
				localexpo.setSuperficieExpo(rs.getDouble(3));
				localexpo.setTelephoneLocal(rs.getString(4));
				localexpo.setAdresse(adresseDao.find(rs.getInt(1)));
				
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le local d'exposition recherché n'a pas été trouvé.");
			} 
			return localexpo;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Localexpo> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM localexpo;";
		ResultSet rs = null;
		List <Localexpo> listeLocalexpo = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeLocalexpo = new ArrayList<Localexpo>();
			Localexpo localexpo = null;
			AdresseDao adresseDao = null;
			
			while (rs.next()){
				
				adresseDao = new AdresseDao(conn);
				localexpo = new Localexpo();
				
				localexpo.setIdAdresseLocal(rs.getInt(1));
				localexpo.setNomLocal(rs.getString(2));
				localexpo.setSuperficieExpo(rs.getDouble(3));
				localexpo.setTelephoneLocal(rs.getString(4));
				localexpo.setAdresse(adresseDao.find(rs.getInt(1)));
				listeLocalexpo.add(localexpo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des locaux d'exposition");
		}		
		return listeLocalexpo;
	}

	
	public List<Localexpo> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM localexpo WHERE idAdresseLocal BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Localexpo> listeLocalexpos = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeLocalexpos = new ArrayList<Localexpo>();
				Localexpo localexpo = null;
				AdresseDao adresseDao = null;
				
				while (rs.next()){
					
					adresseDao = new AdresseDao(conn);
					localexpo = new Localexpo();
					
					localexpo.setIdAdresseLocal(rs.getInt(1));
					localexpo.setNomLocal(rs.getString(2));
					localexpo.setSuperficieExpo(rs.getDouble(3));
					localexpo.setTelephoneLocal(rs.getString(4));
					localexpo.setAdresse(adresseDao.find(rs.getInt(1)));
					listeLocalexpos.add(localexpo);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des locaux d'exposition "
																	+ "entre les deux PK");
			}
			return listeLocalexpos;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (Localexpo[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO localexpo VALUES (?,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdAdresseLocal()==0){
						pst.setInt(1,((Adresse)((Localexpo)tabObj[i]).getAdresse()).getIdAdresse());
						pst.setString(2, tabObj[i].getNomLocal());
						pst.setDouble(3, tabObj[i].getSuperficieExpo());
						pst.setString(4, tabObj[i].getTelephoneLocal());
						pst.executeUpdate();
					}else{
						throw new SQLException ("Ce local d'exposition a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets de locaux d'exposition");
		}
		
	}
	

	public void insertCollObj (Collection<Localexpo> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Localexpo[] tabLocalexpos = (Localexpo[]) collObj.toArray(new Localexpo[collObj.size()]);
		this.insertTabObj(tabLocalexpos);
		
	}
	
	
	
	
	
	
	
}
