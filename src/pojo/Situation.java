package pojo;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

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
@Table(name = "situation", catalog = "galerieart09")
public class Situation implements java.io.Serializable {


	private Oeuvre oeuvre;
	private Localexpo localexpo;
	private Transport transport;
	private Exposition exposition;
	private String typeExpo;
	private Newsletter newsletter;
	
	public Situation() {
	}

	public Situation(Oeuvre oeuvre, Localexpo localexpo, 
			Transport transport, Exposition exposition, String typeExpo, Newsletter newsletter) {
		this.oeuvre = oeuvre;
		this.localexpo = localexpo;
		this.transport = transport;
		this.exposition = exposition;
		this.typeExpo = typeExpo;
		this.newsletter = newsletter;
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idOeuvre", nullable = false)
	public Oeuvre getOeuvre() {
		return this.oeuvre;
	}

	public void setOeuvre (Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAdresseLocal", nullable = false)
	public Localexpo getLocalexpo() {
		return localexpo;
	}

	public void setLocalexpo(Localexpo localexpo) {
		this.localexpo = localexpo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTransport", nullable = false)
	public Transport getTransport() {
		return this.transport;
	}

	public void setTransport (Transport transport) {
		this.transport = transport;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idExposition", nullable = true)
	public Exposition getExposition() {
		return exposition;
	}

	public void setExposition(Exposition exposition) {
		this.exposition = exposition;
	}

	@Column(name = "typeExpo", nullable = false, length = 100)
	public String getTypeExpo() {
		return typeExpo;
	}

	public void setTypeExpo(String typeExpo) {
		this.typeExpo = typeExpo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idNewsletter", nullable = true)
	public Newsletter getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(Newsletter newsletter) {
		this.newsletter = newsletter;
	}

	@Override
	public String toString() {
		return "Situation [oeuvre=" + oeuvre + ", localexpo=" + localexpo
				+ ", transport=" + transport + ", exposition=" + exposition
				+ ", typeExpo=" + typeExpo + ", newsletter=" + newsletter + "]";
	}

	
	
	
	

}
