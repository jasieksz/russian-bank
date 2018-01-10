package pl.edu.agh.to2.russianBank.net.transport;

public class EndGameMessage extends Message {
    private boolean won;
    private String cause;

    public EndGameMessage(boolean won, String cause) {
        this.won = won;
        this.cause = cause;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EndGameMessage that = (EndGameMessage) o;

        return cause != null ? cause.equals(that.cause) : that.cause == null;
    }

    @Override
    public int hashCode() {
        return cause != null ? cause.hashCode() : 0;
    }
}
