package src.checkers.validators;

import src.common.Board;
import src.common.Coordinate;
import src.common.Movement;
import src.common.Piece;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;

public class CanEatAgainValidator implements Validator {

    private final Validator eatValidator;

    public CanEatAgainValidator(Validator eatValidator) {
        this.eatValidator = eatValidator;
    }


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        Coordinate pieceOrigin = movement.getDestination();
        Piece piece = pieces.get(pieceOrigin);
        for (int row = 1; row <= currentBoard.getRows(); row++){
            for (int column = 1; column <= currentBoard.getColumns(); column++){
                Movement eatMovement = new Movement(pieceOrigin, new Coordinate(column, row));
                if (eatValidator.isValid(history, eatMovement) && piece.getValidator().isValid(history, eatMovement)) return true;
            }
        }
        return false;
    }
}
