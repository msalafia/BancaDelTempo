package com.bancadeltempo;

public class Console 
{
	
	private static Sessione sessione;
	
	public Console(Sessione sessione)
	{
		this.sessione = sessione;
	}
	
	public void start()
	{
		Command cmd = null;
	
		do
		{
			cmd = stampaMenu();
			eseguiComando(cmd);
		}
		
		while (!cmd.getCommandName().equals("Exit"));
		
		
	}

	public Command stampaMenu() 
	{	
		return sessione.stampaMenu();
	}
	
	public void eseguiComando(Command cmd)
	{
		cmd.esegui();
	}
	
	public static void setSessione(Sessione sessione)
	{
		Console.sessione = sessione;
	}

}
