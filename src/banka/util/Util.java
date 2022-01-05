package banka.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Util {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static String DATE_TIME_FORMAT = "dd.MM.yyyy. HH:mm";
	public static String DATE_FORMAT = "dd.MM.yyyy.";
	public static String TIME_FORMAT = "HH:mm";
	
	public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	public static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
	
	public static String ocitajTekst() {
		String tekst = "";
		while (tekst.equals("") || tekst == null) {
			tekst = sc.nextLine();
		}
		return tekst;
	}
	
	public static char ocitajKarakter(){
		
		String text;
		while ( (text=sc.next())==null || text.length()!=1) {
			System.out.println("GRESKA - Pogresno unsesena vrednost za karakter, pokusajte ponovo: ");
			sc.nextLine();
		}
		char karakter = text.charAt(0);
		return karakter;
	}
	
	public static int ocitajCeoBroj() {
		while (!sc.hasNextInt()) { 
				System.out.println("Greska pri unosu broja!");
				sc.nextLine();
		}
		int unos = sc.nextInt();
		sc.nextLine();
		return unos;
	}
	
	public static long ocitajCeoBrojLong() {
		while (sc.hasNextLong() == false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		long ceoBroj = sc.nextLong();
		sc.nextLine();
		return ceoBroj;
	}

	public static double ocitajDouble() {
		while (sc.hasNextDouble() == false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		double ceoBroj = sc.nextDouble();
		sc.nextLine();
		return ceoBroj;
	}
	
	public static char ocitajOdlukuOPotvrdi(String tekst){
		System.out.println("Da li zelite " + tekst + " [Y/N]:");
		char odluka = ' ';
		while(!(odluka == 'Y' || odluka == 'N' || odluka == 'y' || odluka == 'n')){
			odluka = ocitajKarakter();
			if (!(odluka == 'Y' || odluka == 'N' || odluka == 'y' || odluka == 'n')) {
				System.out.println("Opcije su Y ili N");
			}
		}
		sc.nextLine();
		return odluka;
	}

	public static LocalDateTime ocitajDatumVreme() {
		LocalDateTime dateTime = null;

		while (dateTime == null) {
		String text = sc.nextLine();
		try {
			dateTime = LocalDateTime.parse(text, DATE_TIME_FORMATTER);
		} catch (Exception ex) {
			System.out.println("Datum i vreme su u pogresnom formatu (ocekivani format - dd.MM.yyyy. HH:mm). Pokusajte ponovo:\n");
			}
		}

		return dateTime;
	}

	public static LocalDate ocitajDatum() {
		LocalDate date = null;

		while (date == null) {
			String text = sc.nextLine();
			try {
				date = LocalDate.parse(text, DATE_FORMATTER);
			} catch (Exception ex) {
				System.out.println("Datum je u pogresnom formatu (ocekivani format - dd.MM.yyyy.). Pokusajte ponovo:\n");
			}
		}

		return date;
	}

	public static LocalTime ocitajVreme() {
		LocalTime time = null;

		while (time == null) {
			String text = sc.nextLine();
			try {
				time = LocalTime.parse(text, TIME_FORMATTER);
			} catch (Exception ex) {
				System.out.println("Vreme je u pogresnom formatu (ocekivani format - HH:mm). Pokusajte ponovo:\n");
			}
		}

		return time;
	}
}