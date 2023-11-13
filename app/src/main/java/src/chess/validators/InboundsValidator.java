package src.chess.validators;

import src.common.Board;
import src.common.Movement;
import src.common.validators.Validator;

import java.util.List;

public class InboundsValidator implements Validator {

    @Override
    public boolean isValid(List<Board> history, Movement movement){
        Board board = history.get(history.size() - 1);
        int originColumn = movement.getOrigin().column();
        int originRow = movement.getOrigin().row();
        int destinationColumn = movement.getDestination().column();
        int destinationRow = movement.getDestination().row();
        return originColumn > 0 && originColumn <= board.getColumns() &&
                originRow > 0 && originRow <= board.getRows() &&
                destinationColumn > 0 && destinationColumn <= board.getColumns() &&
                destinationRow > 0 && destinationRow <= board.getRows();
    }
}
