package com.bancadeltempo;

import java.util.ArrayList;
import java.util.List;

public class ValutaPrestazioneCommand implements Command 
{
	BdT bdt;
	
	public ValutaPrestazioneCommand(BdT bdt)
	{
		this.bdt = bdt;
	}
	
	@Override
	public String getCommandName() {
		
		return "Valutazione Prestazione";
	}

	@Override
	public void esegui() 
	{
		List<List<Prestazione>> richiesteInOut = bdt.visualizzaRichiesteInOut(Registered.getIstance().getLoggedUser().getEmail());
		List<Prestazione> richiesteOutAccepted = new ArrayList<Prestazione>();
		
		if(richiesteInOut == null)
		{
			System.out.println("Non ci sono richieste da mostrare");
			return;
		}
		
		//carico richiesteOutBox con le prestazioni in uscita già confermate in Accepted;
		for(Prestazione pr : richiesteInOut.get(0))
		{
				if(pr.isAccepted())
				{
						richiesteOutAccepted.add(pr);
				}
		}
		
		if(richiesteOutAccepted.isEmpty())
		{
				System.out.println("Non ci sono richieste da mostrare");
				return;
		}
		
		System.out.println("\tSono presenti le seguenti prestazioni complete a cui lasciare un Feedback:");
		System.out.println("\t_______________________________________________________\n");
		
		for(Prestazione pr : richiesteOutAccepted)
		{
			System.out.println("\t" + richiesteOutAccepted.indexOf(pr) + ")" + pr + "\n");
		}
		
		System.out.println("\n\tInserisci il numero corrispondente alla prestazione a cui lasciare il Feedback:");
		
		int selectIndex = ConsoleReader.getIstance().readInt();
		
		while(selectIndex >= richiesteOutAccepted.size())
		{
			System.out.println("Indice non valido!");
			selectIndex = ConsoleReader.getIstance().readInt();
		}
		
		System.out.println("\tInserisci una valutazione compresa tra 1 e 10:");
		int valutazione = ConsoleReader.getIstance().readValutazione();
		
		bdt.valutaRichiesta(richiesteOutAccepted.get(selectIndex), valutazione);
		
		System.out.println("Feedback lasciato con successo.");

	}

}
