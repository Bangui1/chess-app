package src.common.mover;

import src.common.Board;
import src.common.BoardGame;
import src.common.GetResult;
import src.common.Movement;

import java.util.List;

public interface Mover {

    GetResult<BoardGame, Boolean> move(BoardGame boardGame, Movement movement);
}
