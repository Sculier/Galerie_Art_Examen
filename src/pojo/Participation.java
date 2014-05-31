package pojo;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "participation", catalog = "galerieart09")
public class Participation implements java.io.Serializable {


	private Client client;
	private Exposition exposition;

	public Participation() {
	}

	public Participation(Client client, Exposition exposition) {
		
		this.client = client;
		this.exposition = exposition;
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idClient", nullable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient (Client client) {
		this.client = client;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idExposition", nullable = false)
	public Exposition getExposition() {
		return this.exposition;
	}

	public void setExposition(Exposition exposition) {
		this.exposition = exposition;
	}

	@Override
	public String toString() {
		return "Participation [client=" + client + ", exposition=" + exposition
				+ "]";
	}

	
	
	
	

	

}
