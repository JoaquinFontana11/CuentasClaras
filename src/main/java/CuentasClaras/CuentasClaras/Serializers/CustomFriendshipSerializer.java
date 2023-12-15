package CuentasClaras.CuentasClaras.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import CuentasClaras.CuentasClaras.Modelos.Friendship;

import java.io.IOException;

public class CustomFriendshipSerializer extends JsonSerializer<Friendship> {

    @Override
    public void serialize(Friendship friendship, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", friendship.getId());

        // Serializar el usuario y sus campos específicos
        jsonGenerator.writeNumberField("user_id", friendship.getUser().getId());
        jsonGenerator.writeStringField("userName", friendship.getUser().getUserName());
        jsonGenerator.writeStringField("name", friendship.getUser().getName());
        jsonGenerator.writeStringField("lastName", friendship.getUser().getLastName());
        jsonGenerator.writeStringField("email", friendship.getUser().getEmail());
        
        // Otra lógica de serialización de Friendship si es necesario

        jsonGenerator.writeEndObject();
    }
}