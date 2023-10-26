package src.validators;

import src.Board;
import src.Movement;

public class InboundsValidator implements Validator{

    @Override
    public boolean isValid(Board board, Movement movement){
        return movement.getOrigin().column() >= 0 && movement.getOrigin().column() < board.getRows() &&
                movement.getOrigin().row() >= 0 && movement.getOrigin().row() < board.getColumns() &&
                movement.getDestination().column() >= 0 && movement.getDestination().column() < board.getRows() &&
                movement.getDestination().row() >= 0 && movement.getDestination().row() < board.getColumns();
    }
}
