package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

public final class MessageSerializer {
    public static final MessageSerializer GLOBAL = new MessageSerializer()
            .withType(HelloMessage.class)
            .withType(EndGameMessage.class)
            .withType(MoveMessage.class)
            .withType(SwapMessage.class);

    private final ImmutableMap<String, Type> typeMap;

    private final Gson innerGson;

    private final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Message.class, new MessageTypeAdapter())
            .create();

    public MessageSerializer() {
        typeMap = ImmutableMap.of();
        innerGson = new Gson();
    }

    private MessageSerializer(MessageSerializer other, ImmutableMap<String, Type> typeMap) {
        this.typeMap = typeMap;
        this.innerGson = other.innerGson;
    }

    public MessageSerializer withType(Class<? extends Message> messageClass) {
        final ImmutableMap<String, Type> newTypeMap = new ImmutableMap.Builder<String, Type>()
                .putAll(typeMap)
                .put(getTypeName(messageClass), messageClass)
                .build();
        return new MessageSerializer(this, newTypeMap);
    }

    public String serialize(Message message) {
        return gson.toJson(message);
    }

    public Message deserialize(String text) throws JsonParseException {
        return gson.fromJson(text, Message.class);
    }

    public static String getTypeName(Message message) {
        return getTypeName(message.getClass());
    }

    public static String getTypeName(Class<? extends Message> messageClass) {
        String name = messageClass.getSimpleName();
        return getTypeName(name);
    }

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
            object.add(getTypeName(src), innerGson.toJsonTree(src, typeOfSrc));
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
            return innerGson.fromJson(data, typeMap.get(typeName));
        }
    }
}
