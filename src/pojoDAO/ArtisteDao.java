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



public  class ArtisteDao extends Dao<Artiste, Integer> {

	public ArtisteDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Artiste obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdArtiste().intValue()==0){
			
			String sql = "INSERT INTO artiste VALUES (NULL,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getEmail());
				pst.setString(2, obj.getNom());
				pst.setString(3, obj.getPrenom());
				pst.setString(4, obj.getTelephone());
				pst.setInt(5, ((Adresse)((Artiste)obj).getAdresse()).getIdAdresse());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdArtiste(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("L'artiste a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(Artiste obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdArtiste().intValue() != 0){
			
			
			String sql = "UPDATE artiste SET email = ?, nom = ?, prenom = ?, telephone = ?, "
													+ "idAdresse = ?  WHERE idArtiste = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setString(1, obj.getEmail());
				pst.setString(2, obj.getNom());
				pst.setString(3, obj.getPrenom());
				pst.setString(4, obj.getTelephone());
				pst.setInt(5, ((Adresse)((Artiste)obj).getAdresse()).getIdAdresse());
				pst.setInt(6, obj.getIdArtiste());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'Artiste n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("L'Artiste n'avait pas encore été sauvegardé. La modification "
					+ "d'un Artiste, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Artiste obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdArtiste().intValue() != 0){

			String sql = "DELETE FROM artiste WHERE idArtiste = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdArtiste());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'artiste n'a pas été supprimé");
			}
				
		}else{
			throw new MyException ("L'artiste, dont vous demandez qu'il soit supprimé,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Artiste find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM artiste WHERE idArtiste = ?;";
			ResultSet rs = null;
			Artiste artiste = null;
			AdresseDao adresseDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				adresseDao = new AdresseDao(conn);
				artiste = new Artiste();
				
				artiste.setIdArtiste(rs.getInt(1));
				artiste.setEmail(rs.getString(2));
				artiste.setNom(rs.getString(3));
				artiste.setPrenom(rs.getString(4));
				artiste.setTelephone(rs.getString(5));
				artiste.setAdresse(adresseDao.find(rs.getInt(6)));
				
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'artiste recherché n'a pas été trouvé.");
			} 
			return artiste;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Artiste> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM artiste ORDER BY nom, prenom;";
		ResultSet rs = null;
		List <Artiste> listeArtiste = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeArtiste = new ArrayList<Artiste>();
			Artiste artiste = null;
			AdresseDao adresseDao = null;
			
			while (rs.next()){
				
				adresseDao = new AdresseDao(conn);
				artiste = new Artiste();
				
				artiste.setIdArtiste(rs.getInt(1));
				artiste.setEmail(rs.getString(2));
				artiste.setNom(rs.getString(3));
				artiste.setPrenom(rs.getString(4));
				artiste.setTelephone(rs.getString(5));
				artiste.setAdresse(adresseDao.find(rs.getInt(6)));
				listeArtiste.add(artiste);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des Artiste");
		}		
		return listeArtiste;
	}

	
	public List<Artiste> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM artiste WHERE idArtiste BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Artiste> listeArtistes = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeArtistes = new ArrayList<Artiste>();
				Artiste artiste = null;
				AdresseDao adresseDao = null;
				
				while (rs.next()){
					
					adresseDao = new AdresseDao(conn);
					artiste = new Artiste();
					
					artiste.setIdArtiste(rs.getInt(1));
					artiste.setEmail(rs.getString(2));
					artiste.setNom(rs.getString(3));
					artiste.setPrenom(rs.getString(4));
					artiste.setTelephone(rs.getString(5));
					artiste.setAdresse(adresseDao.find(rs.getInt(6)));
					listeArtistes.add(artiste);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des artistes "
																	+ "entre les deux PK");
			}
			return listeArtistes;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (Artiste[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO artiste VALUES (NULL,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdArtiste().intValue()==0){
						pst.setString(1, tabObj[i].getEmail());
						pst.setString(2, tabObj[i].getNom());
						pst.setString(3, tabObj[i].getPrenom());
						pst.setString(4, tabObj[i].getTelephone());
						pst.setInt(5, ((Adresse)((Artiste)tabObj[i]).getAdresse()).getIdAdresse());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();

					}else{
						throw new SQLException ("Cet artiste a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets Artiste");
		}
		
	}
	

	public void insertCollObj (Collection<Artiste> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Artiste[] tabArtistes = (Artiste[]) collObj.toArray(new Artiste[collObj.size()]);
		this.insertTabObj(tabArtistes);
		
	}
	
	public Integer insertPerso(Artiste obj) throws MyException {
		// TODO Auto-generated method stub
		Integer valpk=0;
		if(obj.getIdArtiste().intValue()==0){
			
			String sql = "INSERT INTO artiste VALUES (NULL,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getEmail());
				pst.setString(2, obj.getNom());
				pst.setString(3, obj.getPrenom());
				pst.setString(4, obj.getTelephone());
				pst.setInt(5, ((Adresse)((Artiste)obj).getAdresse()).getIdAdresse());
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
			throw new MyException ("L'artiste a déjà été sauvegardé");
		}
		
		return valpk;
	}
	
	public void updatePerso(Artiste obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdArtiste().intValue() != 0){
			
			
			String sql = "UPDATE artiste SET email = ?, nom = ?, prenom = ?, telephone = ?"
					+ " WHERE idArtiste = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setString(1, obj.getEmail());
				pst.setString(2, obj.getNom());
				pst.setString(3, obj.getPrenom());
				pst.setString(4, obj.getTelephone());
				pst.setInt(5, obj.getIdArtiste());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'Artiste n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("L'Artiste n'avait pas encore été sauvegardé. La modification "
					+ "d'un Artiste, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	
	
	
	
	
}
