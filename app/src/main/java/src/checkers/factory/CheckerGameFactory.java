package src.checkers.factory;

import src.checkers.CheckersGame;
import src.checkers.mover.CanEatAgainMover;
import src.checkers.mover.DefaultCheckersMover;
import src.checkers.mover.MovingSamePieceMover;
import src.checkers.mover.PromotionMover;
import src.checkers.validators.CanEatAgainValidator;
import src.checkers.validators.GameOverValidator;
import src.checkers.validators.HasEatenValidator;
import src.common.BoardGame;
import src.common.Color;
import src.common.mover.SequenceMover;

import java.util.List;

public class CheckerGameFactory {
    private static final CheckerBoardFactory boardFactory = new CheckerBoardFactory();

    public BoardGame createGame() {
        return new BoardGame(
                Color.WHITE,
                Color.BLACK,
                List.of(boardFactory.createCheckersBoard()),
                new GameOverValidator(),
                new SequenceMover(
                        new MovingSamePieceMover(),
                        new DefaultCheckersMover(),
                        new PromotionMover(),
                        new CanEatAgainMover()
                )
                );
    }
}
