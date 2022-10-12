package br.com.brq.challenges.mercadinho.entrypoint.model.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class StringTrimModelRequest extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser value, DeserializationContext context) throws IOException {
        return StringUtils.isBlank(value.getValueAsString()) ? null : value.getValueAsString().trim();
    }
}
