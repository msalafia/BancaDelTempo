package com.bancadeltempo;

public class Registered implements Sessione 
{
	
	private static Registered singleton;
	private Correntista loggedUser;
		
		
	public static Registered getIstance()
	{
			
		if(singleton == null)
		{
			singleton = new Registered();			
			return singleton;
		}
		else
		{
			return singleton;
		}
	}

	@Override
	public Command stampaMenu() 
	{

		Command cmd = null;
		
		System.out.println("\n\n\t------------------Banca del Tempo-------------------");
		System.out.println("\n\tInserisci il comando relativo alla scelta desiderata:");
	        System.out.println("\n\n\t\tLoggato come " + loggedUser.getEmail());
	        System.out.println("\n\n\t\t0) Visualizza i correntisti ");
	        System.out.println("\t\t1) Richiedi una Prestazione");
	        System.out.println("\t\t2) Conferma una richiesta");
	        System.out.println("\t\t3) Aggiungi una nuova Abilita'");
	        System.out.println("\t\t4) Visualizza Prestazioni");
	        System.out.println("\t\t5) Valuta una Prestazione");
	        System.out.println("\t\t6) Logout");
	        System.out.println("\t\t9) Esci ");
	        System.out.println("\n\t---------------------------------------------------------\n\n");
		        
		cmd = ConsoleReader.getIstance().readCommandLogged();
		        
	        
	        return cmd;
		
	}
	
	public void setLoggedUser(Correntista c)
	{
		this.loggedUser = c;
	}
	
	public Correntista getLoggedUser()
	{
		return this.loggedUser;
	}

}
