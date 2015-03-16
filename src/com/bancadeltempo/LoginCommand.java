package com.bancadeltempo;

public class LoginCommand implements Command 
{
	
	private BdT bdt;
	
	public LoginCommand(BdT bdt)
	{
		this.bdt = bdt;
	}

	@Override
	public String getCommandName() 
	{
		return "Login Command";
	}

	@Override
	public void esegui() 
	{
		String email, password;
		System.out.println("\t Inserisci l'email");
		email = ConsoleReader.getIstance().readEmail();
		System.out.println("\t Inserisci password");
		password = ConsoleReader.getIstance().readString();
		
		bdt.login(email, password);
		
		
	}

}
