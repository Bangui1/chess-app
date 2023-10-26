package src.validators;

import src.Board;
import src.Coordinate;
import src.Movement;
import src.Piece;

import java.util.Map;

public class LMoveValidator implements Validator{

    @Override
    public boolean isValid(Board board, Movement movement){
        Map<Coordinate, Piece> pieces = board.getPieces();
        if (movement.getOrigin().column() == movement.getDestination().column() && movement.getOrigin().row() == movement.getDestination().row()) return false;
        if (Math.abs(movement.getOrigin().column() - movement.getDestination().column()) == 2 && Math.abs(movement.getOrigin().row() - movement.getDestination().row()) == 1) return true;
        return Math.abs(movement.getOrigin().column() - movement.getDestination().column()) == 1 && Math.abs(movement.getOrigin().row() - movement.getDestination().row()) == 2;
    }
}
