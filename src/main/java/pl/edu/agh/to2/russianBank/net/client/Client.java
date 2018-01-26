package pl.edu.agh.to2.russianBank.net.client;

import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.net.UnsupportedMessageException;
import pl.edu.agh.to2.russianBank.net.transport.*;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Higher level client API
 */
public class Client implements AutoCloseable {
    private final RawClient client;
    private final Listener listener = new Listener();
    private final ClientCallbacks clientCallbacks;

    /**
     * @param client          low level client instance, this object will take ownership of it
     * @param clientCallbacks GUI callback object
     */
    public Client(RawClient client, ClientCallbacks clientCallbacks) {
        this.client = client;
        this.clientCallbacks = clientCallbacks;

        this.client.addListener(listener);
    }

    /**
     * Creates server connection.
     *
     * @param serverUri       server websocket endpoint URI
     * @param clientCallbacks object which responds to server messages
     * @return a promise with connection object
     * @throws Exception
     */
    public static CompletableFuture<Client> connect(URI serverUri, ClientCallbacks clientCallbacks) throws Exception {
        return RawClientImpl.connect(serverUri)
                .thenApply((rc) -> new Client(rc, clientCallbacks));
    }

    /**
     * Send hello message to server.
     *
     * @param playerName player name
     * @return a future which completes when message is sent (exits client machine).
     */
    public CompletableFuture<Void> hello(String playerName) {
        return client.sendMessage(new HelloMessage(playerName));
    }

    /**
     * Send move message to server.
     *
     * @param move move information
     * @return a future which completes when message is sent (exits client machine).
     */
    public CompletableFuture<Void> move(Move move) {
        return client.sendMessage(new MoveMessage(move));
    }

    /**
     * Send swap message to server.
     *
     * @param handPos
     * @param wastePos
     * @return a future which completes when message is sent (exits client machine).
     */
    public CompletableFuture<Void> swapHandWaste(int handPos, int wastePos) {
        return client.sendMessage(new SwapMessage(handPos, wastePos));
    }

    public CompletableFuture<Void> endGame(boolean won, String cause) {
        return client.sendMessage(new EndGameMessage(won, cause));
    }
    /**
     * End my turn.
     *
     * @return a future which completes when message is sent (exits client machine).
     */
    public CompletableFuture<Void> endTurn() {
        return client.sendMessage(new EndTurnMessage());
    }

    //TODO : add CompletableFuture ... WonGame(client.EndGame(true, "winning condition") @Karek Maput

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

    /**
     * Close server connection.
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        client.removeListener(listener);
        client.close();
    }

    private class Listener implements RawClientListener {
        @Override
        public void visit(HelloMessage message) {
            throw new UnsupportedMessageException(message);
        }

        @Override
        public void visit(EndGameMessage message) {
            clientCallbacks.endGame(message.isWon(), message.getCause());
        }

        @Override
        public void visit(MoveMessage message) {
            clientCallbacks.move(message.getMove());
        }

        @Override
        public void visit(StartGameMessage message) {
            clientCallbacks.startGame(message.getGameState(), message.getMoveController(), message.isMissaStart());
        }

        @Override
        public void visit(SwapMessage message) {
            System.out.println("VISIT SWAP");
            clientCallbacks.swap(message.getHandPosition(), message.getWastePosition());
        }

        @Override
        public void visit(EndTurnMessage message) {
            clientCallbacks.startTurn();
        }

        @Override
        public void onError(Throwable ex) {
            clientCallbacks.networkError(ex);
        }
    }
}
