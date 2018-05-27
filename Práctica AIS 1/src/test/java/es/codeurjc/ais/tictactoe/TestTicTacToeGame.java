package es.codeurjc.ais.tictactoe;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.hamcrest.MockitoHamcrest;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

public class TestTicTacToeGame {

	TicTacToeGame juego;
	Connection c1 , c2;
	Player j1 , j2;
		@Before
		public void setUp() {
			this.juego=new TicTacToeGame();
			this.c1=mock(Connection.class);
			this.c2=mock(Connection.class);
			juego.addConnection(c1);
			juego.addConnection(c2);
			this.j1=new Player(1,"X","Jugador 1");
			this.j2=new Player(2,"O","Jugador 2");
			juego.addPlayer(j1);
			reset(c1);
			reset(c2);
			juego.addPlayer(j2);

			verify(c1).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(j1,j2)));
			verify(c2).sendEvent(eq(EventType.JOIN_GAME),MockitoHamcrest.argThat(hasItems(j1,j2)));
			
			
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j1));
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j1));
			juego.mark(3);	
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j2));
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j2));
			
			reset(c1);
			reset(c2);
			
			juego.mark(0);
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j1));
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j1));
			
			reset(c1);
			reset(c2);
			
			juego.mark(4);
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j2));
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j2));
			
			reset(c1);
			reset(c2);
			
			juego.mark(1);
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j1));
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j1));
			
			reset(c1);
			reset(c2);
			
			
			
		}

		@Test
		public void Test_Victoria_Jugador1() {
			
			juego.mark(5);
			ArgumentCaptor<WinnerValue> ac = ArgumentCaptor.forClass(WinnerValue.class);
			verify(c1).sendEvent(eq(EventType.GAME_OVER), ac.capture());
			verify(c2).sendEvent(eq(EventType.GAME_OVER), ac.capture());
			assertNotNull(ac.getValue().pos);
			assertThat(ac.getValue().player, is(not(j2)));
			assertThat(ac.getValue().player, is(j1));
		}
		@Test
		public void Test_Victoria_Jugador2() {
			
			juego.mark(6);
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j2));
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j2));
			reset(c1);
			reset(c2);
			juego.mark(2);
			ArgumentCaptor<WinnerValue> ac = ArgumentCaptor.forClass(WinnerValue.class);
			verify(c1).sendEvent(eq(EventType.GAME_OVER), ac.capture());
			verify(c2).sendEvent(eq(EventType.GAME_OVER), ac.capture());
			assertNotNull(ac.getValue().pos);
			assertThat(ac.getValue().player, is(not(j1)));
			assertThat(ac.getValue().player, is(j2));
		}
		@Test
		public void Test_Empate() {
			
			juego.mark(2);
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j2));
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j2));
			reset(c1);
			reset(c2);
			juego.mark(5);
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j1));
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j1));
			reset(c1);
			reset(c2);
			juego.mark(6);
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j2));
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j2));
			reset(c1);
			reset(c2);
			juego.mark(7);
			verify(c1).sendEvent(eq(EventType.SET_TURN),eq(j1));
			verify(c2).sendEvent(eq(EventType.SET_TURN),eq(j1));
			reset(c1);
			reset(c2);
			juego.mark(8);
			ArgumentCaptor<WinnerValue> ac = ArgumentCaptor.forClass(WinnerValue.class);
			verify(c1).sendEvent(eq(EventType.GAME_OVER), ac.capture());
			verify(c2).sendEvent(eq(EventType.GAME_OVER), ac.capture());
			assertNull(ac.getValue());
		}
}
