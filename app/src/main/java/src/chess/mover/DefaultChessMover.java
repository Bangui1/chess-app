package src.chess.mover;

import src.common.*;
import src.common.mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultChessMover implements Mover {

    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard();
        Piece piece = board.getPieces().get(movement.getOrigin());
        if(piece.getColor() != boardGame.getTurn() || !piece.getValidator().isValid(boardGame.getHistory(), movement)) return new GetResult<>(Optional.of(boardGame), true);
        Board newBoard = board.movePiece(movement);
        List<Board> newHistory = new ArrayList<>(boardGame.getHistory());
        newHistory.add(newBoard);
        return new GetResult<>(Optional.of(new BoardGame(boardGame.nextTurn(), newHistory, boardGame.getWinningValidator(), boardGame.getMover())), false);
    }
}
