package es.codeurjc.ais.tictactoe;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;


public class TicTacToeGameTest {

    @Test
    public void test() {

        TicTacToeGame game = new TicTacToeGame();

        Connection connectionPlayerOne= mock(Connection.class);
        Connection connectionPlayerTwo= mock(Connection.class);

        game.addConnection(connectionPlayerOne);
        game.addConnection(connectionPlayerTwo);

        Player playerOne = new Player(1, "X", "player_one");
        Player playerTwo = new Player(2, "O", "player_two");

        game.addPlayer(playerOne);

        reset(connectionPlayerOne);
        reset(connectionPlayerTwo);

        game.addPlayer(playerTwo);

        verify(connectionPlayerOne).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(playerOne,playerTwo)));
        verify(connectionPlayerTwo).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(playerOne,playerTwo)));


        /// play

        game.mark(0); // X
        game.mark(3); // O
        game.mark(1); // X
        game.mark(4); // O
        game.mark(2); // X -- win

        ArgumentCaptor<TicTacToeGame.WinnerValue> argument = ArgumentCaptor.forClass(TicTacToeGame.WinnerValue.class);
        verify(connectionPlayerOne).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), argument.capture());
        TicTacToeGame.WinnerValue event = argument.getValue();

        assertThat(event, is(notNullValue()) );
        assertThat(event.player, is(playerOne));

    }
}
