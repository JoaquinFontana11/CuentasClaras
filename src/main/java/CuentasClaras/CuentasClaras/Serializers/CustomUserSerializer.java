package CuentasClaras.CuentasClaras.Serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import CuentasClaras.CuentasClaras.Modelos.User;

public class CustomUserSerializer extends JsonSerializer<User>{
	
	@Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        
        jsonGenerator.writeNumberField("id_user", user.getId());
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeStringField("lastName", user.getLastName());
        jsonGenerator.writeStringField("userName", user.getUserName());

        
        // Otra lógica de serialización de Friendship si es necesario

        jsonGenerator.writeEndObject();
    }

}