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
import pojo.Newsletter;
import pojo.Exposition;



public  class NewsletterDao extends Dao<Newsletter, Integer> {

	public NewsletterDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Newsletter obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdNewsletter().intValue()==0){
			
			String sql = "INSERT INTO newsletter VALUES (NULL,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, obj.getContenu());
				java.sql.Date dateEnv = new java.sql.Date(obj.getDateEnvoi().getTime());
				pst.setDate(2, dateEnv);
				pst.setString(3, obj.getSujet());
				pst.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("La newsletter a déjà été sauvegardée");
		}
		
	}
	
	

	@Override
	public void update(Newsletter obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdNewsletter().intValue() != 0){
			
			
			String sql = "UPDATE newsletter SET contenu = ?, "
											+ "dateEnvoi = ?, sujet = ? WHERE idNewsletter = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				
				
				pst.setString(1, obj.getContenu());
				java.sql.Date dateEnv = new java.sql.Date(obj.getDateEnvoi().getTime());
				pst.setDate(2, dateEnv);
				pst.setString(3, obj.getSujet());
				pst.setInt(4,obj.getIdNewsletter());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La newsletter n'a pas été modifiée");
			}
			
			
		}else{
			throw new MyException ("La newsletter n'avait pas encore été sauvegardée. "
					+ "La modification d'une newsletter, n'ayant pas encore été sauvegardée, "
					+ "n'est pas autorisée");
		}
	}

	@Override
	public void delete(Newsletter obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdNewsletter().intValue() != 0){
			
			String sql = "DELETE FROM newsletter WHERE idNewsletter = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdNewsletter());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La newsletter n'a pas été supprimée");
			}
				
		}else{
			throw new MyException ("La newsletter, dont vous demandez qu'elle soit"
															+ "supprimée, n'existe pas");
		}
	}
	
	

	@Override
	public Newsletter find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM newsletter WHERE idNewsletter = ?;";
			ResultSet rs = null;
			Newsletter newsletter = null;

			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				newsletter = new Newsletter();
				
				newsletter.setIdNewsletter(rs.getInt(1));
				newsletter.setContenu(rs.getString(2));
				newsletter.setDateEnvoi((Date)rs.getDate(3));
				newsletter.setSujet(rs.getString(4));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La newsletter recherché n'a pas été trouvé.");
			} 
			return newsletter;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Newsletter> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM newsletter;";
		ResultSet rs = null;
		List <Newsletter> listeNewsletter = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeNewsletter = new ArrayList<Newsletter>();
			Newsletter newsletter = null;
			
			while (rs.next()){
				
				newsletter = new Newsletter();	
				
				newsletter.setIdNewsletter(rs.getInt(1));
				newsletter.setContenu(rs.getString(2));
				newsletter.setDateEnvoi((Date)rs.getDate(3));
				newsletter.setSujet(rs.getString(4));
				listeNewsletter.add(newsletter);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des newsletters");
		}		
		return listeNewsletter;
	}

	
	public List<Newsletter> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM newsletter WHERE idNewsletter BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Newsletter> listeNewsletters = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeNewsletters = new ArrayList<Newsletter>();
				Newsletter newsletter = null;
				
				while (rs.next()){
					
					newsletter = new Newsletter();
					
					newsletter = new Newsletter();				
					newsletter.setIdNewsletter(rs.getInt(1));
					newsletter.setContenu(rs.getString(2));
					newsletter.setDateEnvoi((Date)rs.getDate(3));
					newsletter.setSujet(rs.getString(4));
					listeNewsletters.add(newsletter);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des newsletters "
																	+ "entre les deux PK");
			}
			return listeNewsletters;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (Newsletter[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO newsletter VALUES (NULL,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdNewsletter().intValue()==0){
						pst.setString(1, tabObj[i].getContenu());
						java.sql.Date dateEnv = new java.sql.Date(tabObj[i].getDateEnvoi().getTime());
						pst.setDate(2, dateEnv);
						pst.setString(3, tabObj[i].getSujet());
						pst.executeUpdate();
		
					}else{
						throw new SQLException ("Cette newsletter a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets de newsletters");
		}
		
	}
	

	public void insertCollObj (Collection<Newsletter> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Newsletter[] tabNewsletters = (Newsletter[]) collObj.toArray(new Newsletter[collObj.size()]);
		this.insertTabObj(tabNewsletters);
		
	}
	
	
	
	
	
	
	
}
