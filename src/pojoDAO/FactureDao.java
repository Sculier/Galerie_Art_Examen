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
import pojo.Client;
import pojo.Facture;
import pojo.Artiste;


public  class FactureDao extends Dao<Facture, Integer> {

	public FactureDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Facture obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdFacture().intValue()==0){
			
			String sql = "INSERT INTO facture VALUES (NULL,?,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				java.sql.Date datefact = new java.sql.Date(obj.getDateVente().getTime());
				pst.setDate(1, datefact);
				pst.setDouble(2, obj.getMontantComission());
				pst.setDouble(3, obj.getMontantTotal());
				pst.setDouble(4, obj.getMontantTva());
				pst.setString(5, obj.getNumeroFacture());
				pst.setDouble(6, obj.getPrixHtva());
				pst.setInt(7,((Client)((Facture)obj).getClient()).getIdClient());
				pst.executeUpdate();
				// Pour être sûr que la PK soit bien définie
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdFacture(rsKey.getInt(1));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("La facture a déjà été sauvegardée");
		}
		
	}
	
	

	@Override
	public void update(Facture obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdFacture().intValue() != 0){
			
			
			String sql = "UPDATE facture SET dateVente = ?, montantComission = ?, montantTotal =?,"
					+ "montantTva = ?, numeroFacture = ?, prixHTva = ?, idClient = ?  "
					+ "WHERE idFacture = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				java.sql.Date datefact = new java.sql.Date(obj.getDateVente().getTime());
				pst.setDate(1, datefact);
				pst.setDouble(2, obj.getMontantComission());
				pst.setDouble(3, obj.getMontantTotal());
				pst.setDouble(4, obj.getMontantTva());
				pst.setString(5, obj.getNumeroFacture());
				pst.setDouble(6, obj.getPrixHtva());
				pst.setInt(7,((Client)((Facture)obj).getClient()).getIdClient());
				pst.setInt(8, obj.getIdFacture()); 
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La facture n'a pas été modifiée");
			}
			
			
		}else{
			throw new MyException ("La facture n'avait pas encore été sauvegardée. La modification "
					+ "d'une facture, n'ayant pas encore été sauvegardée, n'est pas autorisée");
		}
	}

	@Override
	public void delete(Facture obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdFacture().intValue() != 0){
			
			String sql = "DELETE FROM Facture WHERE idFacture = ?;";
			
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdFacture());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La facture n'a pas été supprimé");
			}
				
		}else{
			throw new MyException ("La facture, dont vous demandez qu'elle soit supprimée,"
					+ " n'existe pas");
		}
	}
	
	

	@Override
	public Facture find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM facture WHERE idFacture = ?;";
			ResultSet rs = null;
			Facture facture = null;
			ClientDao clientDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				clientDao = new ClientDao(conn);
				facture = new Facture();
				
				facture.setIdFacture(rs.getInt(1));
				facture.setDateVente(rs.getDate(2));
				facture.setMontantComission(rs.getDouble(3));
				facture.setMontantTotal(rs.getDouble(4));
				facture.setMontantTva(rs.getDouble(5));
				facture.setNumeroFacture(rs.getString(6));
				facture.setPrixHtva(rs.getDouble(7));
				facture.setClient(clientDao.find(rs.getInt(8)));
				
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("La facture recherchée n'a pas été trouvée.");
			} 
			return facture;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<Facture> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM facture;";
		ResultSet rs = null;
		List <Facture> listeFacture = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeFacture = new ArrayList<Facture>();
			Facture facture = null;
			ClientDao clientDao = null;
			
			while (rs.next()){
				
				clientDao = new ClientDao(conn);
				facture = new Facture();
				
				facture.setIdFacture(rs.getInt(1));
				facture.setDateVente(rs.getDate(2));
				facture.setMontantComission(rs.getDouble(3));
				facture.setMontantTotal(rs.getDouble(4));
				facture.setMontantTva(rs.getDouble(5));
				facture.setNumeroFacture(rs.getString(6));
				facture.setPrixHtva(rs.getDouble(7));
				facture.setClient(clientDao.find(rs.getInt(8)));
				listeFacture.add(facture);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des Facture");
		}		
		return listeFacture;
	}

	
	public List<Facture> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM Facture WHERE idFacture BETWEEN ? AND ?";
			ResultSet rs = null;
			List<Facture> listeFactures = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeFactures = new ArrayList<Facture>();
				Facture facture = null;
				ClientDao clientDao = null;
				
				
				while (rs.next()){
					
					clientDao = new ClientDao(conn);
					facture = new Facture();
					
					facture.setIdFacture(rs.getInt(1));
					facture.setDateVente(rs.getDate(2));
					facture.setMontantComission(rs.getDouble(3));
					facture.setMontantTotal(rs.getDouble(4));
					facture.setMontantTva(rs.getDouble(5));
					facture.setNumeroFacture(rs.getString(6));
					facture.setPrixHtva(rs.getDouble(7));
					facture.setClient(clientDao.find(rs.getInt(8)));
					listeFactures.add(facture);
					
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des Factures "
																	+ "entre les deux PK");
			}
			return listeFactures;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
public void insertTabObj (Facture[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO facture VALUES (NULL,?,?,?,?,?,?,?);";
			ResultSet rsKey = null;
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdFacture().intValue()==0){
						java.sql.Date datefact = new java.sql.Date(tabObj[i].getDateVente().getTime());
						pst.setDate(1, datefact);
						pst.setDouble(2, tabObj[i].getMontantComission());
						pst.setDouble(3, tabObj[i].getMontantTotal());
						pst.setDouble(4, tabObj[i].getMontantTva());
						pst.setString(5, tabObj[i].getNumeroFacture());
						pst.setDouble(6, tabObj[i].getPrixHtva());
						pst.setInt(7,((Client)((Facture)tabObj[i]).getClient()).getIdClient());
						pst.executeUpdate();
						rsKey=pst.getGeneratedKeys();
						rsKey.next();

					}else{
						throw new SQLException ("Cet Facture a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets Facture");
		}
		
	}
	


	

	public void insertCollObj (Collection<Facture> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Facture[] tabFactures = (Facture[]) collObj.toArray(new Facture[collObj.size()]);
		this.insertTabObj(tabFactures);
		
	}
	
	
	
	
	
	
	
}
