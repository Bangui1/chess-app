package src.checkers.validators;

import src.common.*;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;

public class GameOverValidator implements Validator {


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Color currentPlayer = getCurrentPlayer(history, movement.getOrigin());
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();

        if (checkEmptyPieces(pieces, currentPlayer)){
            return true;
        }

        for(int row = 1; row < currentBoard.getRows(); row++){
            for(int column = 1; column < currentBoard.getColumns(); column++){
                Coordinate currentDestination = new Coordinate(column, row);
                for(Map.Entry<Coordinate, Piece> piece : pieces.entrySet()){
                    Movement move = new Movement(piece.getKey(), currentDestination);
                    if (piece.getValue().getColor() == currentPlayer && piece.getValue().getValidator().isValid(history, move)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Color getCurrentPlayer(List<Board> history, Coordinate origin) {
        Board previousBoard = history.get(history.size() - 2);
        Map<Coordinate, Piece> pieces = previousBoard.getPieces();
        return pieces.get(origin).getColor();
    }

    private boolean checkEmptyPieces(Map<Coordinate,Piece> pieces, Color player){
        Color color = player == Color.WHITE ? Color.BLACK : Color.WHITE;
        for (Map.Entry<Coordinate,Piece> piece : pieces.entrySet()){
            if (piece.getValue().getColor() == color){
                return false;
            }
        }
        return true;
    }
}
