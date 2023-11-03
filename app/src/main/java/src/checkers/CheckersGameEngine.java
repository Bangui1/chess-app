package src.checkers;

import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.checkers.factory.CheckerGameFactory;
import src.common.GetResult;
import src.common.Movement;
import src.common.adapters.Adapter;

public class CheckersGameEngine implements GameEngine {

    private CheckersGame checkersGame;
    private final Adapter adapter = new Adapter();

    public CheckersGameEngine() {
        CheckerGameFactory factory = new CheckerGameFactory();
        this.checkersGame = factory.createGame();
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        GetResult<CheckersGame, Boolean> result = checkersGame.move(movement);
        if(result.getErrorValue()) {
            return new InvalidMove("Invalid move");
        } else {
            CheckersGame resultCheckersGame = result.getOptional().get();
            return getResult(movement, resultCheckersGame);
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(checkersGame.getBoard()), adapter.piecesToChessPieces(checkersGame.getBoard().getPieces()), PlayerColor.WHITE);
    }

    private MoveResult getResult(Movement movement, CheckersGame game){
        if(game.getGameOverValidator().isValid(game.getHistory(), movement)){
            return new GameOver(adapter.colorToPlayerColor(game.nextTurn()));
        }
        this.checkersGame = game;
        if (game.getCanEatAgainValidator().isValid(game.getHistory(), movement)){
            return new NewGameState(adapter.piecesToChessPieces(game.getBoard().getPieces()), adapter.colorToPlayerColor(game.getTurn()));
        }
        return new NewGameState(adapter.piecesToChessPieces(game.getBoard().getPieces()), adapter.colorToPlayerColor(game.nextTurn()));
    }
}
