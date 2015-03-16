package com.bancadeltempo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class RegExpTest 
{
	
	Pattern emailPattern= Pattern.compile("[^@.].+@[^@.].+\\..+[^@.]");
	
	// due caratteri almeno primo della @ diversi da "." e "@"
	// due caratteri almeno dopo la @ ad esclusione di due @ attaccate o del punto
	// almeno due caratteri dopo il punto e che non finiscano per @ o per .
	
	
	/**
	 * Tutte le combinazioni {stringa, booleano} verrano usate per costruire un'istanza di RegExpTest e di volta in volta
	 * il test verrà avviato testando ognuna delle combinazioni.
	 * 
	 */

	@Parameters(name = "{index}: validEmail({0}) = {1}")
	public static Iterable data() {
		
	
	        return Arrays.asList(
	        		new Object[][] 
	        		{
	        				
	        				{ "" , false }, // classe 1
	        				
	        				{ "abcdef" , false }, // classe 2
	        				
	        				{ "abcd@ef" , false }, // classe 3
	        				
	        				{ "abcde.fge.it" , false }, // classe 4
	        				
	        				{ "abcde@abcd" , false }, // classe 5
	        				
	        				{ "abcd.ef@ghilm" , false }, // classe 6
	        				
	        				{ "abcedf@efs.." , false }, // classe 7
	        				
	        				{ "abcdef@.com" , false }, // classe 8
	        				
	        				{ "@abcd@efg.ht" , false }, // classe 9
	        				
	        				{ ".aabcdefge@abcd.it" , false }, // classe 10
	        				
	        				{ "abcde@...it" , false }, // classe 11
	        				
	        				{ "abcde@aaaa.it@" , false }, // classe 12
	        				
	        				{ "abcdef@g.it" , false }, // classe 13
	        				
	        				{ "valid@email.it", true} // classe valida 1

	        		});
	}
	
	// creo delle variabili per oguna delle colonne del data set
	
	private String input; // colonna per la stringa
	private Boolean expectedToMatch; // colonna per il valore booleano
	
	// il costruttore aggionra di volta in volta il valore di ogni riga
	public RegExpTest(String input, boolean expectedToMatch) 
	{
	        this.input = input;
	        this.expectedToMatch = expectedToMatch;
	 }
	
	@Test
	public void test() 
	{
		Matcher matcher = emailPattern.matcher(input);
	        
	        assertEquals(matcher.matches(), expectedToMatch);
	        
	    }

}
