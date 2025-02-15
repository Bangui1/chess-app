package src.chess.validators;

import src.common.Board;
import src.common.Coordinate;
import src.common.Movement;
import src.common.Piece;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;

public class LMoveValidator implements Validator {

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if (movement.getOrigin().column() == movement.getDestination().column() && movement.getOrigin().row() == movement.getDestination().row()) return false;
        if (Math.abs(movement.getOrigin().column() - movement.getDestination().column()) == 2 && Math.abs(movement.getOrigin().row() - movement.getDestination().row()) == 1) return true;
        return Math.abs(movement.getOrigin().column() - movement.getDestination().column()) == 1 && Math.abs(movement.getOrigin().row() - movement.getDestination().row()) == 2;
    }
}
