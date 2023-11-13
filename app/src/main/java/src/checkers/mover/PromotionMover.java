package src.checkers.mover;

import src.checkers.factory.CheckerPieceFactory;
import src.chess.PieceType;
import src.common.*;
import src.common.mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionMover implements Mover {

    private final static CheckerPieceFactory pieceFactory = new CheckerPieceFactory();


    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        if (checkIfPieceCanPromote(boardGame.getCurrentBoard(), movement)){
            Board newBoard = promotePiece(boardGame.getCurrentBoard(), movement.getDestination());
            List<Board> newHistory = new ArrayList<>(boardGame.getHistory());
            newHistory.remove(newHistory.size() - 1);
            newHistory.add(newHistory.size() - 1, newBoard);
            return new GetResult<>(Optional.of(new BoardGame(boardGame.getTurn(), boardGame.getPreviousTurn(), newHistory, boardGame.getWinningValidator(), boardGame.getMover())), false);
        }
        else{
            return new GetResult<>(Optional.of(boardGame), false);
        }
    }

    private Board promotePiece(Board board, Coordinate coordinate){
        Piece piece = board.getPieces().get(coordinate);
        Board newBoard = board.removePiece(coordinate);
        if (piece.getColor() == Color.WHITE){
            newBoard = newBoard.addPiece(coordinate, pieceFactory.createWhiteKing(piece.getId()));
        }
        else{
            newBoard = newBoard.addPiece(coordinate, pieceFactory.createBlackKing(piece.getId()));
        }
        return newBoard;
    }

    private boolean checkIfPieceCanPromote(Board board, Movement movement){
        Piece piece = board.getPieces().get(movement.getDestination());
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
