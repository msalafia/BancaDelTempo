package com.bancadeltempo;

public class Lavoratore extends Correntista {

	public Lavoratore(String nome, String cognome, String email, String password) 
	{
		super(nome, cognome, email, password);
	}
	
	public String getCategoria()
	{
		return "Lavoratore";
	}

	@Override
	public double calcolaCosto(int numOre) 
	{
		return 1.5 * (double) numOre;
	}
	

}
