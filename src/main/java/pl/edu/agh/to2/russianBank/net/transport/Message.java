package pl.edu.agh.to2.russianBank.net.transport;

/**
 * Base class for all low-level client-server communication messages.
 *
 * <b>Don't forget to register new classes in {@link MessageSerializer#GLOBAL} instance and in {@link MessageVisitor}.</b>
 */
public abstract class Message {
    public abstract void accept(MessageVisitor visitor);
}

