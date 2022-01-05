package banka.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banka.model.Nalog;
import banka.model.Racun;

public class RacunDAO {
	
	public static Racun getByID(int id) throws Exception { 
		Racun rn = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, sifra, vlasnik, stanje, raspolozivo_stanje FROM racuni WHERE id =" + id;

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				rn = new Racun(rset);
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return rn;
	}
	
	public static Racun getBySifra(String tekst) throws Exception {    
		Racun rn = null;										    

		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, sifra, vlasnik, stanje, raspolozivo_stanje FROM racuni WHERE sifra = ?"; 

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, tekst);

			rset = stmt.executeQuery();

			if (rset.next()) {
				rn = new Racun(rset);
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return rn;
	}
	
	public static Racun getByIDSaNalozima(int id) throws Exception { 
		Racun rn = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, sifra, vlasnik, stanje, raspolozivo_stanje FROM racuni WHERE id =" + id;

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				rn = new Racun(rset);
			}
			
			List<Nalog> nalozi = NalogDAO.getAllbyRacunID(rn.getId());
			rn.setNalozi(nalozi);
			
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return rn;
	}
	
	public static List<Racun> getAll() throws Exception {  
		List<Racun> sviRacuni = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, sifra, vlasnik, stanje, raspolozivo_stanje FROM racuni";

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Racun rn = new Racun(rset);
				List<Nalog> nalozi = NalogDAO.getAllbyRacunID(rn.getId());
				rn.setNalozi(nalozi);
				sviRacuni.add(rn);
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return sviRacuni;
	}
}
