package src.common.validators;

import src.common.Board;
import src.common.Movement;
import src.common.validators.Validator;

import java.util.List;

public class CompositeOrValidator implements Validator {

        private final Validator[] validators;

        public CompositeOrValidator(Validator... validators){
            this.validators = validators;
        }

        @Override
        public boolean isValid(List<Board> board, Movement movement){
            for (Validator validator : validators){
                if (validator.isValid(board, movement)) return true;
            }
            return false;
        }
}
