package com.bancadeltempo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class RichiediPrestazioneTest {

	//Controllo che venga addebbitato il costo relativo alle ore inserite
	//in modo corretto;
	@Test
	public void test() {
		BdT bdt = BdT.getIstance();
		
		Correntista richiedente = new Pensionato("A", "A", "a@a.it","1234");
		Correntista offerente = new Lavoratore("B", "B", "b@b.it","1234");
		double saldo = richiedente.getSaldo();
		int nh;
		
		//Controlla ogni possibilie valore di ore che può richiedere in base al suo saldo (sempre 10)
		for (nh =1; (nh*1.5) <= richiedente.getSaldo(); nh++ )
		{
			
			bdt.selezionaOfferente(richiedente, offerente, "elettricista", new Date(1425510000000L), nh);
			
			double expected = (double) (saldo - (nh*1.5));
			
			assertTrue(expected == richiedente.getSaldo());
			
			richiedente = new Pensionato("A", "A", "a@a.it","1234");
		}
	}

}
