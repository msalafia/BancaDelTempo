package com.bancadeltempo;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RichiediPrestazioneCommand implements Command {
	
	private BdT bdt;

	public RichiediPrestazioneCommand(BdT bdt)
	{
		this.bdt = bdt;
	}
	
	@Override
	public String getCommandName() 
	{
		return "Richiedi una Prestazione";
	}

	@Override
	public void esegui() 
	{
		String abilitaRichiesta;
		List<Correntista> performers;
		int j = 0;
		
		System.out.println("\tInserisci l'abilita' richiesta:");
		abilitaRichiesta = ConsoleReader.getIstance().readString();
		
		//viene ritornato l'elenco di tutti i correntisti che possono soddisfare la richiesta selezionata
		if((performers = bdt.richiediPrestazione(abilitaRichiesta)).isEmpty())
		{
			System.out.println("\t\nNessun correntista compatibile trovato.\n\n");
			return; // nessun correntista compatibile con la richiesta.
		}
		
		else
		{

			System.out.println("\tSono stati trovati "+performers.size() + " correntisti compatibili." );
		
			
			for (Correntista c: performers)
			{
				j = performers.indexOf(c);
				System.out.println("\n\t"+ ++j +")\t" 
									+ c.getEmail()
									+ "\n\t\tCategoria: " + c.getCategoria()
									+ "\n\t\tAbilita': " + abilitaRichiesta 
									+ "\n\t\tValutazione: "+ c.getValutazioneAbilita(abilitaRichiesta));

			}
			
		}
		
		System.out.println("\tSeleziona l'offerente desiderato (0 per uscire): ");
		int scelta = 0;
		scelta = ConsoleReader.getIstance().readInt();
		
		if (scelta == 0)
			return;
		
		while(scelta > j)
		{
			System.out.println("Inserisci una scelta valida.");
			scelta = ConsoleReader.getIstance().readInt();
			if (scelta == 0)
				return;
		}
		
		int indiceOfferente = --scelta;
		System.out.println("\tInserisci il numero di ore per la prestazione:");
		int numOre = ConsoleReader.getIstance().readInt();
		
		if( !(performers.get(indiceOfferente).calcolaCosto(numOre) > Registered.getIstance().getLoggedUser().getSaldo()) )
		{
			System.out.println("\tInserisci il giorno di inizio");
			int giornoInizio = ConsoleReader.getIstance().readInt();
			
			System.out.println("\tInserisci il mese di inizio");
			int meseInizio = ConsoleReader.getIstance().readInt();
			
			Date dataInserita = null;
			
			try {
				dataInserita = ConsoleReader.getIstance().simpleDateFormat
											.parse(Integer.toString(giornoInizio)+"/"
											+ Integer.toString(meseInizio) + "/"
											+ Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
			} catch (ParseException e) 
			{
				System.out.println("Errore nella creazione della data.");
			}
		
			
			bdt.selezionaOfferente(Registered.getIstance().getLoggedUser(), performers.get(indiceOfferente), abilitaRichiesta, dataInserita, numOre);
		} 
		else
		{
			System.out.println("Non hai saldo a sufficienza per inoltra la richiesta desiderata.");
			return;
		}
		
				
	} 

}

