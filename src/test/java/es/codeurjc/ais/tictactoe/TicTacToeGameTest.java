package es.codeurjc.ais.tictactoe;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

public class TicTacToeGameTest {

    private TicTacToeGame game;
    private Player playerOne;
    private Player playerTwo;
    private Connection connectionPlayerOne;
    private Connection connectionPlayerTwo;

    @Before
    public void BeforeEach() {

        this.game = new TicTacToeGame();

        this.playerOne = new Player(1, "X", "player_one");
        this.playerTwo = new Player(2, "O", "player_two");

        this.connectionPlayerOne = mock(Connection.class);
        this.connectionPlayerTwo = mock(Connection.class);

        game.addConnection(connectionPlayerOne);
        game.addConnection(connectionPlayerTwo);

        game.addPlayer(playerOne);

        reset(connectionPlayerOne);
        reset(connectionPlayerTwo);

        game.addPlayer(playerTwo);
    }

    @Test
    public void testJoinGameEvent() {
        verify(connectionPlayerOne).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(playerOne, playerTwo)));
        verify(connectionPlayerTwo).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(playerOne, playerTwo)));
    }
        /// play

//        game.mark(0); // X
//        game.mark(3); // O
//        game.mark(1); // X
//        game.mark(4); // O
//        game.mark(2); // X -- win
//
//        ArgumentCaptor<TicTacToeGame.WinnerValue> argument = ArgumentCaptor.forClass(TicTacToeGame.WinnerValue.class);
//        verify(connectionPlayerOne).sendEvent(eq(TicTacToeGame.EventType.GAME_OVER), argument.capture());
//        TicTacToeGame.WinnerValue event = argument.getValue();
//
//        assertThat(event, is(notNullValue()) );
//        assertThat(event.player, is(playerOne));

}
