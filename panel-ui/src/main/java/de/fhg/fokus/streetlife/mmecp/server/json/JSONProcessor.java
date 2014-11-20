package de.fhg.fokus.streetlife.mmecp.server.json;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingMessage;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.github.fge.jsonschema.util.JsonLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONProcessor {

	private String jSONString = "-1";
	String jSONSchema = "-1";

	public JSONProcessor(String jSONSchema) {
		setSchema(jSONSchema);
	}

	public void setSchema(String schema) {
		this.jSONSchema = schema;
	}

	public String getSchema() {
		return jSONSchema;
	}

	public String getjSONString() {
		return jSONString;
	}

	public void setjSONString(String jSONString) {
		this.jSONString = jSONString;
	}

	public static <T> T parse(String jSONExample, Class<T> myClass)
			throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
				.create();
		T eI = gson.fromJson(jSONExample, myClass);

		return eI;
	}

	public boolean validate(String jSONExample) {
		return validate(jSONExample, jSONSchema);
	}

	// Source:
	// http://stackoverflow.com/questions/14511468/java-android-validate-string-json-against-string-schema
	public boolean validate(String jsonData, String jsonSchema) {
		ProcessingReport report = null;
		boolean result = false;
		try {
			// System.out.println("Applying schema: @<@<" + jsonSchema
			// + ">@>@ to data: #<#<" + jsonData + ">#>#");
			JsonNode schemaNode = JsonLoader.fromString(jsonSchema);
			JsonNode data = JsonLoader.fromString(jsonData);
			JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
			JsonSchema schema = factory.getJsonSchema(schemaNode);
			report = schema.validate(data);
		} catch (JsonParseException jpex) {
			System.out
					.println("Error. Something went wrong trying to parse json data: #<#<"
							+ jsonData
							+ ">#># or json schema: @<@<"
							+ jsonSchema
							+ ">@>@. Are the double quotes included? "
							+ jpex.getMessage());
			// jpex.printStackTrace();
		} catch (ProcessingException pex) {
			System.out
					.println("Error. Something went wrong trying to process json data: #<#<"
							+ jsonData
							+ ">#># with json schema: @<@<"
							+ jsonSchema + ">@>@ " + pex.getMessage());
			// pex.printStackTrace();
		} catch (IOException e) {
			System.out
					.println("Error. Something went wrong trying to read json data: #<#<"
							+ jsonData
							+ ">#># or json schema: @<@<"
							+ jsonSchema + ">@>@");
			// e.printStackTrace();
		}
		if (report != null) {
			Iterator<ProcessingMessage> iter = report.iterator();
			while (iter.hasNext()) {
				ProcessingMessage pm = iter.next();
				System.out.println("Processing Message: " + pm.getMessage());
			}
			result = report.isSuccess();
		}
		System.out.println(" Result=" + result);
		return result;
	}

}
