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
import pojo.Newsletter;
import pojoDAO.ClientDao;



public  class OutilReception {
	
	private Connection conn = null;
	
	public OutilReception(Connection conn) {
		this.conn =conn;
	}

	public void insert(Newsletter news, Client cl) throws MyException {
			
			String sql = "INSERT INTO reception VALUES (?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setInt(1,news.getIdNewsletter());
				pst.setInt(2,cl.getIdClient());
				pst.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
						
	}
	
	
	public void insertTabObjcl (Newsletter news, Client[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO reception VALUES (?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					pst.setInt(1,news.getIdNewsletter());
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
			throw new MyException("Problème avec le tableau d'objets reception");
		}
		
	}
	

	public void insertCollObjcl (Newsletter news, Collection<Client> collObj) 
																	throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Client[] tabClient = (Client[]) collObj.toArray(new Client[collObj.size()]);
		this.insertTabObjcl(news,tabClient);
		
	}
	
	
	public void delete(Newsletter news) throws MyException {
			
			String sql = "DELETE FROM reception WHERE IdNewsletter = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, news.getIdNewsletter());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'objet reception n'a pas été supprimé");
			}
				
	}
	

	public void update(Newsletter news, Collection<Client> collObj) throws MyException {
		
		this.delete(news);
		Client[] tabClient = (Client[]) collObj.toArray(new Client[collObj.size()]);
		this.insertTabObjcl(news,tabClient);
		
	}

	
	public List<Client> find(Newsletter news) throws MyException {
		
		String sql = "SELECT idClient FROM reception WHERE IdNewsletter = ?;";
		ResultSet rs = null;
		List <Client> listeClient = null;

		try (PreparedStatement pst = this.conn.prepareStatement(sql)){
			pst.setInt(1, news.getIdNewsletter());
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
			throw new MyException("Impossible de récupérer la liste des receptions");
		}		
		return listeClient;

	}

	
	

	
}
