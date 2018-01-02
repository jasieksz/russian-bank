package pl.edu.agh.to2.russianBank.net.server;

import pl.edu.agh.to2.russianBank.net.transport.Message;

public interface GameHandler {
    void onConnect(PlayerConnection player);

    void onClose(PlayerConnection player);

    void onMessage(PlayerConnection player, Message message);
}
