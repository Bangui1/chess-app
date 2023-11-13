package src.common;

import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.checkers.factory.CheckerGameFactory;
import src.chess.factory.GameFactory;
import src.common.adapters.Adapter;

public class GameEngineImpl implements GameEngine {

    private BoardGame boardGame;
    private final Adapter adapter = new Adapter();

    public GameEngineImpl(){
        CheckerGameFactory factory= new CheckerGameFactory();
        this.boardGame = factory.createGame();
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        GetResult<BoardGame, Boolean> result = boardGame.move(movement);
        if (result.getErrorValue()){
            return new InvalidMove("Invalid Move");
        }else {
            BoardGame resultGame = result.getOptional().get();
            if (resultGame.getWinningValidator().isValid(resultGame.getHistory(), movement))
                return new GameOver(adapter.colorToPlayerColor(resultGame.nextTurn()));
            this.boardGame = resultGame;
            return new NewGameState(adapter.piecesToChessPieces(resultGame.getCurrentBoard().getPieces()), adapter.colorToPlayerColor(resultGame.getTurn()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(boardGame.getCurrentBoard()), adapter.piecesToChessPieces(boardGame.getCurrentBoard().getPieces()), PlayerColor.WHITE);
    }
}
