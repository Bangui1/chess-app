package src.common.mover;

import src.common.BoardGame;
import src.common.GetResult;
import src.common.Movement;

public class SequenceMover implements Mover{

    private final Mover[] movers;

    public SequenceMover(Mover... movers){
        this.movers = movers;
    }

    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        GetResult<BoardGame, Boolean> result = movers[0].move(boardGame, movement);
        if (result.getErrorValue()) return result;
        for (int i = 1; i < movers.length; i++) {
            Mover mover = movers[i];
            result = mover.move(result.getOptional().get(), movement);
            if (result.getErrorValue()) return result;
        }
        return result;
    }
}
