package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import org.junit.Test;
import pl.edu.agh.to2.russianBank.game.Game;
import pl.edu.agh.to2.russianBank.game.GameState;
import pl.edu.agh.to2.russianBank.game.Player;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class MessageSerializerTest {
    @Test
    public void getTypeName() {
        assertEquals("helloWorld", MessageSerializer.getTypeName("HelloWorld"));
        assertEquals("sampleClass", MessageSerializer.getTypeName("SampleClassMessage"));
        assertEquals("test", MessageSerializer.getTypeName(new TestMessage()));
    }

    @Test
    public void serialize() {
        final MessageSerializer serial = new MessageSerializer();
        // language=JSON
        assertEquals("{\"test\":{\"field\":1234}}", serial.serialize(new TestMessage()));
    }

    @Test
    public void deserialize() {
        final MessageSerializer serial = new MessageSerializer()
                .withType(TestMessage.class);
        // language=JSON
        assertEquals(new TestMessage(3), serial.deserialize("{\"test\":{\"field\":3}}"));
    }

    @Test
    public void serializeAndDeserializeGameState() {
        final MessageSerializer serial = MessageSerializer.GLOBAL;
        final Player playerA = new Player("a");
        final Player playerB = new Player("b");
        final Game game = new Game(playerA, playerB);
        final GameState gs = new GameState(playerA, playerB);
        final StartGameMessage msg = new StartGameMessage(gs, game.getMoveController(), false);
        serial.deserialize(serial.serialize(msg));
//        assertEquals(msg, serial.deserialize(serial.serialize(msg)));
    }

    private class TestMessage extends Message {
        private int field;

        public TestMessage() {
            this(1234);
        }

        public TestMessage(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }

        @Override
        public void accept(MessageVisitor visitor) {
            throw new RuntimeException("not implemented");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestMessage that = (TestMessage) o;
            return field == that.field;
        }

        @Override
        public int hashCode() {
            return Objects.hash(field);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("field", field)
                    .toString();
        }
    }
}
