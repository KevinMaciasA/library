package com.kma.library.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class LanguageDeserializer extends JsonDeserializer<Language> {

    @Override
    public Language deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        switch (value.toLowerCase()) {
            case "en":
                return Language.ENGLISH;
            case "hi":
                return Language.HINDI;
            case "pt":
                return Language.PORTUGUESE;
            case "es":
                return Language.SPANISH;
            case "zh":
                return Language.MANDARIN;
            default:
                return Language.UNKNOWN;
            // throw new IllegalArgumentException("Unknown language: " + value);
        }
    }
}
