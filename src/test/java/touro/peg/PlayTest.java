package touro.peg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayTest {

    Play play = new Play();
    TriangleBoard board = new TriangleBoard();

    //remove once TriangleBoard code is merged
    @BeforeEach
    void beforeEachTest(){
        for (int i = 0; i < 15; i++) {
            board.setPeg(i, i != 0);
        }
    }

    @Test
    void move_isValid() {
        //given

        //when
        play.move(board, new Move(3,1,0));

        //then
        assertTrue(board.getPegs()[0]);
        assertFalse(board.getPegs()[1]);
        assertFalse(board.getPegs()[3]);
    }

    @Test
    void move_isNotValid() {
        //given

        //when
        play.move(board, new Move(4,1,0));

        //then
        assertFalse(board.getPegs()[0]);
        assertTrue(board.getPegs()[1]);
        assertTrue(board.getPegs()[4]);
    }

    @Test
    void isValidMove_possible() { //correct boolean values for given indices
        //given
        board.setPeg(0, true);
        board.setPeg(1, true);
        board.setPeg(3, false);

        Move validMove = new Move(0,1,3);
        Move invalidMove = new Move(3,1,0);

        //when
        boolean valid = play.isValidMove(board, validMove);
        boolean invalid = play.isValidMove(board, invalidMove);

        //then
        assertTrue(valid);
        assertFalse(invalid);
    }

    @Test
    void isValidMove_legal() { //correct combination of given indices
        //given
        Move validMove = new Move(3,1,0);
        Move invalidMove = new Move(0,1,4);
        Move invalidMove2 = new Move(0,1,3);

        //when
        boolean valid = play.isValidMove(board, validMove);
        boolean invalid = play.isValidMove(board, invalidMove);
        boolean invalid2 = play.isValidMove(board, invalidMove2);

        //then
        assertTrue(valid);
        assertFalse(invalid);
        assertFalse(invalid2);
    }
}