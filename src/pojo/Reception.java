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
@Table(name = "reception", catalog = "galerieart09")
public class Reception implements java.io.Serializable {


	private Client client;
	private Newsletter newsletter;

	public Reception() {
	}

	public Reception(Client client, Newsletter newsletter) {
		this.client = client;
		this.newsletter = newsletter;
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
	@JoinColumn(name = "idNewsletter", nullable = false)
	public Newsletter getNewsletter() {
		return this.newsletter;
	}

	public void setNewsletter(Newsletter newsletter) {
		this.newsletter = newsletter;
	}

	@Override
	public String toString() {
		return "Reception [client=" + client + ", newsletter=" + newsletter
				+ "]";
	}

	
	

	

}
