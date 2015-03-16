package com.bancadeltempo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class ValutaPrestazioneTest 
{
	static BdT bdt = BdT.getIstance();
	static Correntista rosario, riccardo;
	static Abilita a1,a2;
	static Prestazione p1,p2,p3,p4,p5,p6;

	 @BeforeClass
	 public static void initializeComponent() 
	 {
	 
		 rosario = new Pensionato("Rosario","Baldassare","aa@aa.it","1234");
		 rosario.aggiungiAbilita("muratore", 6.5f);
		 rosario.aggiungiAbilita("agricoltore", 6.5f);
		 
		 riccardo = new Disoccupato("Riccardo","Rizzo","bb@aa.it","1234");
		 riccardo.aggiungiAbilita("ripetizioni", 3f);
		 riccardo.aggiungiAbilita("falegname", 10f);
		 
		 p1 = new Prestazione(rosario,riccardo,"falegname", new Date(), 2);
		 p2 = new Prestazione(riccardo,rosario,"muratore", new Date(), 3);
		 p3 = new Prestazione(rosario,riccardo,"ripetizioni", new Date(), 1);
		 p4 = new Prestazione(riccardo,rosario,"muratore", new Date(), 5);
		 p5 = new Prestazione(rosario,riccardo,"falegname", new Date(), 2);
		 p6 = new Prestazione(rosario,riccardo,"falegname", new Date(), 2);
		 
		 bdt.putPrestazione(p1);
		 bdt.putPrestazione(p2);
		 bdt.putPrestazione(p3);
		 bdt.putPrestazione(p4);
		 bdt.putPrestazione(p5);
		 bdt.putPrestazione(p6);

		 		 		
	 }
	           
	 
	 public double average(double... args)
	 {
		 int count = 0;
		 double tot = 0;
		 for (double f: args)
		 {
			 tot += f;
			 count++;
		 }
		 
		 return tot/count;
	 }
	 
	 
	@Test
	public void test() 
	{
		bdt.valutaRichiesta(p1, 5);
		bdt.valutaRichiesta(p5, 5);
		bdt.valutaRichiesta(p6, 5);

		assertTrue(riccardo.getValutazioneAbilita("falegname") == average(10f,5,5,5));
		
		
	}

}
