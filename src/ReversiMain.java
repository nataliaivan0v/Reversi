import controller.ReversiController;
import model.HumanPlayer;
import model.Player;
import model.Reversi;
import model.PlayerCreator;
import model.ReversiModel;
import model.Tile;
import strategy.AvoidCellsNextToCorners;
import strategy.CaptureMostPieces;
import strategy.GoForCorners;
import strategy.Strategy;
import view.ReversiGUIView;
import view.ReversiGraphics;

/**
 * Serves as the entry point for a Reversi game.
 */
public final class ReversiMain {

  /**
   * Entry point for a Reversi game.
   */
  public static void main(String[] args) {
    // minimum of 3 arguments in this order:
    // REQUIRED: model size, REQUIRED: player1 type, REQUIRED: player2 type, OPTIONAL: strategy
    if (args.length < 3) {
      throw new IllegalArgumentException();
    }

    if (args.length == 4) {
      Reversi model = new ReversiModel(Integer.parseInt(args[0]));
      model.startGame();
      String strategy = args[3].toLowerCase();
      setStrategy(strategy);
      Player player1 = switchHelper(model, args[1].toLowerCase(), Tile.BLACK);
      Player player2 = switchHelper(model, args[2].toLowerCase(), Tile.WHITE);
      makeGame(model, player1, player2);
    } else if (args.length == 3) {
      Reversi model = new ReversiModel(Integer.parseInt(args[0]));
      model.startGame();
      Player player1 = new HumanPlayer(Tile.BLACK);
      Player player2 = new HumanPlayer(Tile.WHITE);
      makeGame(model, player1, player2);
    }
  }

  private static void makeGame(Reversi model, Player player1, Player player2) {
    ReversiGUIView view1 = new ReversiGraphics(model);
    ReversiGUIView view2 = new ReversiGraphics(model);
    ReversiController controller1 = new ReversiController(model, player1, view1);
    ReversiController controller2 = new ReversiController(model, player2, view2);
  }

  private static Strategy setStrategy(String string) {
    switch (string) {
      case "strategy1":
        return new GoForCorners();
      case "strategy2":
        return new AvoidCellsNextToCorners();
      case "strategy3":
        return new CaptureMostPieces();
      default:
        throw new IllegalArgumentException();
    }
  }

  private static Player switchHelper(Reversi model, String player, Tile color) {
    switch (player) {
      case "human":
        return PlayerCreator.create(PlayerCreator.PlayerType.Human, model, color);
      case "ai":
        return PlayerCreator.create(PlayerCreator.PlayerType.AI, model, color);
      default:
        throw new IllegalArgumentException();
    }
  }
}
