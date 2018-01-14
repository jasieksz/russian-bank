package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Streams;
import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.game.command.Move;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (De)serializes message from/to JSON.
 * <p>
 * Requires registering message classes for proper working, see {@link #withType(Class)}.
 */
public final class MessageSerializer {
    /**
     * Serializer instance knowing all standard message types.
     */
    public static final MessageSerializer GLOBAL = new MessageSerializer()
            .withType(HelloMessage.class)
            .withType(StartGameMessage.class)
            .withType(EndGameMessage.class)
            .withType(MoveMessage.class)
            .withType(SwapMessage.class)
            .withType(EndTurnMessage.class);

    private final ImmutableMap<String, Type> typeMap;

    private final Gson innerGson = new GsonBuilder()
            .registerTypeAdapter(ObservableList.class, new ObservableListAdapter())
            .create();

    private final Gson icardSetGson = new GsonBuilder()
            .registerTypeHierarchyAdapter(ICardSet.class, new ICardSetTypeAdapter())
            .registerTypeAdapter(ObservableList.class, new ObservableListAdapter())
            .create();


    private final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Message.class, new MessageTypeAdapter())
            .registerTypeAdapter(ObservableList.class, new ObservableListAdapter())
            .create();

    /**
     * Constructs message serializer without any knowledge on message classes.
     */
    public MessageSerializer() {
        typeMap = ImmutableMap.of();
    }

    private MessageSerializer(MessageSerializer other, ImmutableMap<String, Type> typeMap) {
        this.typeMap = typeMap;
    }

    /**
     * Creates new message serializer and adds new message class to it.
     *
     * @param messageClass a class, inheriting from {@link Message}.
     * @return new message serializer instance.
     */
    public MessageSerializer withType(Class<? extends Message> messageClass) {
        final ImmutableMap<String, Type> newTypeMap = new ImmutableMap.Builder<String, Type>()
                .putAll(typeMap)
                .put(getTypeName(messageClass), messageClass)
                .build();
        return new MessageSerializer(this, newTypeMap);
    }

    /**
     * Serialize message to JSON.
     *
     * @throws IllegalArgumentException when message is not serializable.
     */
    public String serialize(Message message) {
        return gson.toJson(message);
    }

    /**
     * Deserializes message from JSON.
     *
     * @throws JsonParseException when JSON string is malformed.
     */
    public Message deserialize(String text) throws JsonParseException {
        return gson.fromJson(text, Message.class);
    }

    /**
     * Returns message type name (used in JSON format) for given message object.
     */
    public static String getTypeName(Message message) {
        return getTypeName(message.getClass());
    }

    /**
     * Returns message type name (used in JSON format) for given message class.
     */
    public static String getTypeName(Class<? extends Message> messageClass) {
        String name = messageClass.getSimpleName();
        return getTypeName(name);
    }

    /**
     * Processes Java class name for message class to message type name.
     * <p>
     * Currently it strips {@code Message} suffix and converts to camelCase, but this behaviour is not guaranteed.
     */
    public static String getTypeName(String className) {
        if (className.endsWith("Message")) {
            className = className.substring(0, className.length() - "Message".length());
        }
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, className);
    }

    private class MessageTypeAdapter implements JsonSerializer<Message>, JsonDeserializer<Message> {
        @Override
        public JsonElement serialize(Message src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject object = new JsonObject();
            object.add(getTypeName(src), icardSetGson.toJsonTree(src, typeOfSrc));
            return object;
        }

        @Override
        public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final JsonObject object = json.getAsJsonObject();
            final Set<String> rootProps = object.keySet();
            if (rootProps.size() != 1)
                throw new JsonParseException("Malformed message, expected root object to contain exactly one property.");
            final String typeName = rootProps.iterator().next();
            if (!typeMap.containsKey(typeName))
                throw new JsonParseException("Malformed message, unknown message type `" + typeName + "`.");
            final JsonElement data = object.get(typeName);
            return icardSetGson.fromJson(data, typeMap.get(typeName));
        }
    }

    private static class ObservableListAdapter implements JsonSerializer<ObservableList>, JsonDeserializer<ObservableList> {
        private final ImmutableBiMap<String, Type> typeMap = new ImmutableBiMap.Builder<String, Type>()
                .put("c", Card.class)
                .put("m", Move.class)
                .build();

        @Override
        public JsonElement serialize(ObservableList src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonArray array = new JsonArray(src.size());
            for (Object o : src) {
                final Class<?> klass = o.getClass();
                if (!typeMap.containsValue(klass))
                    throw new IllegalArgumentException(klass.getSimpleName() + " is forbidden in ObservableLists");
                final JsonObject obj = new JsonObject();
                obj.add(typeMap.inverse().get(klass), context.serialize(o, klass));
                array.add(obj);
            }
            return array;
        }

        @Override
        public ObservableList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final List<Object> array = Streams.stream(json.getAsJsonArray())
                    .map(jsonObject -> {
                        final JsonObject object = jsonObject.getAsJsonObject();
                        final Set<String> rootProps = object.keySet();
                        if (rootProps.size() != 1)
                            throw new JsonParseException("expected type object to contain exactly one property.");
                        final String typeName = rootProps.iterator().next();
                        if (!typeMap.containsKey(typeName))
                            throw new JsonParseException("unknown ObservableList item type `" + typeName + "`.");
                        final JsonElement data = object.get(typeName);
                        return context.deserialize(data, typeMap.get(typeName));
                    })
                    .collect(Collectors.toList());
            return FXCollections.observableList(array);
        }
    }

    private class ICardSetTypeAdapter implements JsonSerializer<ICardSet>, JsonDeserializer<ICardSet> {
        private final ImmutableBiMap<String, Type> typeMap = new ImmutableBiMap.Builder<String, Type>()
                .put("fo", Foundation.class)
                .put("ha", Hand.class)
                .put("ho", House.class)
                .put("wa", Waste.class)
                .build();

        @Override
        public JsonElement serialize(ICardSet src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject object = new JsonObject();
            object.add(typeMap.inverse().get(typeOfSrc), innerGson.toJsonTree(src, typeOfSrc));
            return object;
        }

        @Override
        public ICardSet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final JsonObject object = json.getAsJsonObject();
            final Set<String> rootProps = object.keySet();
            if (rootProps.size() != 1)
                throw new JsonParseException("expected type object to contain exactly one property.");
            final String typeName = rootProps.iterator().next();
            if (!typeMap.containsKey(typeName))
                throw new JsonParseException("unknown ICardSet child type `" + typeName + "`.");
            final JsonElement data = object.get(typeName);
            return innerGson.fromJson(data, typeMap.get(typeName));
        }
    }
}
