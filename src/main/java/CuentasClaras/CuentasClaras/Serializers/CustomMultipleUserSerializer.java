package CuentasClaras.CuentasClaras.Serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import CuentasClaras.CuentasClaras.Modelos.MultipleUser;

public class CustomMultipleUserSerializer extends JsonSerializer<MultipleUser>{
	
	@Override
    public void serialize(MultipleUser mu, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        
        jsonGenerator.writeNumberField("id", mu.getId());
        jsonGenerator.writeNumberField("amount", mu.getAmount());
        
        jsonGenerator.writeNumberField("id_user", mu.getUserOwner().getId());
        jsonGenerator.writeStringField("name", mu.getUserOwner().getName());
        jsonGenerator.writeStringField("lastName", mu.getUserOwner().getLastName());
        jsonGenerator.writeStringField("userName", mu.getUserOwner().getUserName());

        
        // Otra lógica de serialización de Friendship si es necesario

        jsonGenerator.writeEndObject();
    }

}
