package src.lan.server.listener;

import edu.austral.ingsis.clientserver.ServerConnectionListener;
import org.jetbrains.annotations.NotNull;
import src.lan.server.GameServer;

public class GameServerListener implements ServerConnectionListener {

    GameServer gameServer;

    public GameServerListener(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void handleClientConnection(@NotNull String s) {
        gameServer.handleNewGame();
    }

    @Override
    public void handleClientConnectionClosed(@NotNull String s) {

    }
}
