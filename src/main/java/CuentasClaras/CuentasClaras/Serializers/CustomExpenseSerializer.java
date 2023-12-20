package CuentasClaras.CuentasClaras.Serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import CuentasClaras.CuentasClaras.Modelos.Expense;

public class CustomExpenseSerializer extends JsonSerializer<Expense> {

	@Override
	public void serialize(Expense expense, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id_expense", expense.getId());
		jsonGenerator.writeBooleanField("expense_isReccurent", expense.isRecurrent());
		jsonGenerator.writeStringField("expense_recurrency", expense.getRecurrency());
		jsonGenerator.writeNumberField("expense_cantRecurrency", expense.getCantRecurrency());

		jsonGenerator.writeEndObject();
	}
}
