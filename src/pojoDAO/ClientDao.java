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
import pojo.Adresse;
import pojo.Client;



public  class ClientDao extends Dao<Client, Integer> {

	public ClientDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Client obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdClient().intValue()==0){
			
			String sql = "INSERT INTO client VALUES (NULL,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				
				pst.setByte(1, obj.getAccordNewsletter());
				java.sql.Date datecl = new java.sql.Date(obj.getDateClient().getTime());
				pst.setDate(2, datecl);
				pst.setString(3, obj.getEmail());
				pst.setString(4, obj.getNom());
				pst.setString(5, obj.getPrenom());
				pst.setInt(6, ((Adresse)((Client)obj).getAdresse()).getIdAdresse());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdClient(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le client a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(Client obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdClient().intValue() != 0){
			
			
			String sql = "UPDATE client SET accordNewsletter = ?, "
							+ "dateClient = ?, email = ?, nom = ?, prenom = ?, "
													+ "idAdresse = ?  WHERE idClient = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setByte(1, obj.getAccordNewsletter());
				java.sql.Date datecl = new java.sql.Date(obj.getDateClient().getTime());
				pst.setDate(2, datecl);
				pst.setString(3, obj.getEmail());
				pst.setString(4, obj.getNom());
				pst.setString(5, obj.getPrenom());
				pst.setInt(6, ((Adresse)((Client)obj).getAdresse()).getIdAdresse());
				pst.setInt(7, obj.getIdClient());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le client n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("Le client n'avait pas encore été sauvegardé. La modification "
					+ "d'un Client, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Client obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdClient().intValue() != 0){
			
			String sql = "DELETE FROM client WHERE idClient = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdClient());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le client n'a pas été supprimé");
			}
				
		}else{
			throw new MyException ("Le client, dont vous demandez qu'il soit supprimé,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Client find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM client WHERE idClient = ?;";
			ResultSet rs = null;
			Client client = null;
			AdresseDao adresseDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				adresseDao = new AdresseDao(conn);
				client = new Client();
				
				client.setIdClient(rs.getInt(1));
				client.setAccordNewsletter(rs.getByte(2));
				client.setDateClient((Date)rs.getDate(3));
				client.setEmail(rs.getString(4));
				client.setNom(rs.getString(5));
				client.setPrenom(rs.getString(6));
				client.setAdresse(adresseDao.find(rs.getInt(7)));
				
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le client recherché n'a pas été trouvé.");
			} 
			return client;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Client> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM Client;";
		ResultSet rs = null;
		List <Client> listeClients = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeClients = new ArrayList<Client>();
			Client client = null;
			AdresseDao adresseDao = null;
			
			while (rs.next()){
				
				adresseDao = new AdresseDao(conn);
				client = new Client();
				
				client.setIdClient(rs.getInt(1));
				client.setAccordNewsletter(rs.getByte(2));
				client.setDateClient((Date)rs.getDate(3));
				client.setEmail(rs.getString(4));
				client.setNom(rs.getString(5));
				client.setPrenom(rs.getString(6));
				client.setAdresse(adresseDao.find(rs.getInt(7)));
				listeClients.add(client);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des clients");
		}		
		return listeClients;
	}

	
	public List<Client> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM Client WHERE idClient BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Client> listeClients = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeClients = new ArrayList<Client>();
				Client client = null;
				AdresseDao adresseDao = null;
				
				while (rs.next()){
					
					adresseDao = new AdresseDao(conn);
					client = new Client();
					
					client.setIdClient(rs.getInt(1));
					client.setAccordNewsletter(rs.getByte(2));
					client.setDateClient((Date)rs.getDate(3));
					client.setEmail(rs.getString(4));
					client.setNom(rs.getString(5));
					client.setPrenom(rs.getString(6));
					client.setAdresse(adresseDao.find(rs.getInt(7)));
					listeClients.add(client);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des clients "
																	+ "entre les deux PK");
			}
			return listeClients;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (Client[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO Client VALUES (NULL,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdClient().intValue()==0){
						pst.setByte(1, tabObj[i].getAccordNewsletter());
						java.sql.Date datecl = new java.sql.Date
												(tabObj[i].getDateClient().getTime());
						pst.setDate(2, datecl);
						pst.setString(3, tabObj[i].getEmail());
						pst.setString(4, tabObj[i].getNom());
						pst.setString(5, tabObj[i].getPrenom());
						pst.setInt(6, ((Adresse)((Client)tabObj[i]).getAdresse()).getIdAdresse());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();

					}else{
						throw new SQLException ("Ce client a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets clients");
		}
		
	}
	

	public void insertCollObj (Collection<Client> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Client[] tabClients = (Client[]) collObj.toArray(new Client[collObj.size()]);
		this.insertTabObj(tabClients);
		
	}
	
	
	
	
	
	
	
}
