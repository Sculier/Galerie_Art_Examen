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
import pojo.Transport;



public  class TransportDao extends Dao<Transport, Integer> {

	public TransportDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Transport obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdTransport().intValue()==0){
			
			String sql = "INSERT INTO transport VALUES (NULL,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				java.sql.Date datedep = new java.sql.Date(obj.getDateDepot().getTime());
				pst.setDate(1, datedep);
				java.sql.Date dateretour = new java.sql.Date(obj.getDateRetour().getTime());
				pst.setDate(2, dateretour);
				pst.setString(3, obj.getHeureDepot());
				pst.setString(4, obj.getHeureRetour());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdTransport(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le transport a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(Transport obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdTransport().intValue() != 0){
			
			String sql = "UPDATE transport SET dateDepot = ?, dateRetour = ?, "
									+ "heureDepot = ?, heureRetour = ? WHERE idTransport = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				java.sql.Date datedep = new java.sql.Date(obj.getDateDepot().getTime());
				pst.setDate(1, datedep);
				java.sql.Date dateretour = new java.sql.Date(obj.getDateRetour().getTime());
				pst.setDate(2, dateretour);
				pst.setString(3, obj.getHeureDepot());
				pst.setString(4, obj.getHeureRetour());
				pst.setInt(5, obj.getIdTransport());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le transport n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("Le transport n'avait pas encore été sauvegardé. La modification "
					+ "d'un Transport, n'ayant pas encore été sauvegardé, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Transport obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdTransport().intValue() != 0){
			
			String sql = "DELETE FROM transport WHERE idTransport = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdTransport());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le transport n'a pas été supprimé");
			}
			
			
		}else{
			throw new MyException ("Le transport, dont vous demandez qu'il soit supprimé,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Transport find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM transport WHERE idTransport = ?;";
			ResultSet rs = null;
			Transport transp = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				transp = new Transport();
				transp.setIdTransport(rs.getInt(1));
				transp.setDateDepot((Date)rs.getDate(2));
				transp.setDateRetour((Date)rs.getDate(3));
				transp.setHeureDepot(rs.getString(4));
				transp.setHeureRetour(rs.getString(5));
				
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le transport recherché n'a pas été trouvé.");
			} 
			return transp;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Transport> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM Transport;";
		ResultSet rs = null;
		List <Transport> listeTransports = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeTransports = new ArrayList<Transport>();
			Transport transp = null;
			
			while (rs.next()){
				transp = new Transport();
				transp.setIdTransport(rs.getInt(1));
				transp.setDateDepot((Date)rs.getDate(2));
				transp.setDateRetour((Date)rs.getDate(3));
				transp.setHeureDepot(rs.getString(4));
				transp.setHeureRetour(rs.getString(5));
				listeTransports.add(transp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des transports");
		}		
		return listeTransports;
	}


	public List<Transport> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM Transport WHERE idTransport BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Transport> listeTransports = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeTransports = new ArrayList<Transport>();
				Transport transp = null;
				
				while (rs.next()){
					transp=new Transport();
					transp.setIdTransport(rs.getInt(1));
					transp.setDateDepot((Date)rs.getDate(2));
					transp.setDateRetour((Date)rs.getDate(3));
					transp.setHeureDepot(rs.getString(4));
					transp.setHeureRetour(rs.getString(5));
					listeTransports.add(transp);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des transports "
																	+ "entre les deux PK");
			}
			return listeTransports;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	
	
	
	
	public void insertTabObj (Transport[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO Transport VALUES (NULL,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdTransport().intValue()==0){
						java.sql.Date datedep = new java.sql.Date(tabObj[i].getDateDepot().getTime());
						pst.setDate(1, datedep);
						java.sql.Date dateretour = new java.sql.Date(tabObj[i].getDateRetour().getTime());
						pst.setDate(2, dateretour);
						pst.setString(3, tabObj[i].getHeureDepot());
						pst.setString(4, tabObj[i].getHeureRetour());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();
						tabObj[i].setIdTransport(rsKey.getInt(1));
					}else{
						throw new SQLException ("Cet Transport a déjà été sauvegardé");
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
			throw new MyException("Problème avec le tableau d'objets Transport");
		}
		
	}
	
	public void insertCollObj (Collection<Transport> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Transport[] tabTransports = (Transport[]) collObj.toArray(new Transport[collObj.size()]);
		this.insertTabObj(tabTransports);
		
	}
	
	
	
}
