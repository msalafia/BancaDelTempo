package com.bancadeltempo;

public class RegistrazioneCommand implements Command {
	
	private BdT bdt;
	
	public RegistrazioneCommand(BdT bdt)
	{
		this.bdt = bdt;
	}

	@Override
	public String getCommandName()
	{
		return "Nuova Registrazione";
	}
	
	@Override
	public void esegui() 
	{
		//NuovaRegistrazioneMenu nrm = new NuovaRegistrazioneMenu();
		//nrm.start();
		String nome, cognome, email, password, tipoCategoria, tipologiaAbilita;
		int autoValutazione;
		
		//***************************Da Dissociare********************************
		
		System.out.println("\t Inserisci il nome");
		nome = ConsoleReader.getIstance().readString();
		System.out.println("\t Inserisci il cognome");
		cognome = ConsoleReader.getIstance().readString();
		System.out.println("\t Inserisci l'email");
		email = ConsoleReader.getIstance().readEmail();
		System.out.println("\t Inserisci la password");
		password = ConsoleReader.getIstance().readString();
		
		if(bdt.emailAlreadyExists(email))
		{
			System.out.println("Utente già registrato con questa email.");
			return;
		}
		
		System.out.println(email);
		
		System.out.println("\tInserisci categoria");
		System.out.println("\t1) Pensionato");
	        System.out.println("\t2) Lavoratore");
	        System.out.println("\t3) Disoccupato");
	        
	        tipoCategoria = ConsoleReader.getIstance().readCategoria();
	        
	        
	        //*****************************************************************************
	        
		try
		{
			bdt.registra(nome, cognome, email, password, tipoCategoria);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Errore durante la registrazione");
		}
		
		int scelta = 0;
		
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
		
		// dopo aver inserito le abilita' confermo la registrazione
		
		bdt.confermaRegistrazione();
				
	}

}
