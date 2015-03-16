package com.bancadeltempo;

public class Guest implements Sessione 
{
	
	private static Guest singleton;
	
	
	public static Guest getIstance()
	{
		
		if(singleton == null)
		{
			singleton = new Guest();			
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
		System.out.println("\t\t1) Registrati alla Banca del Tempo");
		System.out.println("\t\t2) Login");
		System.out.println("\t\t3) Esci");
		System.out.println("\n\t---------------------------------------------------------\n\n");
		        
		cmd = ConsoleReader.getIstance().readCommandGuest();
		        
	        
	        return cmd;
	        
	}

}
