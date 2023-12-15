package CuentasClaras.CuentasClaras.Serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import CuentasClaras.CuentasClaras.Modelos.Division;

public class CustomDivisionSerializer extends JsonSerializer<Division>{
	
	@Override
    public void serialize(Division division, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        
        jsonGenerator.writeNumberField("id", division.getId());
        jsonGenerator.writeNumberField("amount", division.getAmount());
        
        jsonGenerator.writeNumberField("id_user", division.getUserOwner().getId());
        jsonGenerator.writeStringField("name", division.getUserOwner().getName());
        jsonGenerator.writeStringField("lastName", division.getUserOwner().getLastName());
        jsonGenerator.writeStringField("userName", division.getUserOwner().getUserName());

        
        // Otra lógica de serialización de Friendship si es necesario

        jsonGenerator.writeEndObject();
    }

}