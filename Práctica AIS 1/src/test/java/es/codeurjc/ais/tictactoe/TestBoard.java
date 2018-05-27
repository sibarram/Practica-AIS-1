package es.codeurjc.ais.tictactoe;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBoard {
	Board mesa;
	String j1,j2;
	@Before
	public void setUp() throws Exception {
				this.mesa=new Board();
				this.j1="x";
				this.j2="o";
				mesa.enableAll();
				mesa.getCell(4).value=j1;
				mesa.getCell(0).value=j2;
				mesa.getCell(3).value=j1;
				mesa.getCell(1).value=j2;
			}

			@Test
			public void testVictoria_Jugador1() {
				mesa.getCell(5).value=j1;
				int[] esperado=new int[] {3,4,5};
				int[] marcador=mesa.getCellsIfWinner(j1);
				int[] marcador2=mesa.getCellsIfWinner(j2);
				assertEquals(false,mesa.checkDraw());
				assertNull(marcador2);
				assertThat(esperado ,equalTo(marcador));			
				}
			
			@Test
			public void testVictoria_Jugador2() {
				mesa.getCell(6).value=j1;
				mesa.getCell(2).value=j2;
				int[] esperado=new int[] {0,1,2};
				int[] marcador=mesa.getCellsIfWinner(j2);
				int[] marcador2=mesa.getCellsIfWinner(j1);
				assertEquals(false,mesa.checkDraw());
				assertNull(marcador2);
				assertThat(esperado,equalTo(marcador));			
				
				
				}
			@Test
			public void testEmpate() {
				mesa.getCell(2).value=j1;
				mesa.getCell(5).value=j2;
				mesa.getCell(6).value=j1;
				mesa.getCell(7).value=j2;
				mesa.getCell(8).value=j1;
				boolean marcadorB=mesa.checkDraw();
				assertEquals(true,marcadorB);

				
				}


	

}
