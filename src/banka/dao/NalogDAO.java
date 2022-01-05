package banka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import banka.model.Nalog;

public class NalogDAO {
	
	public static boolean ad(Nalog ng) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO nalozi (vreme_kreiranja, iznos, vreme_realizacije, uplatilac_id, primalac_id) VALUES (?, ?, ?, ?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setTimestamp(index++, Timestamp.valueOf(ng.getVremeKreiranja()));
			stmt.setDouble(index++, ng.getIznos());
			stmt.setTimestamp(index++, null);
			stmt.setInt(index++, ng.getRacunUplatioca().getId());
			stmt.setInt(index++, ng.getRacunPrimaoca().getId());
			
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	public static void add(Nalog ng) throws Exception{
		double iznos = ng.getIznos();
		int uplatilacID = ng.getRacunUplatioca().getId();
		int primalacID = ng.getRacunPrimaoca().getId();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String query;
			int index;

			conn = ConnectionManager.getConnection();	
			conn.setAutoCommit(false);
			conn.commit();
			
			
			query = "INSERT INTO nalozi (vreme_kreiranja, iznos, vreme_realizacije, uplatilac_id, primalac_id) VALUES (?, ?, ?, ?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(query);
			index = 1;
			stmt.setTimestamp(index++, Timestamp.valueOf(ng.getVremeKreiranja()));
			stmt.setDouble(index++, ng.getIznos());
			stmt.setTimestamp(index++, null);
			stmt.setInt(index++, ng.getRacunUplatioca().getId());
			stmt.setInt(index++, ng.getRacunPrimaoca().getId());
			
			stmt.executeUpdate();
			stmt.close();


			query = "UPDATE racuni SET raspolozivo_stanje = raspolozivo_stanje - ? WHERE id = ?";

			stmt = conn.prepareStatement(query);
			index = 1;
			stmt.setDouble(index++, iznos);	
			stmt.setInt(index++, uplatilacID);
			
			stmt.executeUpdate();
			stmt.close();


			query = "UPDATE racuni SET raspolozivo_stanje = raspolozivo_stanje + ? WHERE id = ?";	

			stmt = conn.prepareStatement(query);
			index = 1;
			stmt.setDouble(index++, iznos);		
			stmt.setInt(index++, primalacID);
			
			stmt.executeUpdate();

			conn.commit();
		} catch (Exception ex) {
			try {conn.rollback();} catch (Exception ex1) {ex1.printStackTrace();}

			System.out.println("Greska u radu sa bazom!");

			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {conn.setAutoCommit(true);} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	public static List<Nalog> getAll() throws Exception { 
		List<Nalog> sviNalozi = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, vreme_kreiranja, iznos, vreme_realizacije, uplatilac_id, primalac_id FROM nalozi";

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Nalog ng = new Nalog(rset);
				sviNalozi.add(ng);
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return sviNalozi;
	}
	
	public static List<Nalog> getAllbyRacunID(int id) throws Exception { 
		List<Nalog> sviNalozi = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT id, vreme_kreiranja, iznos, vreme_realizacije, uplatilac_id, primalac_id FROM nalozi WHERE uplatilac_id = " + id + " OR primalac_id =" + id;

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Nalog ng = new Nalog(rset);
				sviNalozi.add(ng);
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return sviNalozi;
	}
}
