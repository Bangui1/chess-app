package src.common.mover;

import src.common.BoardGame;
import src.common.GetResult;
import src.common.Movement;

import java.util.Optional;

public class CompositeOrMover implements Mover{

    private final Mover[] movers;

    public CompositeOrMover(Mover... movers) {
        this.movers = movers;
    }


    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        for(Mover mover : this.movers){
            GetResult<BoardGame, Boolean> result = mover.move(boardGame, movement);
            if (!result.getErrorValue()) return result;
        }
        return new GetResult<>(Optional.of(boardGame), true);
    }
}
