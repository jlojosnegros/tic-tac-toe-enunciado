package es.codeurjc.ais.tictactoe;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


public class BoardTest {

    private Board board;
    private static String[] labels;

    @BeforeClass
    public  void BeforeAll() {
        labels = new String[2];
        labels[0] = "X";
        labels[1] = "O";
    }
    @Before
    public void BeforeEach() {
        this.board = new Board();
        this.board.enableAll();
    }

    @Test
    public void testSimpleWin() {

        int[] moves = { 0, 1, 2};

        //for (int[] winPos : winPositions)

        for(int move : moves) {
            TicTacToeGame.Cell cell = board.getCell(move);
            cell.value = "X";
        }

        assertNotNull(board.getCellsIfWinner("X"));
    }
}
