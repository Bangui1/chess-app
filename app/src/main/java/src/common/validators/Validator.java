package src.common.validators;

import src.common.Board;
import src.common.Movement;

import java.util.List;

public interface Validator {

    //tirar excepciones
    boolean isValid(List<Board> history, Movement movement);
}
