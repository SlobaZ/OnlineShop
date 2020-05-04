package onlineshop.web.dto;

import java.sql.Timestamp;

public class StavkaDTO {
	
	private Integer id;
	private Integer kolicinaStavke;
	private Double cenaStavke;
	
	
	private Integer kupovinaId;
	private String kupovinaSifra;
	private Double kupovinaUkupnaCena;
	private Timestamp kupovinaDatumVreme;
	private String kupovinaDateTime;
	
	
	private Integer proizvodId;
	private String proizvodNaziv;
	private Integer proizvodKolicina;
	private Double proizvodCena;
	
	
	
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
	
	
	
	
	
	public Integer getKupovinaId() {
		return kupovinaId;
	}
	public void setKupovinaId(Integer kupovinaId) {
		this.kupovinaId = kupovinaId;
	}
	public String getKupovinaSifra() {
		return kupovinaSifra;
	}
	public void setKupovinaSifra(String kupovinaSifra) {
		this.kupovinaSifra = kupovinaSifra;
	}
	public Double getKupovinaUkupnaCena() {
		return kupovinaUkupnaCena;
	}
	public void setKupovinaUkupnaCena(Double kupovinaUkupnaCena) {
		this.kupovinaUkupnaCena = kupovinaUkupnaCena;
	}
	public Timestamp getKupovinaDatumVreme() {
		return kupovinaDatumVreme;
	}
	public void setKupovinaDatumVreme(Timestamp kupovinaDatumVreme) {
		this.kupovinaDatumVreme = kupovinaDatumVreme;
	}
	public String getKupovinaDateTime() {
		return kupovinaDateTime;
	}
	public void setKupovinaDateTime(String kupovinaDateTime) {
		this.kupovinaDateTime = kupovinaDateTime;
	}
	
	
	
	
	
	public Integer getProizvodId() {
		return proizvodId;
	}
	public void setProizvodId(Integer proizvodId) {
		this.proizvodId = proizvodId;
	}
	public String getProizvodNaziv() {
		return proizvodNaziv;
	}
	public void setProizvodNaziv(String proizvodNaziv) {
		this.proizvodNaziv = proizvodNaziv;
	}
	public Integer getProizvodKolicina() {
		return proizvodKolicina;
	}
	public void setProizvodKolicina(Integer proizvodKolicina) {
		this.proizvodKolicina = proizvodKolicina;
	}
	public Double getProizvodCena() {
		return proizvodCena;
	}
	public void setProizvodCena(Double proizvodCena) {
		this.proizvodCena = proizvodCena;
	}
	
	
	
	
	

}
