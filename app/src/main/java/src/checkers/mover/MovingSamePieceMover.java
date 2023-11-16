package src.checkers.mover;

import src.checkers.validators.HasEatenValidator;
import src.common.*;
import src.common.mover.Mover;
import src.common.validators.Validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovingSamePieceMover implements Mover {


    private final static Validator hasEatenValidator = new HasEatenValidator();


    private boolean isMovingSamePiece(List<Board> history, Movement movement){
        Board currentBoard = history.get(history.size() - 1);
        Board previousBoard = history.get(history.size() - 2);
        int pieceId = currentBoard.getPieces().get(movement.getOrigin()).getId();
        Map<Coordinate, Piece> previousPieces = previousBoard.getPieces();
        for (Map.Entry<Coordinate,Piece> piece : previousPieces.entrySet()){
            if (piece.getValue().getId() == pieceId && !piece.getKey().equals(movement.getOrigin())){
                return hasEatenValidator.isValid(history, movement);
            }
        }
        return false;
    }

    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        if (boardGame.getPreviousTurn() != boardGame.getTurn())
            return new GetResult<>(Optional.of(boardGame), false);
        if (!isMovingSamePiece(boardGame.getHistory(), movement))
            return new GetResult<>(Optional.of(boardGame), true);
        return new GetResult<>(Optional.of(boardGame), false);
    }
}
