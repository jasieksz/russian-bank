package pl.edu.agh.to2.russianBank.net.transport;

public interface MessageVisitor {
    void visit(HelloMessage message);

    void visit(EndGameMessage message);
}
