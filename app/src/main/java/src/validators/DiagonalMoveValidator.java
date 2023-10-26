package src.validators;

import src.*;

import java.util.List;
import java.util.Map;

public class DiagonalMoveValidator implements Validator{

    private final boolean forward;

    public DiagonalMoveValidator(boolean forward){
        this.forward = forward;
    }

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if (movement.getOrigin().column() == movement.getDestination().column() || movement.getOrigin().row() == movement.getDestination().row()) return false;
        int directionX = (movement.getOrigin().column() < movement.getDestination().column()) ? 1 : -1;
        int directionY = (movement.getOrigin().row() < movement.getDestination().row()) ? 1 : -1;
        if (forward && directionY == -1) return false;
        Coordinate origin = new Coordinate(movement.getOrigin().column() + directionX, movement.getOrigin().row() + directionY);
        while (!origin.equals(movement.getDestination())) {
            if (pieces.containsKey(origin)) return false;
            origin = new Coordinate(origin.column() + directionX, origin.row() + directionY);
        }
        return true;
    }
}
