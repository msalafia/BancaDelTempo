package com.bancadeltempo;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class ReadCommandLoggedTest {

	//Controlla che vengano ritornate sempre le istanze del comando corretto in base
	//alle scelte dell'utente
	
	@Test
	public void testReadCommand() 
	{
		ByteArrayInputStream in = new ByteArrayInputStream("0 1 2 9".getBytes());
		ConsoleReader cr = ConsoleReader.getIstance(in);
		
		Command cmd;
		
		//test comando 0
		cmd = cr.readCommandLogged();
		assertThat(cmd, instanceOf(VisualizzaCorrentistiCommand.class));
		
		
		//test comando 1
		cmd = cr.readCommandLogged();
		assertThat(cmd, instanceOf(RichiediPrestazioneCommand.class));
		
		//test comando 2
		cmd = cr.readCommandLogged();
		assertThat(cmd, instanceOf(ConfermaPrestazioneCommand.class));
		
		//test comando 9
		cmd = cr.readCommandLogged();
		assertThat(cmd, instanceOf(ExitCommand.class));
		
	}

}
