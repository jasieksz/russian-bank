package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.MoreObjects;

public class EndTurnMessage extends Message {
    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .toString();
    }
}
