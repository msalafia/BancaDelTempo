package com.bancadeltempo;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleReader 
{
	
	private Scanner scanner;
	public SimpleDateFormat simpleDateFormat;
	private static ConsoleReader singleton;
	
	private ConsoleReader()
	{
			scanner = new Scanner(System.in);
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	//Costruttore necessario al testing
	
	private ConsoleReader(InputStream in)
	{
			scanner = new Scanner(in);
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}	
	
	public static ConsoleReader getIstance()
	{
		if(singleton == null)
			return new ConsoleReader();
		else return singleton;
	}
	
	//metodo chiamante costruttore per il testing
	public static ConsoleReader getIstance(ByteArrayInputStream in)
	{
		if(singleton == null)
			return new ConsoleReader(in);
		else return singleton;
	}
	
	// lettura comandi per guest
	
	public Command readCommandGuest() 
	{
		System.out.println("Inserisci scelta:");
		Command cmd = null;
		boolean validInput = false;
			
		while( !validInput )
		{
			switch( readInt() )
			{
				case 1:
					cmd = new RegistrazioneCommand(BdT.getIstance());
					validInput = !validInput;
					break;
				case 2:
					validInput = !validInput;
					cmd = new LoginCommand(BdT.getIstance());
					break;
				case 3:
					cmd = new ExitCommand();
					validInput = !validInput;
					break;
				default:
					System.out.println("\tScelta inesistente. Riprova. \n");
					break;
			}
		}
		
		return cmd;
	}
	
	/* Legge il comando inserito nel menu principale di benvenuto (User Loggato) */
	
	public Command readCommandLogged()
	{
		Command cmd = null;
		boolean validInput = false;
				
		while( !validInput )
		{
			switch( readInt() )
			{
				case 0:
					cmd = new VisualizzaCorrentistiCommand(BdT.getIstance());
					validInput = !validInput;
					break;
				case 1:
					cmd = new RichiediPrestazioneCommand(BdT.getIstance());
					validInput = !validInput;
					break;
				case 2:
					cmd = new ConfermaPrestazioneCommand(BdT.getIstance());
					validInput = !validInput;
					break;
				case 3:
					cmd = new AggiungiNuovaAbilita(BdT.getIstance());
					validInput = !validInput;
					break;
				case 4:
					cmd = new VisualizzaPrestazioni(BdT.getIstance());
					validInput = !validInput;
					break;
				case 5:
					cmd = new ValutaPrestazioneCommand(BdT.getIstance());
					validInput = !validInput;
					break;
				case 6:
					cmd = new LogoutCommand(BdT.getIstance());
					validInput = !validInput;
					break;
				case 9:
					cmd = new ExitCommand(); //non serve il bdt
					validInput = !validInput;
					break;
				default:
					System.out.println("\tScelta inesistente. Riprova. \n");
					break;
			}
		}
		
		return cmd;
		
	}
	
	// prende da input un'email valida
	
	public String readEmail()
	{
		String scelta = null;
		while( !scanner.hasNext("[^@.].+@[^@.].+\\..+[^@.]") )
		{
			System.out.println("Email non valida!");
			scelta = scanner.next();
		}
		scelta = scanner.next();
		return scelta;
	}
	
	// prende da input una categoria Correntista valida in fase di registrazione
	
	public String readCategoria()
	{
		int scelta = 0;
		boolean validInput = false;
		
		while( !validInput )
		{
			scelta = readInt();
			
			if( (scelta == 0) || (scelta > 3) )
			{
				validInput = false;
				System.out.println("Seleziona una scelta valida.");
			} 
			else 
			{
				validInput = true;
			}
		}
		
		
		      // la scelta � giusta
		
			switch(scelta)
			{
			
				case 1:
					return "Pensionato";
				case 2:
					return "Lavoratore";
				case 3:
					return "Disoccupato";
				
			}
			
			return null;
		
	}
	
	// solo interi compresi da 1 a 10
	public int readValutazione()
	{
		int autoValutazione = readInt();
		
		while( autoValutazione > 10 || autoValutazione == 0 )
		{
			System.out.println("Inserisci un numero intero compreso da 1 a 10");
			autoValutazione = readInt();
		}
		return autoValutazione;
	}
	
	//solo interi tra 6 e 10
	public int readAutoValutazione()
	{
		int autoValutazione = readInt();
		
		while( autoValutazione > 10 || autoValutazione < 6 )
		{
			System.out.println("Inserisci un numero intero compreso da 6 a 10");
			autoValutazione = readInt();
		}
		return autoValutazione;
	}
	
	
	// legge un intero da input
	
	public int readInt()
	{
		//A
		boolean validInput = false;
		int scelta = 0;
		//B
		while( !validInput ) 
		{
			try
			{
				//C
				scelta = scanner.nextInt();
				//D
				if(scelta >= 0)
					//E
					validInput = true;
				else
					//F
					System.out.println("Deve essere un intero positivo!");
				
			}
			catch (InputMismatchException e) 
			{
				//G
				System.out.println("Inserisci un numero!");
				scanner.next();
		         }
		}
		//H
		return scelta;
	}
	
	
	public String readString()
	{
		return scanner.nextLine();
		
	}


}
