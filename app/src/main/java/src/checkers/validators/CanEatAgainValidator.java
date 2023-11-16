package src.checkers.validators;

import src.common.Board;
import src.common.Coordinate;
import src.common.Movement;
import src.common.Piece;
import src.common.validators.Validator;

import java.util.ArrayList;
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
        List<Coordinate> movesToEat = movesToEat(pieceOrigin);
        for (Coordinate eatCoordinate : movesToEat){
                Movement eatMovement = new Movement(pieceOrigin, eatCoordinate);
                if (eatValidator.isValid(history, eatMovement) && piece.getValidator().isValid(history, eatMovement)) return true;
        }
        return false;
    }

    private List<Coordinate> movesToEat(Coordinate current){
        List<Coordinate> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() - 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() - 2));
        return possibleMoves;
    }
}
