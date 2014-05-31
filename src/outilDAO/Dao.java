package outilDAO;

import java.sql.Connection;
import java.util.List;

public abstract class Dao <T,PK> {

	protected Connection conn = null;
	
	
	public Dao (Connection conn){
		this.conn =conn;
	}
	
	// la définition des méthodes CRUD
	public abstract void insert (T obj) throws MyException;
	public abstract void update (T oj) throws MyException;
	public abstract void delete (T oj) throws MyException;
	public abstract T find(PK primaryKey) throws MyException;
	public abstract List<T> findAll() throws MyException;
	
	
	
	
}
