package com.bancadeltempo;

public class BdTApp {

	public static void main(String[] args) 
	{
		
		//new FakeLogin(); // login fittizio, user Mario Rossi e caricamento altri correntisti in memoria principale
		
		new Console(Guest.getIstance()).start();

	}

}
