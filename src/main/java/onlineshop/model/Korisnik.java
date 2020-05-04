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
@Table(name="korisnik")
public class Korisnik {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String naziv;
	@Column
	private String mesto;
	@Column
	private String adresa;
	@Column
	private String jmbg;
	@Column
	private String telefon;
	@Column
	private String brojracuna;
	@Column
	private Double stanje;
	
	
	@OneToMany(mappedBy="korisnik", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Kupovina> kupovine = new ArrayList<>();
	
	
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
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getBrojracuna() {
		return brojracuna;
	}
	public void setBrojracuna(String brojracuna) {
		this.brojracuna = brojracuna;
	}
	public Double getStanje() {
		return stanje;
	}
	public void setStanje(Double stanje) {
		this.stanje = stanje;
	}
	
	
	
	public List<Kupovina> getKupovine() {
		return kupovine;
	}
	public void setKupovine(List<Kupovina> kupovine) {
		this.kupovine = kupovine;
	}
	public void addKupovina(Kupovina kupovina) {
		if(kupovina.getKorisnik() != this) {
			kupovina.setKorisnik(this);
		}
		kupovine.add(kupovina);
	}
	
	

}
