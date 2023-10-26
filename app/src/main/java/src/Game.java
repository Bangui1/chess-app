package src;

import src.factory.GameFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {

    private final Color turn;

    private final List<Board> history;


    public Game(Color turn, List<Board> history){
        this.turn = turn;
        this.history = history;
    }


    public GetResult<Game, Boolean> move(Movement movement){
        Board board = getBoard();
        if (board.getPieces().containsKey(movement.getOrigin())){
            Piece piece = board.getPieces().get(movement.getOrigin());
            if (piece.getColor() == turn && piece.getValidator().isValid(board, movement)){
                Board newBoard = board.movePiece(movement);
                List<Board> newHistory = new ArrayList<>(history);
                newHistory.add(newBoard);
                return new GetResult<>(Optional.of(new Game(nextTurn(), newHistory)), false);
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
}
