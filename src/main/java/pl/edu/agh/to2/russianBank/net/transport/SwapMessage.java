package pl.edu.agh.to2.russianBank.net.transport;

public class SwapMessage extends Message {
    // TODO : Implement this @Game
    private int handPosition;
    private int wastePosition;

    public  SwapMessage(int handPos, int wastePos){
        this.handPosition = handPos;
        this.wastePosition = wastePos;
    }

    @Override
    public void accept(MessageVisitor visitor) {

    }
}
