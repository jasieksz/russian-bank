package pl.edu.agh.to2.russianBank.net;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.WriteCallback;

import java.util.concurrent.CompletableFuture;

public final class JettyUtil {
    private JettyUtil() {
    }

    public static CompletableFuture<Void> sendStringByPromise(RemoteEndpoint remote, String text) {
        final CompletableFuture<Void> promise = new CompletableFuture<>();
        remote.sendString(text, new WriteCallback() {
            @Override
            public void writeSuccess() {
                promise.complete(null);
            }

            @Override
            public void writeFailed(Throwable ex) {
                promise.completeExceptionally(ex);
            }
        });
        return promise;
    }
}
