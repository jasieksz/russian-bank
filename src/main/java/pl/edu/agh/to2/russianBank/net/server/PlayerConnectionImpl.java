package pl.edu.agh.to2.russianBank.net.server;

import com.google.common.base.MoreObjects;
import io.javalin.embeddedserver.jetty.websocket.WsSession;
import pl.edu.agh.to2.russianBank.net.JettyUtil;
import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.net.transport.MessageSerializer;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class PlayerConnectionImpl implements PlayerConnection {
    private final int id;
    private final WsSession session;

    private String name = null;

    public PlayerConnectionImpl(int id, WsSession session) {
        this.id = id;
        this.session = session;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name != null ? name : "<unknown " + id + ">";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public CompletableFuture<Void> sendMessage(Message message) {
        final String json = MessageSerializer.GLOBAL.serialize(message);
        return JettyUtil.sendStringByPromise(session.getRemote(), json);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}
