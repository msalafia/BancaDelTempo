package com.bancadeltempo;

public class Abilita {

	private String tipologia;
	private double valutazione;

	private Correntista correntista;

	public Abilita(String tipologia, double autoValutazione) 
	{
		this.tipologia = tipologia;
		this.valutazione = autoValutazione;
	}
	
	public String getTipo()
	{
		return tipologia;
	}
	
	public String toString()
	{
		return "\n\t\t > Abilita': " + tipologia 
				+ "   Valutazione: " + valutazione;	 
	}

	public double getValutazione() 
	{
		return valutazione;
	}
	
	public void setValutazione(double val)
	{
		this.valutazione = val;
	}

}
