package src.lan.client.listener;

import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;
import src.lan.client.GameClient;

public class NewStateListener implements MessageListener<NewGameState> {
    private final GameClient gameClient;

    public NewStateListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void handleMessage(@NotNull Message<NewGameState> message) {
        gameClient.handleNewGameState(message.getPayload());
    }
}
