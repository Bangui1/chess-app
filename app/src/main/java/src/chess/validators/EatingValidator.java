package src.chess.validators;

import src.common.Board;
import src.common.Coordinate;
import src.common.Movement;
import src.common.Piece;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;

public class EatingValidator implements Validator {

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if (movement.getOrigin().equals(movement.getDestination())) return false;
        if (!pieces.containsKey(movement.getOrigin())) return false;
        if (!pieces.containsKey(movement.getDestination())) return false;
        return pieces.get(movement.getOrigin()).getColor() != pieces.get(movement.getDestination()).getColor();
    }
}
