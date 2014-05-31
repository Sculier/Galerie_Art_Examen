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
import pojo.DetailFacture;
import pojo.Oeuvre;
import pojo.Facture;
import pojo.Artiste;



public  class DetailFactureDao extends Dao<DetailFacture, Integer> {

	public DetailFactureDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(DetailFacture obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdDetail()==0){
			
			String sql = "INSERT INTO detailFacture VALUES (?,?,?,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setInt(1,((Oeuvre)((DetailFacture)obj).getOeuvre()).getIdOeuvre());
				java.sql.Date datedetail = new java.sql.Date(obj.getDateDetail().getTime());
				pst.setDate(2, datedetail);
				pst.setDouble(3, obj.getMontantComissionDetail());
				pst.setDouble(4, obj.getMontantTvaDetail());
				pst.setDouble(5, obj.getPrixHtvaDetail());
				pst.setInt(6, ((Facture)((DetailFacture)obj).getFacture()).getIdFacture());
				
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
					
		}else{
			throw new MyException ("Le DetailFacture a déjà été sauvegardé");
		}
		
	}
	
	

	@Override
	public void update(DetailFacture obj) throws MyException {
		// TODO Auto-generated method stub
		
		if(obj.getIdDetail() != 0){
			
			
			String sql = "UPDATE detailFacture SET dateDetail = ?, "
							+ "montantComissionDetail = ?, montantTvaDetail = ?, "
										+ " prixHTvaDetail = ?, idFacture = ? "
														+ "WHERE idDetail = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				java.sql.Date datedetail = new java.sql.Date(obj.getDateDetail().getTime());
				pst.setDate(1, datedetail);
				pst.setDouble(2, obj.getMontantComissionDetail());
				pst.setDouble(3, obj.getMontantTvaDetail());
				pst.setDouble(4, obj.getPrixHtvaDetail());
				pst.setInt(5, ((Facture)((DetailFacture)obj).getFacture()).getIdFacture());
				pst.setInt(6, obj.getIdDetail());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le DetailFacture n'a pas été modifié");
			}
			
			
		}else{
			throw new MyException ("Le DetailFacture n'avait pas encore été sauvegardé. "
					+ "La modification d'un DetailFacture, n'ayant pas encore été sauvegardé, "
					+ "n'est pas autorisée");
		}
	}

	@Override
	public void delete(DetailFacture obj) throws MyException {
		// TODO Auto-generated method stub
		if(obj.getIdDetail() != 0){
			
			String sql = "DELETE FROM detailFacture WHERE idDetail = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, obj.getIdDetail());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le DetailFacture n'a pas été supprimé");
			}
				
		}else{
			throw new MyException ("Le DetailFacture, dont vous demandez qu'il soit"
															+ "supprimé, n'existe pas");
		}
	}
	
	

	@Override
	public DetailFacture find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		
		if(primaryKey.intValue()> 0){
			
			String sql = "SELECT * FROM detailFacture WHERE idDetail = ?;";
			ResultSet rs = null;
			DetailFacture detailFacture = null;
			FactureDao factureDao = null;
			OeuvreDao oeuvreDao = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();
				rs.next();
				
				detailFacture = new DetailFacture();
				factureDao = new FactureDao(conn);
				oeuvreDao = new OeuvreDao(conn);
				
				detailFacture.setIdDetail(rs.getInt(1));
				detailFacture.setOeuvre(oeuvreDao.find(rs.getInt(1)));
				detailFacture.setDateDetail((Date)rs.getDate(2));
				detailFacture.setMontantComissionDetail(rs.getDouble(3));
				detailFacture.setMontantTvaDetail(rs.getDouble(4));
				detailFacture.setPrixHtvaDetail(rs.getDouble(5));
				detailFacture.setFacture(factureDao.find(rs.getInt(6)));
						
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("Le DetailFacture recherché n'a pas été trouvé.");
			} 
			return detailFacture;
		
		} else{
				throw new MyException ("La PK est incorrecte. La recherche est donc impossible");
		}
		
	}

	
	@Override
	public List<DetailFacture> findAll() throws MyException {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM detailFacture;";
		ResultSet rs = null;
		List <DetailFacture> listeDetailFacture = null;
		
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);
			listeDetailFacture = new ArrayList<DetailFacture>();
			DetailFacture detailFacture = null;
			FactureDao factureDao = null;
			OeuvreDao oeuvreDao = null;
			
			while (rs.next()){
				
				detailFacture = new DetailFacture();	
				factureDao = new FactureDao(conn);
				oeuvreDao = new OeuvreDao(conn);
				
				detailFacture.setIdDetail(rs.getInt(1));
				detailFacture.setOeuvre(oeuvreDao.find(rs.getInt(1)));
				detailFacture.setDateDetail((Date)rs.getDate(2));
				detailFacture.setMontantComissionDetail(rs.getDouble(3));
				detailFacture.setMontantTvaDetail(rs.getDouble(4));
				detailFacture.setPrixHtvaDetail(rs.getDouble(5));
				detailFacture.setFacture(factureDao.find(rs.getInt(6)));
				listeDetailFacture.add(detailFacture);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des DetailFactures");
		}		
		return listeDetailFacture;
	}

	
	public List<DetailFacture> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		
		if (a.intValue()>0 && b.intValue()>0 && b.intValue()>a.intValue()){
			

			String sql = "SELECT * FROM DetailFacture WHERE IdDetail BETWEEN ? AND ?";
			ResultSet rs = null;
			List<DetailFacture> listeDetailFactures = null;
			
		    try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,a);
				pst.setInt(2,b);
				rs = pst.executeQuery();
				
				listeDetailFactures = new ArrayList<DetailFacture>();
				DetailFacture detailFacture = null;
				FactureDao factureDao = null;
				OeuvreDao oeuvreDao = null;
				
				while (rs.next()){
					
					detailFacture = new DetailFacture();	
					factureDao = new FactureDao(conn);
					oeuvreDao = new OeuvreDao(conn);
					
					detailFacture.setIdDetail(rs.getInt(1));
					detailFacture.setOeuvre(oeuvreDao.find(rs.getInt(1)));
					detailFacture.setDateDetail((Date)rs.getDate(2));
					detailFacture.setMontantComissionDetail(rs.getDouble(3));
					detailFacture.setMontantTvaDetail(rs.getDouble(4));
					detailFacture.setPrixHtvaDetail(rs.getDouble(5));
					detailFacture.setFacture(factureDao.find(rs.getInt(6)));
					listeDetailFactures.add(detailFacture);
				}	
				
			}catch (SQLException e){
				throw new MyException("Impossible de récupérer la liste des DetailFactures "
																	+ "entre les deux PK");
			}
			return listeDetailFactures;	
			
		}else{
			throw new MyException ("La valeur des deux PK sont incorrectes: arguments incorrect");
		}	
		
	}
	
	

	public void insertTabObj (DetailFacture[] tabObj) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO DetailFacture VALUES (?,?,?,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					if(tabObj[i].getIdDetail()==0){
						pst.setInt(1,((Oeuvre)((DetailFacture)tabObj[i]).getOeuvre()).getIdOeuvre());
						java.sql.Date datedetail = new java.sql.Date(tabObj[i].getDateDetail().getTime());
						pst.setDate(2, datedetail);
						pst.setDouble(3, tabObj[i].getMontantComissionDetail());
						pst.setDouble(4, tabObj[i].getMontantTvaDetail());
						pst.setDouble(5, tabObj[i].getPrixHtvaDetail());
						pst.setInt(6, ((Facture)((DetailFacture)tabObj[i]).getFacture()).getIdFacture());
						pst.executeUpdate();
		
					}else{
						throw new SQLException ("Cette DetailFacture a déjà été sauvegardée");
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
			throw new MyException("Problème avec le tableau d'objets de DetailFactures");
		}
		
	}
	

	public void insertCollObj (Collection<DetailFacture> collObj) throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		DetailFacture[] tabDetailFactures = (DetailFacture[]) collObj.toArray(new DetailFacture[collObj.size()]);
		this.insertTabObj(tabDetailFactures);
		
	}
	
	
	
	
	
	
	
}
