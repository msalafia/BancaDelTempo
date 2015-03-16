package com.bancadeltempo;


import java.util.Date;
import java.util.Observable;

public class Prestazione extends Observable
{

	private String abilita, status;
	private Correntista richiedente,offerente;
	private Date dataInizio;
	private int numOre, valutazione;
	private double costo;
	
	
	public Prestazione(Correntista richiedente, Correntista offerente, String abilita, Date dataInizio, int numOre)
	{
		this.richiedente = richiedente;
		this.offerente = offerente;
		this.abilita = abilita;
		this.dataInizio = dataInizio;
		this.numOre = numOre;
		this.status = "Pending";
	}
	
	public void setCosto(int numOre)
	{
		this.costo = offerente.calcolaCosto(numOre);
		richiedente.addebita(offerente.calcolaCosto(numOre));
	}
	
	private void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
			return this.status;
	}
	
	public boolean isPending()
	{
		if(status.equals("Pending"))
			return true;
		else
			return false;
	}
	
	public boolean isComplete()
	{
		if(status.equals("Complete"))
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		return "\tPrestazione per "+ abilita
				+ "\n\tRichiedente: " + richiedente.getEmail()
				+ "\n\tOfferente: " + offerente.getEmail()
				+ "\n\tData Inizio: " + ConsoleReader.getIstance().simpleDateFormat.format(dataInizio)
				+ "\n\tNumero Ore: " + numOre
				+ "\n\tCosto: " + costo
				+ "\n\tSTATUS: "+ status;
			
	}
	
	public Correntista getOfferente()
	{
		return offerente;
	}
	
	public Correntista getRichiedente()
	{
		return richiedente;
	}
	
	public void setAsAccepted(boolean accept)
	{
		if(isPending())
		{
			if(accept)
			{
				setStatus("Accepted");
				setChanged();
				notifyObservers();
			}
			else
			{
				setStatus("Refused");
				setChanged();
				notifyObservers();
			}
		}
		else
			System.out.println("Richiesta gia' confermata.");
	}
	
	public boolean isAccepted()
	{
		return (status.equals("Accepted")) ? true : false;
	}
	
	public double getCosto()
	{
		return this.costo;
	}
	
	public String getAbilita()
	{
		return this.abilita;
	}
	
	public void setValutazione(int valutazione)
	{
		this.valutazione = valutazione;
	}
	
	public int getValutazione()
	{
		return valutazione;
	}
	

	public void setCompleted(int feedback, int count) 
	{
		setValutazione(feedback);
		
		this.setStatus("Complete");
		
		count++;
				
		offerente.refreshFeedback(this.abilita, feedback, count);
		
	}
	
}
