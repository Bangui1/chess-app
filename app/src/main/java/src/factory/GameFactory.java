package src.factory;

import src.Color;
import src.Game;
import src.validators.CheckMateValidator;

import java.util.List;

public class GameFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public Game createGame() {
        return new Game(Color.WHITE, List.of(boardFactory.createClassicBoard()), new CheckMateValidator());
    }
}
