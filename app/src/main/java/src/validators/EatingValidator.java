package src.validators;

import src.Board;
import src.Coordinate;
import src.Movement;
import src.Piece;

import java.util.Map;

public class EatingValidator implements Validator{

    @Override
    public boolean isValid(Board board, Movement movement){
        Map<Coordinate, Piece> pieces = board.getPieces();
        if (movement.getOrigin().equals(movement.getDestination())) return false;
        if (!pieces.containsKey(movement.getOrigin())) return false;
        if (!pieces.containsKey(movement.getDestination())) return false;
        return pieces.get(movement.getOrigin()).getColor() != pieces.get(movement.getDestination()).getColor();
    }
}
