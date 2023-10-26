package src.validators;

import src.Board;
import src.Coordinate;
import src.Movement;
import src.Piece;

import java.util.List;
import java.util.Map;

public class NoFriendlyFireValidator implements Validator{


    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if(!pieces.containsKey(movement.getDestination())) return true;
        return pieces.get(movement.getOrigin()).getColor() != pieces.get(movement.getDestination()).getColor();
    }
}
