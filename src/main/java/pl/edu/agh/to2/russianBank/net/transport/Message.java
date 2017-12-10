package pl.edu.agh.to2.russianBank.net.transport;

import com.google.gson.Gson;

// TODO: Define this
public enum Message {
    HELLO;

    private static final Gson gson = new Gson();

    public String serialize() {
        return gson.toJson(this);
    }
}
