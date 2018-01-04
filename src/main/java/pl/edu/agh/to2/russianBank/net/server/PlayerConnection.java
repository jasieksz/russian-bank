package pl.edu.agh.to2.russianBank.net.server;

import pl.edu.agh.to2.russianBank.net.transport.Message;

import java.util.concurrent.CompletableFuture;

/**
 * Uniquely describes player connection from server perspective.
 *
 * Implementations of this class must implement {@code equals} and {@code hashCode}
 * <b>based only on value of {@code id} property</b>.
 */
public interface PlayerConnection {
    /**
     * Get player id, which is unique per player session.
     */
    int getId();

    /**
     * Send message to player.
     *
     * @param message
     * @return Promise that completes when message is successfully delivered.
     */
    CompletableFuture<Void> sendMessage(Message message);
}
