package com.ge.app.webcrawler;

import java.io.IOException;

import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
	public void testMain() throws InterruptedException, IOException{
		App.main(null);
	}
	
	@Test(expected=IOException.class)
	public void testMainWithNoInput() throws InterruptedException, IOException{
		App.INPUT_FILE = "testInput999.txt";
		App.main(null);
	}
	
}
