package onlineshop.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="kupovina")
public class Kupovina {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String sifra;
	@Column
	private Double ukupnaCena;
	@Column
	private Timestamp datumVreme;
	
	private String dateTime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "korisnik")
	private Korisnik korisnik;
	
	
	@OneToMany(mappedBy="kupovina", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Stavka> stavke = new ArrayList<>();

	public Kupovina() {
		this.ukupnaCena = 0.0;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public Double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(Double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	public Timestamp getDatumVreme() {
		return datumVreme;
	}
	public void setDatumVreme(Timestamp datumVreme) {
		this.datumVreme = datumVreme;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
	
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		if(!korisnik.getKupovine().contains(this)){
			korisnik.getKupovine().add(this);
		}
	}
	
	
	
	
	public List<Stavka> getStavke() {
		return stavke;
	}
	public void setStavke(List<Stavka> stavke) {
		this.stavke = stavke;
	}
	public void addStavka(Stavka stavka) {
		if(stavka.getKupovina() != this) {
			stavka.setKupovina(this);
		}
		stavke.add(stavka);
	}
	
	
	
	

}
