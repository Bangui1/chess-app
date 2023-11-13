package src.checkers.mover;

import src.common.*;
import src.common.mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultCheckersMover implements Mover {


    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard();
        Piece piece = board.getPieces().get(movement.getOrigin());
        if (!piece.getValidator().isValid(boardGame.getHistory(), movement)) return new GetResult<>(Optional.of(boardGame), true);
        Board newBoard = executeMovement(board, piece, movement);
        List<Board> newHistory = new ArrayList<>(boardGame.getHistory());
        newHistory.add(newBoard);
        return new GetResult<>(Optional.of(new BoardGame(boardGame.getTurn(), boardGame.getTurn(), newHistory, boardGame.getWinningValidator(), boardGame.getMover())), false);
    }


    private Board executeMovement(Board board, Piece piece, Movement movement){
        Board pieceMoved = board.movePiece(movement);
        if (board.getPieces().containsKey(getEatenCoordinate(movement))){
            return pieceMoved.removePiece(getEatenCoordinate(movement));
        }
        return pieceMoved;
    }


    public Coordinate getEatenCoordinate(Movement movement){
        int directionColumn = (movement.getOrigin().column() < movement.getDestination().column()) ? 1 : -1;
        int directionRow = (movement.getOrigin().row() < movement.getDestination().row()) ? 1 : -1;
        return new Coordinate(movement.getDestination().column() - directionColumn, movement.getDestination().row() - directionRow);
    }

}
