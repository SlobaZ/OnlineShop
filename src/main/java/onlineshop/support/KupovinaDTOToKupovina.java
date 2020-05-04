package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.model.Korisnik;
import onlineshop.model.Kupovina;
import onlineshop.service.KorisnikService;
import onlineshop.service.KupovinaService;
import onlineshop.utils.PomocnaKlasa;
import onlineshop.web.dto.KupovinaDTO;


@Component
public class KupovinaDTOToKupovina implements Converter<KupovinaDTO, Kupovina>{

	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Override
	public Kupovina convert(KupovinaDTO kupovinaDTO) {
		
		Korisnik korisnik = korisnikService.getOne(kupovinaDTO.getKorisnikId());
		if(korisnik!=null) {
		Kupovina kupovina = null;
		
		if(kupovinaDTO.getId() != null){
			kupovina = kupovinaService.getOne(kupovinaDTO.getId());

		}
		else {
			kupovina = new Kupovina();
		}
					
			kupovina.setId(kupovinaDTO.getId());
			kupovina.setSifra(kupovinaDTO.getSifra());
			kupovina.setUkupnaCena(0.0);
			if(kupovinaDTO.getDatumVreme()==null) {
				kupovina.setDatumVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
				kupovina.setDateTime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
			}
			if(kupovinaDTO.getDatumVreme()!=null) {
				kupovina.setDatumVreme(PomocnaKlasa.KonvertujStringUSqlDatumIVreme(kupovinaDTO.getDateTime()));
				kupovina.setDateTime(kupovinaDTO.getDateTime());
			}
			kupovina.setKorisnik(korisnik);
			return kupovina;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	

	public List<Kupovina> convert(List<KupovinaDTO> kupovinaDTOs){
		List<Kupovina> ret = new ArrayList<>();
		
		for(KupovinaDTO kupovinaDTO : kupovinaDTOs){
			ret.add(convert(kupovinaDTO));
		}
		
		return ret;
	}
}
