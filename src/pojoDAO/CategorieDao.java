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
import pojo.Categorie;

public  class CategorieDao extends Dao<Categorie, Integer> {

	public CategorieDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Categorie obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdCategorie().intValue()==0){
			
			String sql = "INSERT INTO categorie VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 repr�sente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getNomCategorie());
				pst.executeUpdate();
				// Pour �tre s�r que la PK soit bien d�finie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdCategorie(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("La categorie a d�j� �t� sauvegard�e");
		}
		
	}
	
	

	@Override
	public void update(Categorie obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdCategorie().intValue() != 0){
			
			String sql = "UPDATE categorie SET nomCategorie = ? WHERE idCategorie = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setString(1, obj.getNomCategorie());
				pst.setInt(2, obj.getIdCategorie());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La categorie n'a pas �t� modifi�e");
			}
			
			
		}else{
			throw new MyException ("La categorie n'avait pas encore �t� sauvegard�e.La modification "
					+ "d'une categorie, n'ayant pas encore �t� sauvegard�e, n'est pas autoris�e");
		}
	}

	@Override
	public void delete(Categorie obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdCategorie().intValue() != 0){
			
			String sql = "DELETE FROM categorie WHERE idCategorie = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdCategorie());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La categorie n'a pas �t� supprim�e");
			}
			
			
		}else{
			throw new MyException ("La categorie, dont vous demandez qu'elle soit supprim�e,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Categorie find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM categorie WHERE idCategorie = ?;";
			ResultSet rs = null;
			Categorie cat = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				cat = new Categorie();
				cat.setIdCategorie(rs.getInt(1));
				cat.setNomCategorie(rs.getString(2));
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La cat�gorie recherch�e n'a pas �t� trouv�e.");
			} 
			return cat;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Categorie> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM categorie;";
		ResultSet rs = null;
		List <Categorie> listeCategories = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeCategories = new ArrayList<Categorie>();
			Categorie cat = null;
			
			while (rs.next()){
				cat = new Categorie();
				cat.setIdCategorie(rs.getInt(1));
				cat.setNomCategorie(rs.getString(2));
				listeCategories.add(cat);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de r�cup�rer la liste des cat�gories");
		}		
		return listeCategories;
	}
		
	

	public List<Categorie> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM categorie WHERE idCategorie BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Categorie> listeCategories = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeCategories = new ArrayList<Categorie>();
				Categorie cat = null;
				
				while (rs.next()){
					cat=new Categorie();
					cat.setIdCategorie(rs.getInt(1));
					cat.setNomCategorie(rs.getString(2));
					listeCategories.add(cat);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de r�cup�rer la liste des cat�gories "
																	+ "entre les deux PK");
			}
			return listeCategories;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	
	
	
	public void insertTabObj (Categorie[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO categorie VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdCategorie().intValue()==0){
						pst.setString(1, tabObj[i].getNomCategorie());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();
						tabObj[i].setIdCategorie(rsKey.getInt(1));
					}else{
						throw new SQLException ("Cette categorie a d�j� �t� sauvegard�e");
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
			throw new MyException("Probl�me avec le tableau d'objets Cat�gorie");
		}
		
	}
	
	public void insertCollObj (Collection<Categorie> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Categorie[] tabCategories = (Categorie[]) collObj.toArray(new Categorie[collObj.size()]);
		this.insertTabObj(tabCategories);
		
	}
	
	
	
	
	
}
