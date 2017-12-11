package pl.edu.agh.to2.russianBank.net.server;

import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    public static final int SERVER_PORT = 8666;
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        LOG.info("Starting Russian Bank server");

        Javalin.create()
                .port(SERVER_PORT)
                .ws("/game", new GameWS())
                .start();
    }
}
