package com.bancadeltempo;

public class AggiungiNuovaAbilita implements Command 
{
	
	private BdT bdt;
	
	public AggiungiNuovaAbilita(BdT bdt)
	{
		this.bdt = bdt;
	}

	@Override
	public String getCommandName() 
	{
		return "Aggiungi una Nuova Abilita";
	}

	@Override
	public void esegui() 
	{
		
		String tipologiaAbilita;
		int autoValutazione,scelta;
		
		//fake login sono automaticamente loggato quindi uso come parametro la mail del fake login
		bdt.nuovaAbilita(Registered.getIstance().getLoggedUser().getEmail());
		
		do
		{
			System.out.println("\tInserisci tipologia Abilita");
			tipologiaAbilita = ConsoleReader.getIstance().readString();
			
			System.out.println("\tInserisci auto valutazione");
			autoValutazione = ConsoleReader.getIstance().readAutoValutazione();
			
			
			bdt.aggiungiAbilita(tipologiaAbilita, autoValutazione);
			
			
			System.out.println("\t1) Aggiungi altra abilita.");
			System.out.println("\t2) Fatto");
			scelta = ConsoleReader.getIstance().readInt();
			
			while(scelta != 1 && scelta != 2)
			{
				System.out.println("Inserisci scelta valida.");
				scelta = ConsoleReader.getIstance().readInt();
			}
			
		} while(scelta != 2);
		
		System.out.println("\n\t Abilita' aggiunta/e con successo!");
		
	}

}
