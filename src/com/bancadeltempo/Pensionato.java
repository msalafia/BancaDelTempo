package com.bancadeltempo;

public class Pensionato extends Correntista {

	/**
	 *  
	 */
	public Pensionato(String nome, String cognome, String email, String password) 
	{
		super(nome, cognome, email, password);
	}
	
	public String getCategoria()
	{
		return "Pensionato";
	}
	
	@Override
	public double calcolaCosto(int numOre) 
	{
		return (double) numOre;
	}

}
