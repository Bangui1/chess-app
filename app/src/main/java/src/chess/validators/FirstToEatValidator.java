package src.chess.validators;

import src.common.Board;
import src.common.Movement;
import src.common.validators.Validator;

import java.util.List;

public class FirstToEatValidator implements Validator {

    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board previousBoard = history.get(history.size() - 2);
        Board currentBoard = history.get(history.size() - 1);
        return previousBoard.getPieces().size() > currentBoard.getPieces().size();
    }
}
