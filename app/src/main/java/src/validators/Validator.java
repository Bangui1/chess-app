package src.validators;

import src.Board;
import src.Movement;

public interface Validator {

    //tirar excepciones
    boolean isValid(Board board, Movement movement);
}
