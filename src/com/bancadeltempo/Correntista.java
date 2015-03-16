package com.bancadeltempo;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

public abstract class Correntista 
{

	private String nome;
	private String cognome;
	private String email;
	private double saldo;
	private List<Abilita> abilita;
	private String password;
	
	public Correntista(String nome, String cognome, String email, String password)
	{
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.setPassword(password);
		saldo = 10;
		abilita = new LinkedList<Abilita>();
	}
	
	public abstract String getCategoria();
	
	public String getEmail()
	{
		return email;
	}
	
	public abstract double calcolaCosto(int numOre);
	
	public void accredita(double costo)
	{
		this.saldo += costo;
	}
	
	public void addebita(double costo)
	{
		this.saldo -= costo;
	}
	
	public boolean hasAbilita(String skill)
	{
		for (Abilita a: abilita)
		{
			if(a.getTipo().equals(skill))
				return true;
		}
		return false;
	}
	
	public void aggiungiAbilita(String tipologia, double autoValutazione) 
	{
		abilita.add(new Abilita(tipologia,autoValutazione));
	}
	
	/* data una tipologia di Abilita mi ritorna l'indice di valutazione */
	
	public double getValutazioneAbilita(String tipologiaAbilita)
	{
		for(Abilita a: abilita)
		{
			if ( a.getTipo().equals(tipologiaAbilita) )
			{
				return a.getValutazione();
			}
		}
		return 0;
	}
	
	public double getSaldo()
	{
		return saldo;
	}
	
	public String toString()
	{
		String listaAbilita = "";
		
		for(Abilita a: abilita)
			listaAbilita += "" + a.toString();
		
		String dati =  "\n\tNome: " + nome
				+ "\n\tCognome: " + cognome
				+ "\n\tEmail:" + email
				+ "\n\tCategoria:" + getCategoria()
				+ "\n\tSaldo: " + saldo + " ore\n";
		
		return dati + listaAbilita;
	}
	
	public void refreshFeedback(String skill, int feedback, int count)
	{
			double average;
		
			for(Abilita s : abilita)
			{
					if(s.getTipo().equals(skill))
					{
							//Viene calcolata usando la valutazione attuale, tot non serve
							average = ((s.getValutazione()*(count)) + feedback) / (count + 1);
							s.setValutazione(average);
					}
						
			}
	}

	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

}
