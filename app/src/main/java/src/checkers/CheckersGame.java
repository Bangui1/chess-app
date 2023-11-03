package src.checkers;

import org.jetbrains.annotations.NotNull;
import src.checkers.validators.HasEatenValidator;
import src.common.*;
import src.common.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckersGame {

    private final Color turn;
    private final List<Board> history;
    private final HasEatenValidator eatenValidator;
    private final Validator gameOverValidator;
    private final Validator canEatAgainValidator;

    public CheckersGame(Color turn, List<Board> history, HasEatenValidator eatenValidator, Validator gameOverValidator, Validator canEatAgainValidator) {
        this.turn = turn;
        this.history = history;
        this.eatenValidator = eatenValidator;
        this.gameOverValidator = gameOverValidator;
        this.canEatAgainValidator = canEatAgainValidator;
    }

    public GetResult<CheckersGame, Boolean> move(Movement movement){
        Board board = getBoard();
        if (board.getPieces().containsKey(movement.getOrigin())){
            Piece piece = board.getPieces().get(movement.getOrigin());
            return getResult(movement, piece, board);
        }
        return new GetResult<>(Optional.of(this), true);
    }

    @NotNull
    private GetResult<CheckersGame, Boolean> getResult(Movement movement, Piece piece, Board board) {
        if (piece.getColor() == turn && piece.getValidator().isValid(history, movement)){
            Board newBoard = board.movePiece(movement);
            if(eatenValidator.isValid(history, movement)){
                newBoard = newBoard.removePiece(eatenValidator.getEatenCoordinate(piece, movement));
            }
            List<Board> newHistory = new ArrayList<>(history);
            newHistory.add(newBoard);
            return new GetResult<>(Optional.of(new CheckersGame(nextTurn(), newHistory, eatenValidator, gameOverValidator, canEatAgainValidator)), false);
        }
        return new GetResult<>(Optional.of(this), true);
    }


    public Color getTurn() {
        return turn;
    }

    public Color nextTurn(){
        return (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public List<Board> getHistory() {
        return history;
    }

    public Board getBoard(){
        return history.get(history.size() - 1);
    }

    public Validator getEatenValidator() {
        return eatenValidator;
    }

    public Validator getGameOverValidator() {
        return gameOverValidator;
    }

    public Validator getCanEatAgainValidator() {
        return canEatAgainValidator;
    }
}
