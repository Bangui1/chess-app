package src.chess.factory;

import src.chess.mover.DefaultChessMover;
import src.chess.mover.PromotionMover;
import src.chess.validators.FirstToEatValidator;
import src.common.BoardGame;
import src.common.Color;
import src.chess.validators.CheckMateValidator;
import src.chess.validators.CheckValidator;
import src.common.mover.CompositeOrMover;
import src.common.mover.MoverWithValidator;

import java.util.List;

public class GameFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public BoardGame createGame() {
        return new BoardGame(Color.WHITE, Color.BLACK, List.of(boardFactory.createClassicBoard()), new CheckMateValidator(new CheckValidator()), new MoverWithValidator(new CheckValidator(), new CompositeOrMover(new PromotionMover(), new DefaultChessMover())));
    }

    public BoardGame createFirstToEatGame() {
        return new BoardGame(Color.WHITE, Color.BLACK, List.of(boardFactory.createFirstToEatBoard()), new FirstToEatValidator(), new MoverWithValidator(new CheckValidator(), new CompositeOrMover(new PromotionMover(), new DefaultChessMover())));
    }
}
