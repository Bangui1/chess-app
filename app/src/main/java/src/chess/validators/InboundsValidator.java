package src.chess.validators;

import src.common.Board;
import src.common.Movement;
import src.common.validators.Validator;

import java.util.List;

public class InboundsValidator implements Validator {

    @Override
    public boolean isValid(List<Board> history, Movement movement){
        Board board = history.get(history.size() - 1);
        return movement.getOrigin().column() >= 0 && movement.getOrigin().column() < board.getRows() &&
                movement.getOrigin().row() >= 0 && movement.getOrigin().row() < board.getColumns() &&
                movement.getDestination().column() >= 0 && movement.getDestination().column() < board.getRows() &&
                movement.getDestination().row() >= 0 && movement.getDestination().row() < board.getColumns();
    }
}
