package onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="stavka")
public class Stavka {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private Integer kolicinaStavke;
	@Column
	private Double cenaStavke;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "kupovina")
	private Kupovina kupovina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "proizvod")
	private Proizvod proizvod;
	
	
	public Stavka() {
		this.kolicinaStavke = 0;
		this.cenaStavke = 0.0;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKolicinaStavke() {
		return kolicinaStavke;
	}
	public void setKolicinaStavke(Integer kolicinaStavke) {
		this.kolicinaStavke = kolicinaStavke;
	}
	public Double getCenaStavke() {
		return cenaStavke;
	}
	public void setCenaStavke(Double cenaStavke) {
		this.cenaStavke = cenaStavke;
	}
	
	
	
	public Kupovina getKupovina() {
		return kupovina;
	}
	public void setKupovina(Kupovina kupovina) {
		this.kupovina = kupovina;
		if(!kupovina.getStavke().contains(this)){
			kupovina.getStavke().add(this);
		}
	}
	
	
	public Proizvod getProizvod() {
		return proizvod;
	}
	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
		if(!proizvod.getStavke().contains(this)){
			proizvod.getStavke().add(this);
		}
	}
	
	
	
	
	

}
