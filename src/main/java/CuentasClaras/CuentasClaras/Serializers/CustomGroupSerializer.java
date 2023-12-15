package CuentasClaras.CuentasClaras.Serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import CuentasClaras.CuentasClaras.Modelos.Group;

public class CustomGroupSerializer extends JsonSerializer<Group>{
	
	@Override
    public void serialize(Group group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        
        jsonGenerator.writeNumberField("id", group.getId());
        jsonGenerator.writeStringField("name", group.getName());
        jsonGenerator.writeObjectField("category", group.getCategory());

        
        // Otra lógica de serialización de Friendship si es necesario

        jsonGenerator.writeEndObject();
    }

}
