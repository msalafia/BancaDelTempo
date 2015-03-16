package com.bancadeltempo;

public class ExitCommand implements Command {

	@Override
	public String getCommandName() 
	{
		return "Exit";
	}

	@Override
	public void esegui() 
	{
		System.out.println("Arrivederci!");
	}

}
