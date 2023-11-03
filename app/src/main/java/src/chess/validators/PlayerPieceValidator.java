package src.chess.validators;

import src.common.*;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;

public class PlayerPieceValidator implements Validator {

    private final Color color;

    public PlayerPieceValidator(Color color){
        this.color = color;
    }
    @Override
    public boolean isValid(List<Board> board, Movement movement) {
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if (movement.getOrigin().column() == movement.getDestination().column() && movement.getOrigin().row() == movement.getDestination().row()) return false;
        if (!pieces.containsKey(movement.getOrigin())) return false;
        return pieces.get(movement.getOrigin()).getColor() == this.color;
    }
}
