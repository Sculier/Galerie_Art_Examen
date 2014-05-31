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
import pojo.Courant;



public  class CourantDao extends Dao<Courant, Integer> {

	public CourantDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Courant obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdCourant().intValue()==0){
			
			String sql = "INSERT INTO courant VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getNomCourant());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdCourant(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le courant a déjà été sauvegardée");
		}
		
	}
	
	

	@Override
	public void update(Courant obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdCourant().intValue() != 0){
			
			String sql = "UPDATE courant SET nomCourant = ? WHERE idCourant = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setString(1, obj.getNomCourant());
				pst.setInt(2, obj.getIdCourant());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le courant n'a pas été modifiée");
			}
			
			
		}else{
			throw new MyException ("Le courant n'avait pas encore été sauvegardé. La modification "
					+ "d'un courant, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Courant obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdCourant().intValue() != 0){
			
			String sql = "DELETE FROM courant WHERE idCourant = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdCourant());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le courant n'a pas été supprimée");
			}
			
			
		}else{
			throw new MyException ("Le courant, dont vous demandez qu'il soit supprimé,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Courant find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM courant WHERE idCourant = ?;";
			ResultSet rs = null;
			Courant cour = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				cour = new Courant();
				cour.setIdCourant(rs.getInt(1));
				cour.setNomCourant(rs.getString(2));
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le courant recherchée n'a pas été trouvée.");
			} 
			return cour;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Courant> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM courant;";
		ResultSet rs = null;
		List <Courant> listeCourants = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeCourants = new ArrayList<Courant>();
			Courant cour = null;
			
			while (rs.next()){
				cour = new Courant();
				cour.setIdCourant(rs.getInt(1));
				cour.setNomCourant(rs.getString(2));
				listeCourants.add(cour);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des courants");
		}		
		return listeCourants;
	}


	public List<Courant> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM courant WHERE idCourant BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Courant> listeCourants = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeCourants = new ArrayList<Courant>();
				Courant cour = null;
				
				while (rs.next()){
					cour=new Courant();
					cour.setIdCourant(rs.getInt(1));
					cour.setNomCourant(rs.getString(2));
					listeCourants.add(cour);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des courants "
																	+ "entre les deux PK");
			}
			return listeCourants;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	
	
	
	
	public void insertTabObj (Courant[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO courant VALUES (NULL,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdCourant().intValue()==0){
						pst.setString(1, tabObj[i].getNomCourant());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();
						tabObj[i].setIdCourant(rsKey.getInt(1));
					}else{
						throw new SQLException ("Ce courant a déjà été sauvegardé");
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
			throw new MyException("Problème avec le tableau d'objets Courant");
		}
		
	}
	
	public void insertCollObj (Collection<Courant> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Courant[] tabCourants = (Courant[]) collObj.toArray(new Courant[collObj.size()]);
		this.insertTabObj(tabCourants);
		
	}
	
	
	
}
