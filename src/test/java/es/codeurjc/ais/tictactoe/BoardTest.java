package es.codeurjc.ais.tictactoe;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;


import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;


@RunWith(Parameterized.class)
public class BoardTest {

    private static int WIN_X= 0;
    private static int WIN_O= 1;
    private static int DRAW = 2;

    @Parameters
    public static Collection<Object[]> data () {
        Object[][] values = {
                { new int[] { 0,3,1,4,2}, WIN_X },
                { new int[] { 0,3,1,4,8,5}, WIN_O },
                { new int[] { 4,0,7,1,2,6,3,5,8}, DRAW},
                { new int[] { 0,3,1,4,7,6,5,2,8}, WIN_O}, // Error because checkDraw returns true => checkDraw does not work when someone wins in the last move.
        };

        return Arrays.asList(values);
    }

    @Parameter(0) public int[] moves;
    @Parameter(1) public int operation;



    private Board board;
    private static String[] labels;

    @BeforeClass
    static public  void BeforeAll() {
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
    public void testSimpleGames() {

        // given an empty board -- see BeforeEach
        System.out.println(Arrays.toString(moves));
        System.out.println(operation);

        //when I play the given moves ...
        int index = 0;
        for (int move : moves) {

            TicTacToeGame.Cell cell = board.getCell(move);
            cell.value = labels[index];
            index = (index + 1) % (labels.length);
        }

        System.out.println(board.toString());

        //then one player should win or there should be draw
        if (operation == WIN_X) {
            System.out.println("WIN_X");
            System.out.println(Arrays.toString(board.getCellsIfWinner("X")));

            assertNotNull(board.getCellsIfWinner("X"));
            assertNull(board.getCellsIfWinner("O"));
            assertFalse(board.checkDraw());
        } else if (operation == WIN_O) {
            System.out.println("WIN_O");
            System.out.println(Arrays.toString(board.getCellsIfWinner("O")));

            assertNull(board.getCellsIfWinner("X"));
            assertNotNull(board.getCellsIfWinner("O"));
            assertFalse(board.checkDraw());
        } else {
            System.out.println("DRAW");
            System.out.println(Arrays.toString(board.getCellsIfWinner("X")));
            System.out.println(Arrays.toString(board.getCellsIfWinner("O")));

            assertTrue(board.checkDraw());
            assertNull(board.getCellsIfWinner("X"));
            assertNull(board.getCellsIfWinner("O"));
        }
    }
}
