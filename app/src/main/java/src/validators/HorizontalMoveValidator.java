package src.validators;

import src.*;

import java.util.List;
import java.util.Map;

public class HorizontalMoveValidator implements Validator {

    private final boolean right;

    public HorizontalMoveValidator(boolean right) {
        this.right = right;
    }

    @Override
    public boolean isValid(List<Board> board, Movement movement) {
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if (movement.getOrigin().row() != movement.getDestination().row()) return false;
        if (right && movement.getOrigin().column() >= movement.getDestination().column()) return false;
        int direction = (right) ? 1 : -1;
        Coordinate origin = new Coordinate(movement.getOrigin().column() + direction, movement.getOrigin().row());
        while (!origin.equals(movement.getDestination())){
            if (pieces.containsKey(origin)) return false;
            origin = new Coordinate(origin.column() + direction, origin.row());
        }
        return true;
    }

}
