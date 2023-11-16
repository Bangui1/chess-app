package src.chess.mover;

import src.common.PieceType;
import src.chess.factory.PieceFactory;
import src.common.*;
import src.common.mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionMover implements Mover {

    private final static PieceFactory pieceFactory = new PieceFactory();


    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard();
        Piece piece = board.getPieces().get(movement.getOrigin());
        if (piece.getColor() != boardGame.getTurn() || !piece.getValidator().isValid(boardGame.getHistory(), movement))
            return new GetResult<>(Optional.of(boardGame), true);
        if (checkIfPawnCanPromote(board, movement)) return promotePawn(boardGame, movement);
        return new GetResult<>(Optional.of(boardGame), true);
    }

    private GetResult<BoardGame, Boolean> promotePawn(BoardGame boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard();
        Piece piece = board.getPieces().get(movement.getOrigin());
        Board newBoard = board.movePiece(movement);
        Board newBoard2 = piece.getColor() == Color.WHITE ? newBoard.addPiece(movement.getDestination(), pieceFactory.createWhiteQueen(piece.getId())) : newBoard.addPiece(movement.getDestination(), pieceFactory.createBlackQueen(piece.getId()));
        List<Board> newHistory = new ArrayList<>(boardGame.getHistory());
        newHistory.add(newBoard2);
        return new GetResult<>(Optional.of(new BoardGame(boardGame.nextTurn(), boardGame.getTurn(), newHistory, boardGame.getWinningValidator(), boardGame.getMover())), false);
    }

    private boolean checkIfPawnCanPromote(Board board, Movement movement){
        Piece piece = board.getPieces().get(movement.getOrigin());
        if(piece.getType() == PieceType.PAWN){
            if(piece.getColor() == Color.WHITE){
                return movement.getDestination().row() == board.getRows();
            }
            else{
                return movement.getDestination().row() == 1;
            }
        }
        return false;
    }


}
