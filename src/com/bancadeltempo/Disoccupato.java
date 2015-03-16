package com.bancadeltempo;

public class Disoccupato extends Correntista {

	public Disoccupato(String nome, String cognome, String email, String password) 
	{
		super(nome, cognome, email, password);
	}
	
	public String getCategoria()
	{
		return "Disoccupato";
	}
	
	@Override
	public double calcolaCosto(int numOre) 
	{
		return 1.2 * (double) numOre;
	}
}
