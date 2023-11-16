package src.lan.server.listener;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import src.lan.server.GameServer;

public class GameListener implements GameEventListener {

    private GameServer server;

    public GameListener(GameServer server){
        this.server = server;
    }

    @Override
    public void handleMove(Move move){
        server.handleMove(move);
    }
}
