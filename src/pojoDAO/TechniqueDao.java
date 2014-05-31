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
import pojo.Technique;


public  class TechniqueDao extends Dao<Technique, Integer> {

	public TechniqueDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Technique obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdTechnique().intValue()==0){
			
			String sql = "INSERT INTO technique VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getNomTechnique());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdTechnique(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le technique a déjà été sauvegardée");
		}
		
	}
	
	

	@Override
	public void update(Technique obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdTechnique().intValue() != 0){
			
			String sql = "UPDATE technique SET nomTechnique = ? WHERE idTechnique = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setString(1, obj.getNomTechnique());
				pst.setInt(2, obj.getIdTechnique());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le technique n'a pas été modifiée");
			}
			
			
		}else{
			throw new MyException ("Le technique n'avait pas encore été sauvegardée. La modification "
					+ "d'une technique, n'ayant pas encore été sauvegardée, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Technique obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdTechnique().intValue() != 0){
			
			String sql = "DELETE FROM technique WHERE idTechnique = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdTechnique());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le technique n'a pas été supprimée");
			}
			
			
		}else{
			throw new MyException ("Le technique, dont vous demandez qu'elle soit supprimée,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Technique find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM technique WHERE idTechnique = ?;";
			ResultSet rs = null;
			Technique tech = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				tech = new Technique();
				tech.setIdTechnique(rs.getInt(1));
				tech.setNomTechnique(rs.getString(2));
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le technique recherchée n'a pas été trouvée.");
			} 
			return tech;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Technique> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM technique;";
		ResultSet rs = null;
		List <Technique> listeTechniques = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeTechniques = new ArrayList<Technique>();
			Technique tech = null;
			
			while (rs.next()){
				tech = new Technique();
				tech.setIdTechnique(rs.getInt(1));
				tech.setNomTechnique(rs.getString(2));
				listeTechniques.add(tech);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des techniques");
		}		
		return listeTechniques;
	}

	
	public List<Technique> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM technique WHERE idTechnique BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Technique> listeTechniques = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeTechniques = new ArrayList<Technique>();
				Technique cat = null;
				
				while (rs.next()){
					cat=new Technique();
					cat.setIdTechnique(rs.getInt(1));
					cat.setNomTechnique(rs.getString(2));
					listeTechniques.add(cat);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des techniques "
																	+ "entre les deux PK");
			}
			return listeTechniques;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	
	public void insertTabObj (Technique[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO technique VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdTechnique().intValue()==0){
						pst.setString(1, tabObj[i].getNomTechnique());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();
						tabObj[i].setIdTechnique(rsKey.getInt(1));
					}else{
						throw new SQLException ("Cette technique a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets Technique");
		}
		
	}
	
	public void insertCollObj (Collection<Technique> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Technique[] tabTechniques = (Technique[]) collObj.toArray(new Technique[collObj.size()]);
		this.insertTabObj(tabTechniques);
		
	}
	
	
	
	
	
}
