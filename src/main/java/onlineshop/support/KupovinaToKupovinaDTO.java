package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.model.Kupovina;
import onlineshop.utils.PomocnaKlasa;
import onlineshop.web.dto.KupovinaDTO;


@Component
public class KupovinaToKupovinaDTO implements Converter<Kupovina, KupovinaDTO>{

	@Override
	public KupovinaDTO convert(Kupovina kupovina) {
		
		KupovinaDTO retValue = new KupovinaDTO();
		
		retValue.setId(kupovina.getId());
		retValue.setSifra(kupovina.getSifra());
		retValue.setUkupnaCena(kupovina.getUkupnaCena());
		if(kupovina.getDatumVreme()==null) {
			retValue.setDatumVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
			retValue.setDateTime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
		}
		else {
			retValue.setDatumVreme(kupovina.getDatumVreme());
			retValue.setDateTime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(kupovina.getDatumVreme()));
		}
		
		retValue.setKorisnikId(kupovina.getKorisnik().getId());
		retValue.setKorisnikNaziv(kupovina.getKorisnik().getNaziv());
		retValue.setKorisnikStanje(kupovina.getKorisnik().getStanje());

		return retValue;
	}

	public List<KupovinaDTO> convert(List<Kupovina> kupovine){
		List<KupovinaDTO> ret = new ArrayList<>();
		
		for(Kupovina kupovina : kupovine){
			ret.add(convert(kupovina));
		}
		
		return ret;
	}

}
