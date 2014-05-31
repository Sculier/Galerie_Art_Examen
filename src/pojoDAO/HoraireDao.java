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
import pojo.Artiste;
import pojo.Horaire;
import pojo.Localexpo;


public  class HoraireDao extends Dao<Horaire, Integer> {

	public HoraireDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Horaire obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdHoraire().intValue()==0){
			
			String sql = "INSERT INTO horaire VALUES (NULL,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getHeureFermeture());
				pst.setString(2, obj.getHeureOuverture());
				pst.setString(3, obj.getJour());
				pst.setInt(4, ((Localexpo)((Horaire)obj).getLocalexpo()).getIdAdresseLocal());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdHoraire(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("L'horaire a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(Horaire obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdHoraire().intValue() != 0){
			
			String sql = "UPDATE horaire SET HeureFermeture = ?, HeureOuverture = ?,"
					+ "jour = ?, idAdresseLocal = ? WHERE idHoraire = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setString(1, obj.getHeureFermeture());
				pst.setString(2, obj.getHeureOuverture());
				pst.setString(3, obj.getJour());
				pst.setInt(4, ((Localexpo)((Horaire)obj).getLocalexpo()).getIdAdresseLocal());
				pst.setInt(5, obj.getIdHoraire());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'horaire n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("L'horaire n'avait pas encore été sauvegardé. La modification "
					+ "d'un horaire, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Horaire obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdHoraire().intValue() != 0){
			
			String sql = "DELETE FROM horaire WHERE idHoraire = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdHoraire());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'horaire n'a pas été supprimé");
			}
			
			
		}else{
			throw new MyException ("L'horaire, dont vous demandez qu'il soit supprimé,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Horaire find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM horaire WHERE idHoraire = ?;";
			ResultSet rs = null;
			Horaire hor = null;
			LocalexpoDao localexpoDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				localexpoDao = new LocalexpoDao(conn);
				hor = new Horaire();
				
				hor.setIdHoraire(rs.getInt(1));
				hor.setHeureFermeture(rs.getString(2));
				hor.setHeureOuverture(rs.getString(3));
				hor.setJour(rs.getString(4));
				hor.setLocalexpo(localexpoDao.find(rs.getInt(5)));
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'horaire recherché n'a pas été trouvé.");
			} 
			return hor;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Horaire> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM horaire;";
		ResultSet rs = null;
		List <Horaire> listeHoraires = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeHoraires = new ArrayList<Horaire>();
			Horaire hor = null;
			LocalexpoDao localexpoDao = null;
			
			while (rs.next()){
				
				localexpoDao = new LocalexpoDao(conn);
				hor = new Horaire();
				
				hor.setIdHoraire(rs.getInt(1));
				hor.setHeureFermeture(rs.getString(2));
				hor.setHeureOuverture(rs.getString(3));
				hor.setJour(rs.getString(4));
				hor.setLocalexpo(localexpoDao.find(rs.getInt(5)));
				listeHoraires.add(hor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des Horaires");
		}		
		return listeHoraires;
	}


	public List<Horaire> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM horaire WHERE idHoraire BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Horaire> listeHoraires = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeHoraires = new ArrayList<Horaire>();
				Horaire hor = null;
				LocalexpoDao localexpoDao = null;
				
				while (rs.next()){
					
					localexpoDao = new LocalexpoDao(conn);
					hor=new Horaire();
					
					hor.setIdHoraire(rs.getInt(1));
					hor.setHeureFermeture(rs.getString(2));
					hor.setHeureOuverture(rs.getString(3));
					hor.setJour(rs.getString(4));
					hor.setLocalexpo(localexpoDao.find(rs.getInt(5)));
					listeHoraires.add(hor);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des horaires "
																	+ "entre les deux PK");
			}
			return listeHoraires;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	
	
	
	
	public void insertTabObj (Horaire[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO horaire VALUES (NULL,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdHoraire().intValue()==0){
						pst.setString(1, tabObj[i].getHeureFermeture());
						pst.setString(2, tabObj[i].getHeureOuverture());
						pst.setString(3, tabObj[i].getJour());
						pst.setInt(4, ((Localexpo)((Horaire)tabObj[i]).getLocalexpo()).getIdAdresseLocal());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();
						tabObj[i].setIdHoraire(rsKey.getInt(1));
					}else{
						throw new SQLException ("Cet horaire a déjà été sauvegardé");
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
			throw new MyException("Problème avec le tableau d'objets horaire");
		}
		
	}
	
	public void insertCollObj (Collection<Horaire> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Horaire[] tabHoraires = (Horaire[]) collObj.toArray(new Horaire[collObj.size()]);
		this.insertTabObj(tabHoraires);
		
	}
	
	
	
}
