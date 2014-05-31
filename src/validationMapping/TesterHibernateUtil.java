package validationMapping;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Categorie;


public class TesterHibernateUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = null;
		session = HibernateUtil.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 

/*			
			Categorie c1 = new Categorie("Auto-portrait");
			session.persist(c1);
			Categorie c2 = new Categorie("Nature morte");
			session.persist(c2);
			Categorie c3 = new Categorie("Vanité");
			session.persist(c3);
*/	
			
			tx.commit();
			System.out.println("pas de problème de mapping");
		}
		catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}
		
	}

}
