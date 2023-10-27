package src;

import src.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {

    private final Color turn;

    private final List<Board> history;

    private final Validator winnerValidator;

    private final Validator checkValidator;


    public Game(Color turn, List<Board> history, Validator winnerValidator, Validator checkValidator){
        this.turn = turn;
        this.history = history;
        this.winnerValidator = winnerValidator;
        this.checkValidator = checkValidator;
    }


    public GetResult<Game, Boolean> move(Movement movement){
        Board board = getBoard();
        if (board.getPieces().containsKey(movement.getOrigin())){
            Piece piece = board.getPieces().get(movement.getOrigin());
            if (piece.getColor() == turn && piece.getValidator().isValid(history, movement)){
                Board newBoard = board.movePiece(movement);
                List<Board> newHistory = new ArrayList<>(history);
                newHistory.add(newBoard);
                return new GetResult<>(Optional.of(new Game(nextTurn(), newHistory, winnerValidator, checkValidator)), false);
            }
        }
        return new GetResult<>(Optional.of(this), true);
    }

    public Color nextTurn(){
        return (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public Color getTurn() {
        return turn;
    }

    public List<Board> getHistory() {
        return history;
    }

    public Board getBoard(){
        return history.get(history.size() - 1);
    }

    public Validator getWinnerValidator(){
        return this.winnerValidator;
    }

    public Validator getCheckValidator(){
        return this.checkValidator;
    }
}
