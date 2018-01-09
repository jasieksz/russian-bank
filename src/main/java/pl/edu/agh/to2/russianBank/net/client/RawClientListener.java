package pl.edu.agh.to2.russianBank.net.client;

import pl.edu.agh.to2.russianBank.net.transport.MessageVisitor;

public interface RawClientListener extends MessageVisitor {
    void onError(Throwable ex);
}
