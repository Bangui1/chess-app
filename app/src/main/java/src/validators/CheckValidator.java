package src.validators;

import src.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// doesn't allow to move piece if its king is checked
public class CheckValidator implements Validator{


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> currentPieces = currentBoard.getPieces();
        Piece pieceToMove = currentPieces.get(movement.getOrigin());


        Board nextBoard = currentBoard.movePiece(movement);
        Map<Coordinate, Piece> newPieces = nextBoard.getPieces();

        Coordinate kingsCoordinate = nextBoard.getKingsCoordinate(pieceToMove.getColor());
        Color  enemyColor = pieceToMove.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;

        for(Map.Entry<Coordinate, Piece> piece : newPieces.entrySet()){
            Movement movementToKing = new Movement(piece.getKey(), kingsCoordinate);
            if(piece.getValue().getColor() == enemyColor && piece.getValue().getValidator().isValid(new ArrayList<>(List.of(nextBoard)), movementToKing)) return false;
        }

        return true;
    }
}
