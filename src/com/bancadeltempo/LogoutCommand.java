package com.bancadeltempo;

public class LogoutCommand implements Command 
{
	
	private BdT bdt;
	
	public LogoutCommand(BdT bdt)
	{
		this.bdt = bdt;
	}
	
	@Override
	public String getCommandName() 
	{
		return "Logout Command";
	}

	@Override
	public void esegui() 
	{
		bdt.logout();
	}

}
