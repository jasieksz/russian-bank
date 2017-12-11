package pl.edu.agh.to2.russianBank.net.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.net.transport.Message;

public class GameHandlerImpl implements GameHandler {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void onConnect(PlayerConnection player) {
        // TODO
        LOG.debug("Incoming player {}", player);
    }

    @Override
    public void onClose(PlayerConnection player) {
        // TODO
        LOG.debug("Closing player {}", player);
    }

    @Override
    public void onMessage(PlayerConnection player, Message message) {
        // TODO
        LOG.debug("Message from player {}: {}", player, message);
    }
}
