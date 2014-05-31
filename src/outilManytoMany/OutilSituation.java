package outilManytoMany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import outilDAO.MyException;
import pojo.Localexpo;
import pojo.Newsletter;
import pojo.Oeuvre;
import pojo.Exposition;
import pojo.Situation;
import pojo.Transport;
import pojoDAO.OeuvreDao;



public  class OutilSituation {
	
	private Connection conn = null;
	
	public OutilSituation(Connection conn) {
		this.conn =conn;
	}

	public void insert(String typeExpo, Transport transp, Oeuvre oeuv, Localexpo loc,
			Exposition expo, Newsletter newsl) throws MyException {
			
			String sql = "INSERT INTO situation VALUES (?,?,?,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				// ci-dessous, le 1 représente le point d'interrogation du preparedStatement
				pst.setString(1, typeExpo);
				pst.setInt(2, transp.getIdTransport());
				pst.setInt(3, oeuv.getIdOeuvre());
				pst.setInt(4, loc.getIdAdresseLocal());
				pst.setInt(5, expo.getIdExposition());
				pst.setInt(6, newsl.getIdNewsletter());
				pst.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException(e.getMessage());
			} 
						
	}

	
	public void insertTabObjoeuv (String typeExpo, Transport transp, Oeuvre tabObj[], Localexpo loc,
			Exposition expo, Newsletter newsl) throws MyException {
		
		if(tabObj.length>0){
			String sql = "INSERT INTO situation VALUES (?,?,?,?,?,?);";
			
			try (PreparedStatement pst = 
					this.conn.prepareStatement(sql)){
				
				conn.setAutoCommit(false);
				for (int i=0; i<tabObj.length; i++){
					pst.setString(1, typeExpo);
					pst.setInt(2, transp.getIdTransport());
					pst.setInt(3, tabObj[i].getIdOeuvre());
					pst.setInt(4, loc.getIdAdresseLocal());
					pst.setInt(5, expo.getIdExposition());
					pst.setInt(6, newsl.getIdNewsletter());
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
			throw new MyException("Problème avec le tableau d'objets Situation");
		}
		
	}
	

	public void insertCollObjoeuv (String typeExpo, Transport transp, Collection<Oeuvre> collObj, Localexpo loc,
			Exposition expo, Newsletter newsl) 
																	throws MyException{
		
		// Transformer la Collection en tableau d'objets par la fonction toArray 
		Oeuvre[] tabOeuvre = (Oeuvre[]) collObj.toArray(new Oeuvre[collObj.size()]);
		this.insertTabObjoeuv(typeExpo, transp, tabOeuvre, loc, expo, newsl);
		
	}
	
	
	public void delete(Exposition expo) throws MyException {
			
			String sql = "DELETE FROM situation WHERE idExposition = ?;";
			
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1, expo.getIdExposition());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new MyException("L'objet Regrouper n'a pas été supprimé");
			}
				
	}
	

	public void update(String typeExpo, Transport transp, Collection<Oeuvre> collObj, Localexpo loc,
			Exposition expo, Newsletter newsl) throws MyException {
		
		this.delete(expo);
		Oeuvre[] tabOeuvre = (Oeuvre[]) collObj.toArray(new Oeuvre[collObj.size()]);
		this.insertTabObjoeuv(typeExpo, transp, tabOeuvre, loc, expo, newsl);
		
	}
	

	
	public List<Oeuvre> find(Exposition expo) throws MyException {
		
		String sql = "SELECT idOeuvre FROM situation WHERE idExposition = ?;";
		ResultSet rs = null;
		List <Oeuvre> listeOeuvre = null;

		try (PreparedStatement pst = this.conn.prepareStatement(sql)){
			pst.setInt(1, expo.getIdExposition());
			rs = pst.executeQuery();
			
			
			listeOeuvre = new ArrayList<Oeuvre>();
			Oeuvre oeuv = null;
			OeuvreDao oeuvreDao = null;
			
			while (rs.next()){
				
				oeuv = new Oeuvre();	
				oeuvreDao = new OeuvreDao(conn);
				
				oeuv = oeuvreDao.find(rs.getInt(1));
				listeOeuvre.add(oeuv);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MyException("Impossible de récupérer la liste des regroupers");
		}		
		return listeOeuvre;

	}

	
	

	
}
