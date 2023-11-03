package src.checkers.factory;

import src.checkers.CheckersGame;
import src.checkers.validators.CanEatAgainValidator;
import src.checkers.validators.GameOverValidator;
import src.checkers.validators.HasEatenValidator;
import src.common.Color;

import java.util.List;

public class CheckerGameFactory {
    private static final CheckerBoardFactory boardFactory = new CheckerBoardFactory();

    public CheckersGame createGame() {
        return new CheckersGame(Color.WHITE, List.of(boardFactory.createCheckersBoard()), new HasEatenValidator(), new GameOverValidator(), new CanEatAgainValidator(new HasEatenValidator()));
    }
}
