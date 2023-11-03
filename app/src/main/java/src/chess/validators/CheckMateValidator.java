package src.chess.validators;

import src.common.*;
import src.common.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckMateValidator implements Validator {

    private final Validator checkValidator;

    public CheckMateValidator(Validator checkValidator) {
        this.checkValidator = checkValidator;
    }


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board previousBoard = history.get(history.size() - 2);
        Color currentPlayer = previousBoard.getPieces().get(movement.getOrigin()).getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();

        for (int row = 1; row <= currentBoard.getRows(); row ++){
            for (int column = 1; column <= currentBoard.getColumns(); column++){
                Coordinate currentDestination = new Coordinate(column, row);
                for(Map.Entry<Coordinate, Piece> piece : pieces.entrySet()){
                    Movement move = new Movement(piece.getKey(), currentDestination);
                    if (piece.getValue().getColor() == currentPlayer && piece.getValue().getValidator().isValid(history, move)) {
                        Board newBoard = currentBoard.movePiece(move);
                        List<Board> newHistory = new ArrayList<>(history);
                        newHistory.add(newBoard);
                        if (checkValidator.isValid(newHistory, move)) return false;
                    }
                }
            }
        }
        return true;
    }
}
