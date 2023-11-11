package src.common.mover;

import src.common.BoardGame;
import src.common.GetResult;
import src.common.Movement;
import src.common.validators.Validator;

import java.util.Optional;

public class MoverWithValidator implements Mover{

    private final Validator validator;
    private final Mover mover;

    public MoverWithValidator(Validator validator, Mover mover) {
        this.validator = validator;
        this.mover = mover;
    }

    @Override
    public GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement) {
        GetResult<BoardGame, Boolean> result = mover.move(boardGame, movement);
        if (!result.getErrorValue()){
            if (validator.isValid(result.getOptional().get().getHistory(), movement)){
                return result;
            }
        }
        return new GetResult<>(Optional.of(boardGame), true);
    }
}
