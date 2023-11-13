package src.common;

import src.common.mover.Mover;
import src.common.validators.Validator;

import java.util.List;
import java.util.Optional;

public class BoardGame {

    private final Color turn;
    private final Color previousTurn;
    private final List<Board> history;
    private final Validator winningValidator;
    private final Mover mover;


    public BoardGame(Color turn, Color previousTurn, List<Board> history, Validator winningValidator, Mover mover) {
        this.turn = turn;
        this.previousTurn = previousTurn;
        this.history = history;
        this.winningValidator = winningValidator;
        this.mover = mover;
    }

    public GetResult<BoardGame, Boolean> move(Movement movement){
        if (!checkPieceEmpty(movement.getOrigin())) return new GetResult<>(Optional.of(this), true);
        if (!checkTurn(movement.getOrigin())) return new GetResult<>(Optional.of(this), true);
        return mover.move(this, movement);
    }


    public Board getCurrentBoard(){
        return history.get(history.size() - 1);
    }

    public Color nextTurn() {
        return (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public Color getTurn() {
        return turn;
    }

    public List<Board> getHistory() {
        return history;
    }

    public Validator getWinningValidator() {
        return winningValidator;
    }

    public Mover getMover() {
        return mover;
    }

    private boolean checkPieceEmpty(Coordinate coordinate) {
        return getCurrentBoard().getPieces().containsKey(coordinate);
    }

    private boolean checkTurn(Coordinate coordinate){
        return getCurrentBoard().getPieces().get(coordinate).getColor()  == turn;
    }

    public Color getPreviousTurn(){
        return previousTurn;
    }
}
