package src.checkers.mover;

import src.checkers.validators.CanEatAgainValidator;
import src.checkers.validators.HasEatenValidator;
import src.common.*;
import src.common.mover.Mover;

import java.util.List;
import java.util.Optional;

public class CanEatAgainMover implements Mover {

    private static final CanEatAgainValidator eatAgainValidator = new CanEatAgainValidator(new HasEatenValidator());

    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        if (!checkNumberOfPieces(boardGame.getHistory())) return new GetResult<>(Optional.of(new BoardGame(boardGame.nextTurn(), boardGame.getTurn(), boardGame.getHistory(), boardGame.getWinningValidator(), boardGame.getMover())), false);
        if (eatAgainValidator.isValid(boardGame.getHistory(), movement))
            return new GetResult<>(Optional.of(boardGame), false);
        return new GetResult<>(Optional.of(new BoardGame(boardGame.nextTurn(), boardGame.getTurn(), boardGame.getHistory(), boardGame.getWinningValidator(), boardGame.getMover())), false);
    }

    private boolean checkNumberOfPieces(List<Board> history){
        return history.get(history.size() - 1).getPieces().size()  < history.get(history.size() - 2).getPieces().size();
    }

}
