package src.validators;

import src.Board;
import src.Movement;

public class CompositeOrValidator implements Validator{

        private final Validator[] validators;

        public CompositeOrValidator(Validator... validators){
            this.validators = validators;
        }

        @Override
        public boolean isValid(Board board, Movement movement){
            for (Validator validator : validators){
                if (validator.isValid(board, movement)) return true;
            }
            return false;
        }
}
