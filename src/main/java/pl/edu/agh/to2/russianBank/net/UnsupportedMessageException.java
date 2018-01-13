package pl.edu.agh.to2.russianBank.net;

import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.net.transport.MessageSerializer;

/**
 * Thrown when this endpoint does not support given message types.
 */
public class UnsupportedMessageException extends RuntimeException {
    public UnsupportedMessageException(Message message) {
        super(formatMessage(message));
    }

    public UnsupportedMessageException(Message message, Throwable cause) {
        super(formatMessage(message), cause);
    }

    protected UnsupportedMessageException(Message message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(formatMessage(message), cause, enableSuppression, writableStackTrace);
    }

    private static String formatMessage(Message message) {
        return "Unsupported message type: " + MessageSerializer.getTypeName(message);
    }
}
