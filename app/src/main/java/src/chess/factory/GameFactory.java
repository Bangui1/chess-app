package src.chess.factory;

import src.chess.validators.FirstToEatValidator;
import src.common.Color;
import src.chess.ChessGame;
import src.chess.validators.CheckMateValidator;
import src.chess.validators.CheckValidator;

import java.util.List;

public class GameFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public ChessGame createGame() {
        return new ChessGame(Color.WHITE, List.of(boardFactory.createClassicBoard()), new CheckMateValidator(new CheckValidator()), new CheckValidator());
    }

    public ChessGame createFirstToEatGame() {
        return new ChessGame(Color.WHITE, List.of(boardFactory.createFirstToEatBoard()), new FirstToEatValidator(), new CheckValidator());
    }
}
