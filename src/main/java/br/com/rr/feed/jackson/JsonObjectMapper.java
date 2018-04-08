package br.com.rr.feed.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JsonObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -7948823447142483830L;

	public JsonObjectMapper() {
        this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        this.configure(SerializationFeature.INDENT_OUTPUT, true);
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
