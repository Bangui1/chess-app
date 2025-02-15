package src.checkers.validators;

import src.common.Board;
import src.common.Coordinate;
import src.common.Movement;
import src.common.Piece;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;

public class DiagonalMoveValidator implements Validator {

    private final boolean forward;

    public DiagonalMoveValidator(boolean forward) {
        this.forward = forward;
    }

    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        if (!isDiagonal(movement)) return false;
        int directionRow = (movement.getOrigin().row() < movement.getDestination().row()) ? 1 : -1;
        return (forward && directionRow == 1) || (!forward && directionRow == -1);
    }

    private boolean isDiagonal(Movement movement) {
        return Math.abs(movement.getOrigin().column() - movement.getDestination().column()) == Math.abs(movement.getOrigin().row() - movement.getDestination().row());
    }
}
