package banka.ui;

import banka.dao.ConnectionManager;
import banka.util.Util;

public class ApplicationUI {
	
	public static void ispisiMenu() {
		
		System.out.println("Banka - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Kreiraj novi nalog");
		System.out.println("\tOpcija broj 2 - Ispisi sve naloge");
		System.out.println("\tOpcija broj 3 - Rad sa pohadjanjem predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}
	
	public static void Menu() {
		int odluka = -1;
		while (odluka != 0) {
			
			ispisiMenu();
			
			System.out.print("opcija:");
			System.out.println();
			odluka = Util.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				NalogUI.kreirajNoviNalog();
				System.out.println();
				break;
			case 2:
				NalogUI.ispisiSve();
				System.out.println();
				break;
			case 3:
				System.out.println();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				System.out.println();
				break;
			}
		}
	}

	public static void main(String[] args)  {
		
		try {
			ConnectionManager.open();
		} catch (Exception ex) {
			System.out.println("Neuspesna konekcija na bazu!");

			ex.printStackTrace();
			return;
		}
		

		Menu();
		
		
		try {
			ConnectionManager.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
