package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class SwapMessage extends Message {
    // TODO : Implement this @Game
    private int handPosition;
    private int wastePosition;

    public SwapMessage(int handPos, int wastePos) {
        this.handPosition = handPos;
        this.wastePosition = wastePos;
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }

    public int getHandPosition() {
        return handPosition;
    }

    public void setHandPosition(int handPosition) {
        this.handPosition = handPosition;
    }

    public int getWastePosition() {
        return wastePosition;
    }

    public void setWastePosition(int wastePosition) {
        this.wastePosition = wastePosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwapMessage that = (SwapMessage) o;
        return handPosition == that.handPosition &&
                wastePosition == that.wastePosition;
    }

    @Override
    public int hashCode() {

        return Objects.hash(handPosition, wastePosition);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("handPosition", handPosition)
                .add("wastePosition", wastePosition)
                .toString();
    }
}
