package pojo;

// Generated 13 avr. 2014 16:46:50 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Localexpo generated by hbm2java
 */
@Entity
@Table(name = "localexpo", catalog = "galerieart09")
public class Localexpo implements java.io.Serializable {

	private int idAdresseLocal;
	private Adresse adresse;
	private String nomLocal;
	private Double superficieExpo;
	private String telephoneLocal;	
	private Set<Situation> situations = new HashSet<Situation>(0);
	private Set<Horaire> horaires = new HashSet<Horaire>(0);
	
	public Localexpo() {
	}

	public Localexpo(Adresse adresse, String nomLocal, String telephoneLocal) {
		this.adresse = adresse;
		this.nomLocal = nomLocal;
		this.telephoneLocal = telephoneLocal;
	}

	public Localexpo(Adresse adresse, String nomLocal, Double superficieExpo,
			String telephoneLocal, Set<Exposition> expositions, Set<Situation> situations,Set<Horaire> horaires) {
		this.adresse = adresse;
		this.nomLocal = nomLocal;
		this.superficieExpo = superficieExpo;
		this.telephoneLocal = telephoneLocal;
		this.situations = situations;
		this.horaires=horaires;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "adresse"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "idAdresseLocal", unique = true, nullable = false)
	public int getIdAdresseLocal() {
		return this.idAdresseLocal;
	}

	public void setIdAdresseLocal(int idAdresseLocal) {
		this.idAdresseLocal = idAdresseLocal;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Column(name = "nomLocal", nullable = false, length = 100)
	public String getNomLocal() {
		return this.nomLocal;
	}

	public void setNomLocal(String nomLocal) {
		this.nomLocal = nomLocal;
	}

	@Column(name = "superficieExpo", nullable = true, precision = 22, scale = 0)
	public Double getSuperficieExpo() {
		return this.superficieExpo;
	}

	public void setSuperficieExpo(Double superficieExpo) {
		this.superficieExpo = superficieExpo;
	}

	@Column(name = "telephoneLocal", nullable = false, length = 100)
	public String getTelephoneLocal() {
		return this.telephoneLocal;
	}

	public void setTelephoneLocal(String telephoneLocal) {
		this.telephoneLocal = telephoneLocal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localexpo")
	public Set<Situation> getSituations() {
		return situations;
	}

	public void setSituations(Set<Situation> situations) {
		this.situations = situations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localexpo")
	public Set<Horaire> getHoraires() {
		return horaires;
	}

	public void setHoraires(Set<Horaire> horaires) {
		this.horaires = horaires;
	}

	@Override
	public String toString() {
		return "Localexpo [idAdresseLocal=" + idAdresseLocal + ", adresse="
				+ adresse + ", nomLocal=" + nomLocal + ", superficieExpo="
				+ superficieExpo + ", telephoneLocal=" + telephoneLocal + "]";
	}

	
	
	
}
