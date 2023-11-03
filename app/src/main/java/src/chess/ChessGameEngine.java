package src.chess;

import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.common.GetResult;
import src.common.Movement;
import src.common.adapters.Adapter;
import src.chess.factory.GameFactory;


public class ChessGameEngine implements GameEngine {

    private ChessGame chessGame;

    private final Adapter adapter = new Adapter();

    public ChessGameEngine() {
        GameFactory factory = new GameFactory();
        this.chessGame = factory.createGame();
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        GetResult<ChessGame, Boolean> result = chessGame.move(movement);
        if(result.getErrorValue()) {
            return new InvalidMove("Invalid move");
        } else {
            ChessGame resultChessGame = result.getOptional().get();
            if (!resultChessGame.getCheckValidator().isValid(resultChessGame.getHistory(), movement)){
                return new InvalidMove("Checked");
            }
            if (resultChessGame.getWinnerValidator().isValid(resultChessGame.getHistory(), movement)) return new GameOver(adapter.colorToPlayerColor(resultChessGame.nextTurn()));
            this.chessGame = resultChessGame;
            return new NewGameState(adapter.piecesToChessPieces(resultChessGame.getBoard().getPieces()), adapter.colorToPlayerColor(resultChessGame.getTurn()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(chessGame.getBoard()), adapter.piecesToChessPieces(chessGame.getBoard().getPieces()), PlayerColor.WHITE);
    }
}
