package com.bancadeltempo;

import java.util.List;

public class VisualizzaPrestazioni implements Command 
{
	private BdT bdt;
	
	public VisualizzaPrestazioni(BdT bdt)
	{
		this.bdt = bdt;
	}

	@Override
	public String getCommandName() 
	{
		return "Visualizza Prestazioni in ingresso e in uscita";
	}

	@Override
	public void esegui() 
	{
		List<List<Prestazione>> inOutPrestazioni;
				
		if( (inOutPrestazioni = bdt.visualizzaRichiesteInOut(Registered.getIstance().getLoggedUser().getEmail())) == null )
		{
			System.out.println("\n\t Non ci sono prestazioni da mostrare.");
			return;
		}
		
		// ci sono prestazioni da mostrare
		
		if( inOutPrestazioni.get(1).isEmpty() ) // richieste in ingresso
		{
			System.out.println("\n\tNon ci sono richieste in ingresso.");
		}
		else
		{
			System.out.println("\n\t--- Richieste in ingresso ---\n");
			for(Prestazione pInput : inOutPrestazioni.get(1))
				System.out.println(pInput + "\n");
		}
		
		if( inOutPrestazioni.get(0).isEmpty() ) // richieste in uscita
		{
			System.out.println("\n\tNon ci sono richieste in uscita.");
		}
		else
		{
			System.out.println("\n\t--- Richieste in uscita ---\n");
			for(Prestazione pOut : inOutPrestazioni.get(0))
				System.out.println(pOut + "\n");
		}
		
	}

}
