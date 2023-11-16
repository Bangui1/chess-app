package src.lan.client.listener;

import edu.austral.dissis.chess.gui.InvalidMove;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;
import src.lan.client.GameClient;

public class InvalidMoveListener implements MessageListener<InvalidMove> {
    private final GameClient gameClient;

    public InvalidMoveListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void handleMessage(@NotNull Message<InvalidMove> message) {
        gameClient.handleInvalidMove(message.getPayload());
    }
}
