package com.bancadeltempo;

public class VisualizzaCorrentistiCommand implements Command 
{

	BdT bdt;
	
	public VisualizzaCorrentistiCommand(BdT bdt)
	{
		this.bdt = bdt;
	}
	
	@Override
	public void esegui() 
	{
		bdt.visualizzaCorrentisti();

	}
	
	public String getCommandName()
	{
		return "Visualizza Correntisti";
	}

}
