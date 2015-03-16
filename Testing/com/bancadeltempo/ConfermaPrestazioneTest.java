package com.bancadeltempo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class ConfermaPrestazioneTest {

	//Testa che la conferma o il rifiuto di una richiesta accrediti l'offerente o il ricevente
	// del relativo costo. (Testa il pattern observer se è ben implementato.)
	@Test
	public void test() {
		BdT bdt = BdT.getIstance();
		
		Correntista richiedente = new Pensionato("A", "A", "a@a.it", "1234");
		Correntista offerente = new Lavoratore("B", "B", "b@b.it", "1234");
		
		
		int nh = 4;
		
		Prestazione select = new Prestazione(richiedente, offerente, "elettricista", new Date(1425510000000L), nh);
		select.setCosto(nh);
		select.addObserver(bdt);
		
		bdt.confermaRichiesta(select, true);
		System.out.println(offerente.getSaldo());
		System.out.println(select.getStatus());
		assertTrue(offerente.getSaldo() == 16);
		
		richiedente = new Pensionato("A", "A", "a@a.it","1234");
		select = new Prestazione(richiedente, offerente, "elettricista", new Date(1425510000000L), nh);
		select.setCosto(nh);
		select.addObserver(bdt);
		
		bdt.confermaRichiesta(select, false);
		System.out.println(richiedente.getSaldo());
		System.out.println(select.getStatus());
		assertTrue(richiedente.getSaldo() == 10);
	}

}
