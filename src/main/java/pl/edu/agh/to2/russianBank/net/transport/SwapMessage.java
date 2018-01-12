package pl.edu.agh.to2.russianBank.net.transport;

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

        if (handPosition != that.handPosition) return false;
        return wastePosition == that.wastePosition;
    }

    @Override
    public int hashCode() {
        int result = handPosition;
        result = 31 * result + wastePosition;
        return result;
    }

    @Override
    public String toString() {
        return "SwapMessage{" +
                "handPosition=" + handPosition +
                ", wastePosition=" + wastePosition +
                '}';
    }
}
