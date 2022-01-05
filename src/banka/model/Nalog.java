package banka.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

import banka.dao.RacunDAO;

public class Nalog {
	private int id;
	private LocalDateTime vremeKreiranja;
	private double iznos;
	private LocalDateTime vremeRealizacije;
	private Racun racunUplatioca;
	private Racun racunPrimaoca;
	
	public Nalog(int id, Racun racunUplatioca, Racun racunPrimaoca, LocalDateTime vremeKreiranja, double iznos,
			LocalDateTime vremeRealizacije) {
		this.id = id;
		this.racunUplatioca = racunUplatioca;
		this.racunPrimaoca = racunPrimaoca;
		this.vremeKreiranja = vremeKreiranja;
		this.iznos = iznos;
		this.vremeRealizacije = vremeRealizacije;
	}
	
	public Nalog(Racun racunUplatioca, Racun racunPrimaoca, LocalDateTime vremeKreiranja, double iznos,
			LocalDateTime vremeRealizacije) {
		this.racunUplatioca = racunUplatioca;
		this.racunPrimaoca = racunPrimaoca;
		this.vremeKreiranja = vremeKreiranja;
		this.iznos = iznos;
		this.vremeRealizacije = vremeRealizacije;
	}

	public Nalog() {
	}
	
	public Nalog(ResultSet rset) throws SQLException, Exception {
		int index = 1;
		
		id = rset.getInt(index++);          
		vremeKreiranja = rset.getTimestamp(index++).toLocalDateTime();
		iznos = rset.getDouble(index++);
		if (rset.getTimestamp(4) == null) {
			vremeRealizacije = null;
			index++;
		} else vremeRealizacije = rset.getTimestamp(index++).toLocalDateTime();
		racunUplatioca = RacunDAO.getByID(rset.getInt(index++));
		racunPrimaoca = RacunDAO.getByID(rset.getInt(index++));
	}

	@Override
	public String toString() {
		return "Nalog: [vremeKreiranja=" + vremeKreiranja + ", iznos=" + iznos + ", vremeRealizacije=" + vremeRealizacije
				+ " Sifra racuna uplatioca " + this.getRacunUplatioca().getId() + " Sifra racuna primaoca " + this.racunPrimaoca.getId() + "]";
	}
	
	public void tabelarniIspis() {
		System.out.printf("\tNalog ima vreme kreiranja: %17s, vreme realizacije: %17s, za iznos: %10.2f. Sifra uplatioca je %2s, a sifra primaoca %2s\n",
				vremeKreiranja.toString(), vremeRealizacije.toString(), iznos, racunUplatioca.getSifra(), racunPrimaoca.getSifra());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nalog other = (Nalog) obj;
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Racun getRacunUplatioca() {
		return racunUplatioca;
	}

	public void setRacunUplatioca(Racun racunUplatioca) {
		this.racunUplatioca = racunUplatioca;
	}

	public Racun getRacunPrimaoca() {
		return racunPrimaoca;
	}

	public void setRacunPrimaoca(Racun racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
	}

	public LocalDateTime getVremeKreiranja() {
		return vremeKreiranja;
	}

	public void setVremeKreiranja(LocalDateTime vremeKreiranja) {
		this.vremeKreiranja = vremeKreiranja;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public LocalDateTime getVremeRealizacije() {
		return vremeRealizacije;
	}

	public void setVremeRealizacije(LocalDateTime vremeRealizacije) {
		this.vremeRealizacije = vremeRealizacije;
	}
	
	
}
