package src.validators;

import src.Board;
import src.Movement;

import java.util.List;

public interface Validator {

    //tirar excepciones
    boolean isValid(List<Board> history, Movement movement);
}
