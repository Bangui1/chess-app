package src;

import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.adapters.Adapter;
import src.factory.GameFactory;


public class ChessGameEngine implements GameEngine {

    private Game game;

    private final Adapter adapter = new Adapter();

    private final GameFactory factory = new GameFactory();

    public ChessGameEngine() {
        this.game = factory.createGame();
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        GetResult<Game, Boolean> result = game.move(movement);
        if(result.getErrorValue()) {
            return new InvalidMove("Invalid move");
        } else {
            Game resultGame = result.getOptional().get();
            this.game = resultGame;
            return new NewGameState(adapter.piecesToChessPieces(resultGame.getBoard().getPieces()), adapter.colorToPlayerColor(resultGame.getTurn()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(game.getBoard()), adapter.piecesToChessPieces(game.getBoard().getPieces()), PlayerColor.WHITE);
    }
}
