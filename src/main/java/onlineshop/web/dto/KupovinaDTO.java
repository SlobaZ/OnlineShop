package onlineshop.web.dto;

import java.sql.Timestamp;


public class KupovinaDTO {
	
	private Integer id;
	private String sifra;
	private Double ukupnaCena;
	private Timestamp datumVreme;
	private String dateTime;

	private Integer korisnikId;
	private String korisnikNaziv;
	private Double korisnikStanje;

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

	
	
	
	
	public Integer getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}

	public String getKorisnikNaziv() {
		return korisnikNaziv;
	}

	public void setKorisnikNaziv(String korisnikNaziv) {
		this.korisnikNaziv = korisnikNaziv;
	}

	public Double getKorisnikStanje() {
		return korisnikStanje;
	}

	public void setKorisnikStanje(Double korisnikStanje) {
		this.korisnikStanje = korisnikStanje;
	}

	
	

	

}
