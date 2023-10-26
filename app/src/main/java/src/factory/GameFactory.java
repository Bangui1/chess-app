package src.factory;

import src.Board;
import src.Color;
import src.Game;

import java.util.HashMap;
import java.util.List;

public class GameFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public Game createGame() {
        return new Game(Color.WHITE, List.of(boardFactory.createClassicBoard()));
    }
}
