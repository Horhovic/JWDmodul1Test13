package banka.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Racun {
	private int id;
	private String sifra;
	private String vlasnik;
	private double stanje;
	private double raspolozivoStanje;
	private List<Nalog> nalozi = new ArrayList<>();
	
	public Racun(int id, String sifra, String vlasnik, double stanje, double raspolozivoStanje) {
		this.id = id;
		this.sifra = sifra;
		this.vlasnik = vlasnik;
		this.stanje = stanje;
		this.raspolozivoStanje = raspolozivoStanje;
	}

	public Racun() {
	}
	
	public Racun(ResultSet rset) throws SQLException { 
		int index = 1;
		
		id = rset.getInt(index++);
		sifra = rset.getString(index++);
		vlasnik = rset.getString(index++);
		stanje = rset.getDouble(index++);
		raspolozivoStanje = rset.getDouble(index++);
	}

	@Override
	public String toString() {
		return "Racun [id=" + id + ", sifra=" + sifra + ", vlasnik=" + vlasnik + ", stanje=" + stanje
				+ ", raspolozivoStanje=" + raspolozivoStanje + ", nalozi=" + nalozi + "]";
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
		Racun other = (Racun) obj;
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}

	public double getStanje() {
		return stanje;
	}

	public void setStanje(double stanje) {
		this.stanje = stanje;
	}

	public double getRaspolozivoStanje() {
		return raspolozivoStanje;
	}

	public void setRaspolozivoStanje(double raspolozivoStanje) {
		this.raspolozivoStanje = raspolozivoStanje;
	}

	public List<Nalog> getNalozi() {
		return nalozi;
	}

	public void setNalozi(List<Nalog> nalozi) {
		this.nalozi = nalozi;
	}
	
	
	
}
