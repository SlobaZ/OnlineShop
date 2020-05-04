package onlineshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import onlineshop.model.Korisnik;
import onlineshop.model.Kupovina;
import onlineshop.model.Proizvod;
import onlineshop.model.Stavka;
import onlineshop.service.KorisnikService;
import onlineshop.service.KupovinaService;
import onlineshop.service.ProizvodService;
import onlineshop.service.StavkaService;
import onlineshop.utils.PomocnaKlasa;



@Component
public class TestData {

	@Autowired
	private ProizvodService proizvodService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private StavkaService stavkaService;
	
	@PostConstruct
	public void init() {
		
		Proizvod proizvod1 = new Proizvod();
		proizvod1.setNaziv("Patike");
		proizvod1.setKolicina(50);
		proizvod1.setCena(9000.0);
		proizvod1 = proizvodService.save(proizvod1);
		
		Proizvod proizvod2 = new Proizvod();
		proizvod2.setNaziv("Cipele");
		proizvod2.setKolicina(70);
		proizvod2.setCena(12000.0);
		proizvod2 = proizvodService.save(proizvod2);
		
		Proizvod proizvod3 = new Proizvod();
		proizvod3.setNaziv("Jakna");
		proizvod3.setKolicina(30);
		proizvod3.setCena(15000.0);
		proizvod3 = proizvodService.save(proizvod3);
		
		Proizvod proizvod4 = new Proizvod();
		proizvod4.setNaziv("Pantalone");
		proizvod4.setKolicina(20);
		proizvod4.setCena(5000.0);
		proizvod4 = proizvodService.save(proizvod4);
		
		Proizvod proizvod5 = new Proizvod();
		proizvod5.setNaziv("Kosulja");
		proizvod5.setKolicina(40);
		proizvod5.setCena(4000.0);
		proizvod5 = proizvodService.save(proizvod5);
		
		Proizvod proizvod6 = new Proizvod();
		proizvod6.setNaziv("Dzemper");
		proizvod6.setKolicina(30);
		proizvod6.setCena(6000.0);
		proizvod6 = proizvodService.save(proizvod6);
		
		
		Korisnik korisnik1 = new Korisnik();
		korisnik1.setNaziv("Pera Peric");
		korisnik1.setMesto("Novi Sad");
		korisnik1.setAdresa("Marka Miljanova 55");
		korisnik1.setJmbg("1004963112233");
		korisnik1.setTelefon("064112233");
		korisnik1.setBrojracuna("300-123456-77");
		korisnik1.setStanje(30000.0); 
		korisnik1 = korisnikService.save(korisnik1);

		Korisnik korisnik2 = new Korisnik();
		korisnik2.setNaziv("Sima Simic");
		korisnik2.setMesto("Beograd");
		korisnik2.setAdresa("Vojvode Milenka 30");
		korisnik2.setJmbg("1507956112233");
		korisnik2.setTelefon("065112233");
		korisnik2.setBrojracuna("300-123456-88");
		korisnik2.setStanje(140000.0); 
		korisnik2 = korisnikService.save(korisnik2);
		
		Korisnik korisnik3 = new Korisnik();
		korisnik3.setNaziv("Jova Jovic");
		korisnik3.setMesto("Nis");
		korisnik3.setAdresa("Cara Dusana 45");
		korisnik3.setJmbg("2310974112233");
		korisnik3.setTelefon("063112233");
		korisnik3.setBrojracuna("300-123456-22");
		korisnik3.setStanje(130000.0); 
		korisnik3 = korisnikService.save(korisnik3);
		
		
		Kupovina kupovina1 = new Kupovina();
		kupovina1.setSifra("A 123");
		kupovina1.setDatumVreme(java.sql.Timestamp.valueOf("2018-09-23 10:10:10"));
		kupovina1.setDateTime("23.09.2018. 10:10");
		kupovina1.setKorisnik(korisnik1);
		kupovina1 = kupovinaService.save(kupovina1);
		
		Kupovina kupovina2 = new Kupovina();
		kupovina2.setSifra("B 456");
		kupovina2.setDatumVreme(java.sql.Timestamp.valueOf("2019-04-15 08:25:20"));
		kupovina2.setDateTime("15.04.2019. 08:25");
		kupovina2.setKorisnik(korisnik2);
		kupovina2 = kupovinaService.save(kupovina2);
		
		Kupovina kupovina3 = new Kupovina();
		kupovina3.setSifra("C 789");
		kupovina3.setDatumVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
		kupovina3.setDateTime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
		kupovina3.setKorisnik(korisnik3);
		kupovina3 = kupovinaService.save(kupovina3);

		Stavka stavka1 = new Stavka();
		stavka1.setProizvod(proizvod1);
		stavka1 = stavkaService.save(stavka1);
		
		Stavka stavka2 = new Stavka();
		stavka2.setProizvod(proizvod2);
		stavka2 = stavkaService.save(stavka2);
		
		Stavka stavka3 = new Stavka();
		stavka3.setProizvod(proizvod3);
		stavka3 = stavkaService.save(stavka3);
		
		Stavka stavka4 = new Stavka();
		stavka4.setProizvod(proizvod4);
		stavka4 = stavkaService.save(stavka4);

		Stavka stavka5 = new Stavka();
		stavka5.setProizvod(proizvod5);
		stavka5 = stavkaService.save(stavka5);
		
		Stavka stavka6 = new Stavka();
		stavka6.setProizvod(proizvod6);
		stavka6 = stavkaService.save(stavka6);
		
		kupovina1.addStavka(stavka1);
		kupovina1.addStavka(stavka2);
		kupovina1.addStavka(stavka3);
		kupovina1.addStavka(stavka4);
		kupovina1.addStavka(stavka5);
		kupovina1.addStavka(stavka6);
		kupovinaService.save(kupovina1);
		
		
		Stavka stavka7 = new Stavka();
		stavka7.setProizvod(proizvod1);
		stavka7 = stavkaService.save(stavka7);
		
		Stavka stavka8 = new Stavka();
		stavka8.setProizvod(proizvod2);
		stavka8 = stavkaService.save(stavka8);
		
		Stavka stavka9 = new Stavka();
		stavka9.setProizvod(proizvod3);
		stavka9 = stavkaService.save(stavka9);
		
		Stavka stavka10 = new Stavka();
		stavka10.setProizvod(proizvod4);
		stavka10 = stavkaService.save(stavka10);

		Stavka stavka11 = new Stavka();
		stavka11.setProizvod(proizvod5);
		stavka11 = stavkaService.save(stavka11);
		
		Stavka stavka12 = new Stavka();
		stavka12.setProizvod(proizvod6);
		stavka12 = stavkaService.save(stavka12);
		
		kupovina2.addStavka(stavka7);
		kupovina2.addStavka(stavka8);
		kupovina2.addStavka(stavka9);
		kupovina2.addStavka(stavka10);
		kupovina2.addStavka(stavka11);
		kupovina2.addStavka(stavka12);
		kupovinaService.save(kupovina2);
		
		
		
		Stavka stavka13 = new Stavka();
		stavka13.setProizvod(proizvod1);
		stavka13 = stavkaService.save(stavka13);
		
		Stavka stavka14 = new Stavka();
		stavka14.setProizvod(proizvod2);
		stavka14 = stavkaService.save(stavka14);
		
		Stavka stavka15 = new Stavka();
		stavka15.setProizvod(proizvod3);
		stavka15 = stavkaService.save(stavka15);
		
		Stavka stavka16 = new Stavka();
		stavka16.setProizvod(proizvod4);
		stavka16 = stavkaService.save(stavka16);
		
		Stavka stavka17 = new Stavka();
		stavka17.setProizvod(proizvod5);
		stavka17 = stavkaService.save(stavka17);
			
		Stavka stavka18 = new Stavka();
		stavka18.setProizvod(proizvod6);
		stavka18 = stavkaService.save(stavka18);
		
		kupovina3.addStavka(stavka13);
		kupovina3.addStavka(stavka14);
		kupovina3.addStavka(stavka15);
		kupovina3.addStavka(stavka16);
		kupovina3.addStavka(stavka17);
		kupovina3.addStavka(stavka18);
		kupovinaService.save(kupovina3);

	}

}
