package pl.edu.agh.to2.russianBank.net.client;

import pl.edu.agh.to2.russianBank.GUIApi;
import pl.edu.agh.to2.russianBank.net.UnsupportedMessageException;
import pl.edu.agh.to2.russianBank.net.transport.EndGameMessage;
import pl.edu.agh.to2.russianBank.net.transport.HelloMessage;
import pl.edu.agh.to2.russianBank.net.transport.MessageVisitor;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Higher level client API
 */
public class Client implements AutoCloseable {
    private final RawClient client;
    private final Visitor visitor = new Visitor();
    private final GUIApi gui;

    /**
     * @param client low level client instance, this object will take ownership of it
     * @param gui    GUI callback object
     */
    public Client(RawClient client, GUIApi gui) {
        this.client = client;
        this.gui = gui;

        this.client.addListener(visitor);
    }

    public static CompletableFuture<Client> connect(URI serverUri, GUIApi gui) throws Exception {
        return RawClientImpl.connect(serverUri)
                .thenApply((rc) -> new Client(rc, gui));
    }

    public CompletableFuture<Void> hello(String playerName) {
        return client.sendMessage(new HelloMessage(playerName));
    }

    /**
     * Waits for client connection to close.
     *
     * @param timeout the maximum time to wait
     * @param unit    the time unit of the {@code timeout} argument
     * @return {@code true} connection really closed or {@code false}
     * if the waiting time elapsed before closing
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public boolean awaitClose(int timeout, TimeUnit unit) throws InterruptedException {
        return client.awaitClose(timeout, unit);
    }

    @Override
    public void close() throws Exception {
        client.removeListener(visitor);
        client.close();
    }

    private class Visitor implements MessageVisitor {
        @Override
        public void visit(HelloMessage message) {
            throw new UnsupportedMessageException(message);
        }

        @Override
        public void visit(EndGameMessage message) {
            gui.endGame(message.getMessage());
        }
    }
}
