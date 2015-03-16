package com.bancadeltempo;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class ReadIntTest {

	@Test
	public void testReadInt() 
	{
		ByteArrayInputStream in = new ByteArrayInputStream("-1 c 1".getBytes());
		ConsoleReader cr = ConsoleReader.getIstance(in);
		
		int current = cr.readInt();
		assertEquals(1, current);
		
	}

}
