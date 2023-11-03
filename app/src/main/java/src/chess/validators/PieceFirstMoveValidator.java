package src.chess.validators;

import src.common.Board;
import src.common.Coordinate;
import src.common.Movement;
import src.common.Piece;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;

public class PieceFirstMoveValidator implements Validator {
    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Map<Coordinate, Piece> currentPieces = history.get(history.size()- 1).getPieces();
        int pieceId = currentPieces.get(movement.getOrigin()).getId();
        Coordinate initialCoordinate = history.get(0).getCoordinateByPieceId(pieceId);
        for (Board board : history){
            if (board.getCoordinateByPieceId(pieceId) != initialCoordinate) return false;
        }
        return true;
    }
}
