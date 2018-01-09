package pl.edu.agh.to2.russianBank;

import java.net.URI;
import java.net.URISyntaxException;

public final class Constants {
    private Constants() {
    }

    public final static URI SERVER_URI;

    static {
        try {
            SERVER_URI = new URI("ws://localhost:8666/game");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
