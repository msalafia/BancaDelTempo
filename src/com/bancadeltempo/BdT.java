package com.bancadeltempo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class BdT implements Observer
{
	private Correntista ct;
	private static BdT singleton;
	private Hashtable<String, Correntista> correntisti;
	private List<Prestazione> prestazioni;
	
	private BdT() 
	{
		correntisti = new Hashtable<String, Correntista>();
		prestazioni = new LinkedList<Prestazione>();
		
		// carico alcuni utenti in memoria
		Correntista loggedUser;
		loggedUser = new Pensionato("Mario", "Rossi", "mariorossi@gmail.com","1234");
		loggedUser.aggiungiAbilita("giardiniere", 7);
		loggedUser.aggiungiAbilita("restauratore", 9);
		loggedUser.aggiungiAbilita("falegname", 10);
		
		correntisti.put(loggedUser.getEmail(), loggedUser);
		
		Correntista loggedUser1;
		loggedUser1 = new Disoccupato("Rosario", "Baldassare", "rosario@gmail.com","1234");
		loggedUser1.aggiungiAbilita("meccanico", 7);
		
		correntisti.put(loggedUser1.getEmail(), loggedUser1);

		
	}
	
	public static BdT getIstance()
	{
		if(singleton == null)
		{
			singleton = new BdT();
			return singleton;
		}
		else
			return singleton;
	}
	
	
	/**
	 * 
	 * 		- UC1 - [ Registrazione Correntista ]
	 * 
	 */
	
	public void registra(String nome, String cognome, String email, String password, String tipoCategoria) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException 
	{
		ct = (Correntista) Class.forName("com.bancadeltempo."+tipoCategoria).getConstructor(String.class, String.class, String.class, String.class).newInstance(nome,cognome,email,password);
	}
	
	public void aggiungiAbilita(String tipologia, int autoValutazione) 
	{
		ct.aggiungiAbilita(tipologia, autoValutazione);
	}
	
	public void confermaRegistrazione() 
	{
		correntisti.put(ct.getEmail(), ct);
	}
	
	public boolean emailAlreadyExists(String email)
	{
		return correntisti.containsKey(email);
	}
	
	/**
	 * 
	 * 		- UC2 - [ Aggiungi una nuova abilità ]
	 * 
	 */
	
	public void nuovaAbilita(String emailCorrentista)
	{
		ct = correntisti.get(emailCorrentista);
	}
	
	
	
	/**
	 * 
	 * 		- UC3 - [ Richiesta di una prestazione ]
	 * 
	 */
	
	
	public List<Correntista> richiediPrestazione(String abilitaRichiesta)
	{
		
		List<Correntista> performers = new ArrayList<Correntista>();
		
		for(Correntista c: correntisti.values())
		{
			if (c.hasAbilita(abilitaRichiesta))
				performers.add(c);
		}
	
		return performers;
		
	}
	
	public void selezionaOfferente(Correntista richiedente, Correntista offerente, String abilita, Date dataInizio, int numOre )
	{
	
		Prestazione p = new Prestazione(richiedente,offerente,abilita,dataInizio,numOre);
		
		//la prestazione ha un costo e viene addebitato l'importo al richiedente
		p.setCosto(numOre);
		
		p.addObserver(this);
		
		prestazioni.add(p);		
	}
	
	
	/**
	 * 
	 * 		- UC4 - [ Visualizza Richieste in ingresso e in uscita ]
	 * 
	 */
	
	public List<List<Prestazione>> visualizzaRichiesteInOut(String emailCorrentista)
	{
		List<List<Prestazione>> inOutPrestazioni = new ArrayList<List<Prestazione>>();
		
		List<Prestazione> inPrestazioni = new ArrayList<Prestazione>();
		List<Prestazione> outPrestazioni = new ArrayList<Prestazione>();
		
		if(prestazioni.isEmpty())
			return null;
		
		for(Prestazione pr: prestazioni)
		{
			// prendo le richieste in uscita, indice = 0
			if(pr.getRichiedente().getEmail().equals(emailCorrentista))
			{
				outPrestazioni.add(pr);
			}
			
			// prendo le richieste in ingresso, indice = 1 
			if (pr.getOfferente().getEmail().equals(emailCorrentista))
			{
				inPrestazioni.add(pr);
			}
		}
		
		inOutPrestazioni.add(outPrestazioni);
		inOutPrestazioni.add(inPrestazioni);

		return inOutPrestazioni;
	}
	
	
	
	/**
	 * 
	 * 		- UC5 - [ Conferma di una prestazione ]
	 * 
	 */
	
	public List<Prestazione> visualizzaRichieste(String email)
	{
		List<Prestazione> prestazioniRichieste = new ArrayList<Prestazione>();
		for(Prestazione pr: prestazioni)
			if(pr.getOfferente().getEmail().equals(email) && pr.isPending())
			{
				prestazioniRichieste.add(pr);
			}
		return prestazioniRichieste;
	}
	
	public void confermaRichiesta(Prestazione select, boolean accept)
	{
		select.setAsAccepted(accept);
	}
	
	
	/**
	 * 
	 * 		- Visualizzazione correntisti in memoria per conferma -
	 * 
	 */
	
	public void visualizzaCorrentisti()
	{
		if(!correntisti.isEmpty())
		{
			for(Correntista c: correntisti.values())
			{
					System.out.println(c);
			}
		}
		else
			System.out.println("\t Nessun correntista in memoria principale.\n\n");
	}
	
	/**
	 * 
	 * 		- UC6 - [ Valutazione di una prestazione ]
	 * 
	 */
	
	public int contaPrestazioniComplete(String abilita, Correntista offerente)
	{
			int count = 0;
			
			for(Prestazione pr : prestazioni)
			{
					
					if(pr.getOfferente().getEmail().equals(offerente.getEmail()) && pr.getAbilita().equals(abilita) && pr.isComplete())
					{
							count++;
					}
			}
			
			return count;
	}
	
	public void valutaRichiesta(Prestazione pr, int feedback)
	{
					
			int count = contaPrestazioniComplete(pr.getAbilita(), pr.getOfferente());	
			
			pr.setCompleted(feedback,count);	
			
	}
	
	/**
	 * 
	 * 		- UC7 - [ Login ]
	 * 
	 */
	
	public void login(String emailLogin, String password)
	{
		Correntista c;
		if( (c = correntisti.get(emailLogin)) != null )
		{
			if(password.equals(c.getPassword())) //login riuscito
			{
				Registered.getIstance().setLoggedUser(c); 
				Console.setSessione(Registered.getIstance());
			}
			else
				System.out.println("\n\tPassword Errata");
		}
		else
		{
			System.out.println("\n\t Login Fallito.");
		}
		
	}
	
	/**
	 * 
	 * 		- UC8 - [ Logout ]
	 * 
	 */
	
	public void logout()
	{
		Console.setSessione(Guest.getIstance());
	}
	
	
	//controlla se l'email inserita esiste nella lista dei performers
	public boolean emailExists(String email, List<Correntista> performers)
	{
		for(Correntista c: performers)
		{
			if(c.getEmail().equals(email))
				return true;
		}
		return false;
	}
	
	// metodo bruto per fake login
	public void putFakeLogin(Correntista c)
	{
		correntisti.put(c.getEmail(), c);
	}

	//METODI DI INTERFACCIA OBSERVER
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		Prestazione p = (Prestazione) arg0;
		
		if (p.isAccepted())
		{
			p.getOfferente().accredita(p.getCosto());
		}
		else
			p.getRichiedente().accredita( p.getCosto());
		
		//p.deleteObserver(this);
	}

	
	// SOLO PER TESTING
	public void putPrestazione(Prestazione p) 
	{
		prestazioni.add(p);
	}
	
	
}