package onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="proizvod")
public class Proizvod {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String naziv;
	@Column
	private Integer kolicina;
	@Column
	private Double cena;
	
	@OneToMany(mappedBy="proizvod", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Stavka> stavke = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	
	
	public List<Stavka> getStavke() {
		return stavke;
	}
	public void setStavke(List<Stavka> stavke) {
		this.stavke = stavke;
	}
	public void addStavka(Stavka stavka) {
		if(stavka.getProizvod() != this) {
			stavka.setProizvod(this);
		}
		stavke.add(stavka);
	}
	
	
	
	


	
	
	
	
	

}
