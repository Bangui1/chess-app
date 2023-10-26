package src.validators;

import src.Board;
import src.Coordinate;
import src.Movement;
import src.Piece;

import java.util.Map;

public class NotEatingValidator implements Validator{

    @Override
    public boolean isValid(Board board, Movement movement){
        Map<Coordinate, Piece> pieces = board.getPieces();
        return !pieces.containsKey(movement.getDestination());
    }
}
