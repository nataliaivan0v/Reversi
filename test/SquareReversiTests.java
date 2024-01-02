import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import model.SquareReversi;
import model.Tile;

/**
 * Tests the SquareReversi class.
 */
public class SquareReversiTests {
  SquareReversi model = new SquareReversi();

  @Test
  public void testStartGame() {
    model.startGame();
    Assert.assertEquals(model.getTurn(), Tile.BLACK);
    Assert.assertThrows(IllegalStateException.class, () -> model.startGame());

    Assert.assertEquals(Tile.BLACK, model.getTileAt(3, 3));
    Assert.assertEquals(Tile.BLACK, model.getTileAt(4, 4));
    Assert.assertEquals(Tile.WHITE, model.getTileAt(3, 4));
    Assert.assertEquals(Tile.WHITE, model.getTileAt(4, 3));

    Assert.assertEquals(Tile.EMPTY, model.getTileAt(1, 1));
    Assert.assertEquals(Tile.EMPTY, model.getTileAt(5, 6));
  }

  @Test
  public void testPass() {
    model.startGame();
    model.makeMove(2, 4);
    model.pass();
    Assert.assertFalse(model.isGameOver());
    Assert.assertEquals(model.getTurn(), Tile.BLACK);
    model.pass();
    Assert.assertTrue(model.isGameOver());
    Assert.assertThrows(IllegalStateException.class, () -> model.getTurn());
  }

  @Test
  public void testGetWinnerAndScore() {
    model.startGame();
    Assert.assertEquals(2, model.getScoreOfMove(2, 4, Tile.BLACK));
    model.makeMove(2, 4);
    Assert.assertEquals(4, model.getScore(Tile.BLACK));
    Assert.assertEquals(1, model.getScore(Tile.WHITE));
    model.pass();
    model.pass();
    Assert.assertEquals(Tile.BLACK, model.getWinner());
  }

  @Test
  public void testGetSideLen() {
    model.startGame();
    Assert.assertEquals(8, model.getSideLen());
  }

  @Test
  public void testPlayerHasMove() {
    model.startGame();
    Assert.assertTrue(model.currentPlayerHasLegalMove());
    model.makeMove(2, 4);
    Assert.assertTrue(model.currentPlayerHasLegalMove());
  }

  @Test
  public void testGetNeighbors() {
    model.startGame();
    List<int[]> neighbors1 = model.getNeighbors(2, 2);
    Assert.assertEquals(8, neighbors1.size());

    List<int[]> neighbors4 = model.getNeighbors(10, 10);
    Assert.assertTrue(neighbors4.isEmpty());
  }

  @Test
  public void testCopy() {
    model.startGame();
    model.makeMove(2, 4);
    Tile[][] copy = model.copyGameBoard();
    Assert.assertEquals(Tile.BLACK, copy[2][4]);
    Assert.assertEquals(Tile.BLACK, copy[3][4]);
    Assert.assertEquals(Tile.BLACK, copy[4][4]);
  }

  @Test
  public void testIsLegalMove() {
    model.startGame();
    Assert.assertTrue(model.isLegalMove(2, 4));
    Assert.assertFalse(model.isLegalMove(-1, -1));
    Assert.assertFalse(model.isLegalMove(6, 7));
  }

}
