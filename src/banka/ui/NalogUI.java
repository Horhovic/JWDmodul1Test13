package banka.ui;

import java.time.LocalDateTime;
import java.util.List;

import banka.dao.NalogDAO;
import banka.dao.RacunDAO;
import banka.model.Nalog;
import banka.model.Racun;
import banka.util.Util;

public class NalogUI {
	
	public static void kreirajNoviNalog() {  
		LocalDateTime vremeKreiranja = LocalDateTime.now();
		double iznos;
		LocalDateTime vremeRealizacije = null;
		Racun racunUplatioca;
		Racun racunPrimaoca;
	
		try {
			do {
				System.out.println("Unesite sifru racuna uplatioca: ");
				String sifraUplatioca = Util.ocitajTekst();
		
				racunUplatioca = RacunDAO.getBySifra(sifraUplatioca);
			
				if (racunUplatioca == null) {
					System.out.println("Racun ne postoji!\n");
				}
			} while (racunUplatioca == null);
			
			do {
				System.out.println("Unesite sifru racuna primaoca: ");
				String sifraPrimaoca = Util.ocitajTekst();
		
				racunPrimaoca = RacunDAO.getBySifra(sifraPrimaoca);
			
				if (racunPrimaoca == null) {
					System.out.println("Racun ne postoji!\n");
				}
			} while (racunPrimaoca == null);

			do{
				System.out.println("Unesite iznos: ");
				iznos = Util.ocitajDouble();
				if (iznos > racunUplatioca.getRaspolozivoStanje()) {
					System.out.println("Nema toliko novca na raspolaganju!");
				}
			} while (iznos > racunUplatioca.getRaspolozivoStanje());
	
			Nalog ng = new Nalog(racunUplatioca, racunPrimaoca, vremeKreiranja, iznos, vremeRealizacije);
	
			NalogDAO.add(ng);
			
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
	}
	
	public static void ispisiSve() {
		
		List<Nalog> sviNalozi = null;
		
		try {
			sviNalozi = NalogDAO.getAll();
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
		System.out.println();
		
		sviNalozi.forEach(nalog -> nalog.tabelarniIspis());
	}
	
}
