package src.validators;

import src.*;

import java.util.List;
import java.util.Map;

public class CheckMateValidator implements Validator{
    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board previousBoard = history.get(history.size() - 2);
        Color currentPlayer = previousBoard.getPieces().get(movement.getOrigin()).getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();

        for (int row = 1; row <= currentBoard.getRows(); row ++){
            for (int column = 1; column <= currentBoard.getColumns(); column++){
                Coordinate currentDestination = new Coordinate(column, row);
                for(Map.Entry<Coordinate, Piece> piece : pieces.entrySet()){
                    if (piece.getValue().getColor() == currentPlayer && piece.getValue().getValidator().isValid(history, new Movement(piece.getKey(), currentDestination))) return false;
                }
            }
        }
        return true;
    }
}
