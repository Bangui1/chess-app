package src.validators;

import src.Board;
import src.Movement;

import java.util.List;

public class CompositeAndValidator implements Validator{

        private final Validator[] validators;

        public CompositeAndValidator(Validator... validators){
            this.validators = validators;
        }

        @Override
        public boolean isValid(List<Board> board, Movement movement){
            for (Validator validator : validators){
                if (!validator.isValid(board, movement)) return false;
            }
            return true;
        }

}
