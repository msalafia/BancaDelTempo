package com.bancadeltempo;

import java.util.List;

public class ConfermaPrestazioneCommand implements Command {
	
	BdT bdt;

	public ConfermaPrestazioneCommand(BdT bdt)
	{
		this.bdt = bdt;
	}
	
	@Override
	public String getCommandName() 
	{
		return "Conferma Prestazione";
	}

	@Override
	public void esegui() 
	{
		List<Prestazione> richiesteInBox = bdt.visualizzaRichieste(Registered.getIstance().getLoggedUser().getEmail());
		
		if(richiesteInBox.isEmpty())
		{
			System.out.println("\tNessuna Richiesta in entrata!");
			return;
		}
		
		for(Prestazione pr: richiesteInBox)
			System.out.println("\n\t"+ richiesteInBox.indexOf(pr) + ") " + pr);
		
		System.out.println("\tInserisci il numero corrispondente alla richiesta da confermare:");
		
		int selectIndex = ConsoleReader.getIstance().readInt();
		
		while(selectIndex >= richiesteInBox.size())
		{
			System.out.println("Indice non valido!");
			selectIndex = ConsoleReader.getIstance().readInt();
		}
		
		System.out.println("\tVuoi Confermare o Rifiutare la Prestazione selezionata?:");
		System.out.println("\t1) Accetta");
		System.out.println("\t2) Rifiuta");	
		
		int scelta = ConsoleReader.getIstance().readInt();
		
		while(scelta > 2 || scelta == 0)
		{
			System.out.println("\tComando non contemplato!");
			scelta = ConsoleReader.getIstance().readInt();
		}
		
		boolean accept = (scelta == 1) ? true : false;
		
		bdt.confermaRichiesta(richiesteInBox.get(selectIndex), accept);
	}

}
