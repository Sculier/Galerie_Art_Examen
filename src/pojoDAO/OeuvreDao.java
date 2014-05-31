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
import pojo.Oeuvre;
import pojo.Artiste;
import pojo.Categorie;
import pojo.Courant;
import pojo.Localexpo;
import pojo.Technique;


public  class OeuvreDao extends Dao<Oeuvre, Integer> {

	public OeuvreDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Oeuvre obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdOeuvre().intValue()==0){
			
			String sql = "INSERT INTO oeuvre VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getDescription());
				pst.setInt(2, obj.getHauteur());
				pst.setInt(3, obj.getLargeur());
				pst.setString(4, obj.getNomOeuvre());
				pst.setFloat(5, obj.getPourcentageComission());
				pst.setDouble(6, obj.getPrixAffiche());
				pst.setDouble(7, obj.getPrixNegocie());
				pst.setDouble(8, obj.getPrixPlancher());
				pst.setFloat(9, obj.getTauxTva());
				pst.setByte(10, obj.getVendu());
				pst.setInt(11,((Artiste)((Oeuvre)obj).getArtiste()).getIdArtiste());
				pst.setInt(12,((Categorie)((Oeuvre)obj).getCategorie()).getIdCategorie());
				pst.setInt(13,((Courant)((Oeuvre)obj).getCourant()).getIdCourant());
				pst.setInt(14,((Technique)((Oeuvre)obj).getTechnique()).getIdTechnique());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdOeuvre(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("L'euvre a déjà été sauvegardée");
		}
		
	}
	
	

	@Override
	public void update(Oeuvre obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdOeuvre().intValue() != 0){
			
			
			String sql = "UPDATE oeuvre SET description = ?, hauteur = ?, "
					+ "largeur = ?, nomOeuvre = ?, pourcentageComission = ?, prixAffiche = ?, "
							+ "prixNegocie = ?, prixPlancher = ?, tauxTva = ?, "
							+ "vendu = ?, idArtiste = ?, "
							+ "idCategorie = ?, idCourant = ?, idTechnique = ?"
							+ "  WHERE idOeuvre = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setString(1, obj.getDescription());
				pst.setInt(2, obj.getHauteur());
				pst.setInt(3, obj.getLargeur());
				pst.setString(4, obj.getNomOeuvre());
				pst.setFloat(5, obj.getPourcentageComission());
				pst.setDouble(6, obj.getPrixAffiche());
				pst.setDouble(7, obj.getPrixNegocie());
				pst.setDouble(8, obj.getPrixPlancher());
				pst.setFloat(9, obj.getTauxTva());
				pst.setByte(10, obj.getVendu());
				pst.setInt(11,((Artiste)((Oeuvre)obj).getArtiste()).getIdArtiste());
				pst.setInt(12,((Categorie)((Oeuvre)obj).getCategorie()).getIdCategorie());
				pst.setInt(13,((Courant)((Oeuvre)obj).getCourant()).getIdCourant());
				pst.setInt(14,((Technique)((Oeuvre)obj).getTechnique()).getIdTechnique());
				pst.setInt(15, obj.getIdOeuvre()); 
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'oeuvre n'a pas été modifiée");
			}
			
			
		}else{
			throw new MyException ("L'oeuvre n'avait pas encore été sauvegardée. La modification "
					+ "d'une oeuvre, n'ayant pas encore été sauvegardée, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Oeuvre obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdOeuvre().intValue() != 0){
			
			String sql = "DELETE FROM oeuvre WHERE idOeuvre = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdOeuvre());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'oeuvre n'a pas été supprimée");
			}
				
		}else{
			throw new MyException ("L'oeuvre, dont vous demandez qu'elle soit supprimée,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Oeuvre find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM oeuvre WHERE idOeuvre = ?;";
			ResultSet rs = null;
			Oeuvre oeuvre = null;
			ArtisteDao artisteDao = null;
			CategorieDao categorieDao = null;
			CourantDao courantDao = null;
			TechniqueDao techniqueDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				oeuvre = new Oeuvre();
				artisteDao = new ArtisteDao(conn);
				categorieDao = new CategorieDao(conn);
				courantDao = new CourantDao (conn);
				techniqueDao = new TechniqueDao(conn);			
				
				oeuvre.setIdOeuvre(rs.getInt(1));
				oeuvre.setDescription(rs.getString(2));
				oeuvre.setHauteur(rs.getInt(3));
				oeuvre.setLargeur(rs.getInt(4));
				oeuvre.setNomOeuvre(rs.getString(5));
				oeuvre.setPourcentageComission(rs.getFloat(6));
				oeuvre.setPrixAffiche(rs.getDouble(7));
				oeuvre.setPrixNegocie(rs.getDouble(8));
				oeuvre.setPrixPlancher(rs.getDouble(9));
				oeuvre.setTauxTva(rs.getFloat(10));
				oeuvre.setVendu(rs.getByte(11));
				oeuvre.setArtiste(artisteDao.find(rs.getInt(12)));
				oeuvre.setCategorie(categorieDao.find(rs.getInt(13)));
				oeuvre.setCourant(courantDao.find(rs.getInt(14)));	
				oeuvre.setTechnique(techniqueDao.find(rs.getInt(15)));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La Oeuvre recherchée n'a pas été trouvée.");
			} 
			return oeuvre;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Oeuvre> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM Oeuvre;";
		ResultSet rs = null;
		List <Oeuvre> listeOeuvre = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			
			listeOeuvre = new ArrayList<Oeuvre>();
			Oeuvre oeuvre = null;
			ArtisteDao artisteDao = null;
			CategorieDao categorieDao = null;
			CourantDao courantDao = null;
			TechniqueDao techniqueDao = null;
			
			while (rs.next()){
				
				oeuvre = new Oeuvre();
				artisteDao = new ArtisteDao(conn);
				categorieDao = new CategorieDao(conn);
				courantDao = new CourantDao (conn);
				techniqueDao = new TechniqueDao(conn);			
				
				oeuvre.setIdOeuvre(rs.getInt(1));
				oeuvre.setDescription(rs.getString(2));
				oeuvre.setHauteur(rs.getInt(3));
				oeuvre.setLargeur(rs.getInt(4));
				oeuvre.setNomOeuvre(rs.getString(5));
				oeuvre.setPourcentageComission(rs.getFloat(6));
				oeuvre.setPrixAffiche(rs.getDouble(7));
				oeuvre.setPrixNegocie(rs.getDouble(8));
				oeuvre.setPrixPlancher(rs.getDouble(9));
				oeuvre.setTauxTva(rs.getFloat(10));
				oeuvre.setVendu(rs.getByte(11));
				oeuvre.setArtiste(artisteDao.find(rs.getInt(12)));
				oeuvre.setCategorie(categorieDao.find(rs.getInt(13)));
				oeuvre.setCourant(courantDao.find(rs.getInt(14)));
				oeuvre.setTechnique(techniqueDao.find(rs.getInt(15)));
				listeOeuvre.add(oeuvre);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des Oeuvre");
		}		
		return listeOeuvre;
	}

	
	public List<Oeuvre> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM Oeuvre WHERE idOeuvre BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Oeuvre> listeOeuvres = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeOeuvres = new ArrayList<Oeuvre>();
				Oeuvre oeuvre = null;
				ArtisteDao artisteDao = null;
				CategorieDao categorieDao = null;
				CourantDao courantDao = null;
				TechniqueDao techniqueDao = null;
				
				while (rs.next()){
					
					oeuvre = new Oeuvre();
					artisteDao = new ArtisteDao(conn);
					categorieDao = new CategorieDao(conn);
					courantDao = new CourantDao (conn);
					techniqueDao = new TechniqueDao(conn);			
					
					oeuvre.setIdOeuvre(rs.getInt(1));
					oeuvre.setDescription(rs.getString(2));
					oeuvre.setHauteur(rs.getInt(3));
					oeuvre.setLargeur(rs.getInt(4));
					oeuvre.setNomOeuvre(rs.getString(5));
					oeuvre.setPourcentageComission(rs.getFloat(6));
					oeuvre.setPrixAffiche(rs.getDouble(7));
					oeuvre.setPrixNegocie(rs.getDouble(8));
					oeuvre.setPrixPlancher(rs.getDouble(9));
					oeuvre.setTauxTva(rs.getFloat(10));
					oeuvre.setVendu(rs.getByte(11));
					oeuvre.setArtiste(artisteDao.find(rs.getInt(12)));
					oeuvre.setCategorie(categorieDao.find(rs.getInt(13)));
					oeuvre.setCourant(courantDao.find(rs.getInt(14)));
					oeuvre.setTechnique(techniqueDao.find(rs.getInt(15)));
					listeOeuvres.add(oeuvre);
					
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des Oeuvres "
																	+ "entre les deux PK");
			}
			return listeOeuvres;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (Oeuvre[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO Oeuvre VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdOeuvre().intValue()==0){
						
						pst.setString(1, tabObj[i].getDescription());
						pst.setInt(2, tabObj[i].getHauteur());
						pst.setInt(3, tabObj[i].getLargeur());
						pst.setString(4, tabObj[i].getNomOeuvre());
						pst.setFloat(5, tabObj[i].getPourcentageComission());
						pst.setDouble(6, tabObj[i].getPrixAffiche());
						pst.setDouble(7, tabObj[i].getPrixNegocie());
						pst.setDouble(8, tabObj[i].getPrixPlancher());
						pst.setFloat(9, tabObj[i].getTauxTva());
						pst.setByte(10, tabObj[i].getVendu());
						pst.setInt(11,((Artiste)((Oeuvre)tabObj[i]).getArtiste()).getIdArtiste());
						pst.setInt(12,((Categorie)((Oeuvre)tabObj[i]).getCategorie()).getIdCategorie());
						pst.setInt(13,((Courant)((Oeuvre)tabObj[i]).getCourant()).getIdCourant());
						pst.setInt(14,((Technique)((Oeuvre)tabObj[i]).getTechnique()).getIdTechnique());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();

					}else{
						throw new SQLException ("Cet Oeuvre a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets Oeuvre");
		}
		
	}
	

	public void insertCollObj (Collection<Oeuvre> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Oeuvre[] tabOeuvres = (Oeuvre[]) collObj.toArray(new Oeuvre[collObj.size()]);
		this.insertTabObj(tabOeuvres);
		
	}
	
	
	
	
	
	
	
}
