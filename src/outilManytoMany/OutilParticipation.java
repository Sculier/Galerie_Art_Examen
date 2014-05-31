package outilManytoMany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import outilDAO.MyException;
import pojo.Client;
import pojo.Exposition;
import pojoDAO.ClientDao;



public  class OutilParticipation {
	
	private Connection conn = null;
	
	public OutilParticipation(Connection conn) {
		this.conn =conn;
	}

	public void insert(Exposition expo, Client cl) throws MyException {
			
			String sql = "INSERT INTO participation VALUES (?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setInt(1,expo.getIdExposition());
				pst.setInt(2,cl.getIdClient());
				pst.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
						
	}
	
	
	public void insertTabObjcl (Exposition expo, Client[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO participation VALUES (?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					pst.setInt(1,expo.getIdExposition());
					pst.setInt(2,tabObj[i].getIdClient());
					pst.executeUpdate();
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
			throw new MyException("Problème avec le tableau d'objets participation");
		}
		
	}
	

	public void insertCollObjcl (Exposition expo, Collection<Client> collObj) 
																	throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Client[] tabClient = (Client[]) collObj.toArray(new Client[collObj.size()]);
		this.insertTabObjcl(expo,tabClient);
		
	}
	
	
	public void delete(Exposition expo) throws MyException {
			
			String sql = "DELETE FROM participation WHERE idExposition = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, expo.getIdExposition());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'objet participation n'a pas été supprimé");
			}
				
	}
	

	public void update(Exposition expo, Collection<Client> collObj) throws MyException {
		
		this.delete(expo);
		Client[] tabClient = (Client[]) collObj.toArray(new Client[collObj.size()]);
		this.insertTabObjcl(expo,tabClient);
		
	}

	
	public List<Client> find(Exposition expo) throws MyException {
		
		String sql = "SELECT idClient FROM participation WHERE idExposition = ?;";
		ResultSet rs = null;
		List <Client> listeClient = null;

		try (PreparedStatement pst = this.conn.prepareStatement(sql)){
			pst.setInt(1, expo.getIdExposition());
			rs = pst.executeQuery();
			
			
			listeClient = new ArrayList<Client>();
			Client cl = null;
			ClientDao clientDao = null;
			
			while (rs.next()){
				
				cl = new Client();	
				clientDao = new ClientDao(conn);
				
				cl = clientDao.find(rs.getInt(1));
				listeClient.add(cl);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des participations");
		}		
		return listeClient;

	}

	
	

	
}
