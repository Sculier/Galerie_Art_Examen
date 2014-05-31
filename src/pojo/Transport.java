package pojo;

// Generated 13 avr. 2014 16:46:50 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "transport", catalog = "galerieart09")
public class Transport implements java.io.Serializable {

	private Integer idTransport;
	private String heureDepot;
	private String heureRetour;
	private Date dateRetour;
	private Date dateDepot;
	private Set<Situation> situations = new HashSet<Situation>(0);

	public Transport() {
	}

	public Transport(Date dateRetour, Date dateDepot, String heureDepot, String heureRetour) {
		this.dateRetour = dateRetour;
		this.dateDepot = dateDepot;
		this.heureDepot = heureDepot;
		this.heureRetour = heureDepot;
	}

	public Transport(Date dateRetour, Date dateDepot, String heureDepot, String heureRetour,
			Set<Situation> situations) {
		this.dateRetour = dateRetour;
		this.dateDepot = dateDepot;
		this.heureDepot = heureDepot;
		this.heureRetour = heureDepot;
		this.situations = situations;
	}
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTransport", unique = true, nullable = false)
	public Integer getIdTransport() {
		return this.idTransport;
	}

	public void setIdTransport(Integer idTransport) {
		this.idTransport = idTransport;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateRetour", nullable = false, length = 10)
	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateDepot", nullable = false, length = 10)
	public Date getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}
	
	@Column(name = "heureRetour", nullable = false, length = 100)
	public String getHeureRetour() {
		return heureRetour;
	}

	public void setHeureRetour(String heureRetour) {
		this.heureRetour = heureRetour;
	}
	
	@Column(name = "heureDepot", nullable = false, length = 100)
	public String getHeureDepot() {
		return heureDepot;
	}

	public void setHeureDepot(String heureDepot) {
		this.heureDepot = heureDepot;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transport")
	public Set<Situation> getSituations() {
		return situations;
	}

	public void setSituations(Set<Situation> situations) {
		this.situations = situations;
	}
	
	
	@Override
	public String toString() {
		return "Transport [idTransport=" + idTransport + ", heureDepot="
				+ heureDepot + ", heureRetour=" + heureRetour + ", dateRetour="
				+ dateRetour + ", dateDepot=" + dateDepot + "]";
	}

	
	

	
	
}
