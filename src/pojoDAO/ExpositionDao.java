package pojoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import outilDAO.Dao;
import outilDAO.MyException;
import pojo.Exposition;



public  class ExpositionDao extends Dao<Exposition, Integer> {

	public ExpositionDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Exposition obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdExposition().intValue()==0){
			
			String sql = "INSERT INTO exposition VALUES (NULL,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				java.sql.Date dateExpoDeb = new java.sql.Date(obj.getDateDebut().getTime());
				pst.setDate(1, dateExpoDeb);
				java.sql.Date dateExpoFin = new java.sql.Date(obj.getDateFin().getTime());
				pst.setDate(2, dateExpoFin);
				java.sql.Date dateVernissage = new java.sql.Date(obj.getDateVernissage().getTime());
				pst.setDate(3, dateVernissage);
				pst.setString(4, obj.getHeureDebutVernissage());
				pst.setString(5, obj.getNomExposition());
				pst.setString(6, obj.getThemeExpo());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdExposition(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("L'exposition a déjà été sauvegardée");
		}
		
	}
	
	

	@Override
	public void update(Exposition obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdExposition().intValue() != 0){
			
			
			String sql = "UPDATE exposition SET dateDebut = ?, dateFin = ?, dateVernissage = ?, "
					+ "heureDebutVernissage = ?, nomExposition = ? , themeExpo = ?"
					+ " WHERE idExposition = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				java.sql.Date dateExpoDeb = new java.sql.Date(obj.getDateDebut().getTime());
				pst.setDate(1, dateExpoDeb);
				java.sql.Date dateExpoFin = new java.sql.Date(obj.getDateFin().getTime());
				pst.setDate(2, dateExpoFin);
				java.sql.Date dateVernissage = new java.sql.Date(obj.getDateVernissage().getTime());
				pst.setDate(3, dateVernissage);
				pst.setString(4, obj.getHeureDebutVernissage());
				pst.setString(5, obj.getNomExposition());
				pst.setString(6, obj.getThemeExpo());
				pst.setInt(7, obj.getIdExposition());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'exposition n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("L'exposition n'avait pas encore été sauvegardée. La modification "
					+ "d'une exposition, n'ayant pas encore été sauvegardée, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Exposition obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdExposition().intValue() != 0){
			
			String sql = "DELETE FROM exposition WHERE idExposition = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdExposition());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'exposition n'a pas été supprimée");
			}
				
		}else{
			throw new MyException ("L'exposition, dont vous demandez qu'elle soit supprimée,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Exposition find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM exposition WHERE idExposition = ?;";
			ResultSet rs = null;
			Exposition exposition = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				exposition = new Exposition();
				
				exposition.setIdExposition(rs.getInt(1));
				exposition.setDateDebut((Date)rs.getDate(2));
				exposition.setDateFin((Date)rs.getDate(3));
				exposition.setDateVernissage((Date)rs.getDate(4));
				exposition.setHeureDebutVernissage(rs.getString(5));
				exposition.setNomExposition(rs.getString(6));
				exposition.setThemeExpo(rs.getString(7));				
				
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'exposition recherchée n'a pas été trouvée.");
			} 
			return exposition;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Exposition> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM exposition;";
		ResultSet rs = null;
		List <Exposition> listeExposition = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeExposition = new ArrayList<Exposition>();
			Exposition exposition = null;
			
			while (rs.next()){
				
				exposition = new Exposition();
				
				exposition.setIdExposition(rs.getInt(1));
				exposition.setDateDebut((Date)rs.getDate(2));
				exposition.setDateFin((Date)rs.getDate(3));
				exposition.setDateVernissage((Date)rs.getDate(4));
				exposition.setHeureDebutVernissage(rs.getString(5));
				exposition.setNomExposition(rs.getString(6));
				exposition.setThemeExpo(rs.getString(7));				
				listeExposition.add(exposition);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des expositions");
		}		
		return listeExposition;
	}

	
	public List<Exposition> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM exposition WHERE idExposition BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Exposition> listeExpositions = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeExpositions = new ArrayList<Exposition>();
				Exposition exposition = null;
				
				while (rs.next()){
					
					exposition = new Exposition();
					
					exposition.setIdExposition(rs.getInt(1));
					exposition.setDateDebut((Date)rs.getDate(2));
					exposition.setDateFin((Date)rs.getDate(3));
					exposition.setDateVernissage((Date)rs.getDate(4));
					exposition.setHeureDebutVernissage(rs.getString(5));
					exposition.setNomExposition(rs.getString(6));
					exposition.setThemeExpo(rs.getString(7));				
					listeExpositions.add(exposition);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des expositions "
																	+ "entre les deux PK");
			}
			return listeExpositions;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (Exposition[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO exposition VALUES (NULL,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdExposition().intValue()==0){
						java.sql.Date dateExpoDeb = new java.sql.Date(tabObj[i].getDateDebut().getTime());
						pst.setDate(1, dateExpoDeb);
						java.sql.Date dateExpoFin = new java.sql.Date(tabObj[i].getDateFin().getTime());
						pst.setDate(2, dateExpoFin);
						java.sql.Date dateVernissage = new java.sql.Date(tabObj[i].getDateVernissage().getTime());
						pst.setDate(3, dateVernissage);
						pst.setString(4, tabObj[i].getHeureDebutVernissage());
						pst.setString(5, tabObj[i].getNomExposition());
						pst.setString(6, tabObj[i].getThemeExpo());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();

					}else{
						throw new SQLException ("Cette Exposition a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets Exposition");
		}
		
	}
	

	public void insertCollObj (Collection<Exposition> collObj) throws MyException{
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Exposition[] tabExpositions = (Exposition[]) collObj.toArray(new Exposition[collObj.size()]);
		this.insertTabObj(tabExpositions);
		
	}
	
	
	
	
	
	
	
}
