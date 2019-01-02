package br.eti.arthurgregorio.jms.infrastructure.misc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Simple helper class to manipulate JSON values with Jackson the {@link ObjectMapper}
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 27/12/2017
 */
public final class JsonUtils {

    /**
     * Serialize a given object to a JSON value
     * 
     * @param <T> the type of the object
     * @param object the object to be serialized
     * @return the JSON string for the given object
     * @throws JsonProcessingException if any problem occurs
     */
    public static <T> String serialize(T object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
    
    /**
     * Deserialize a JSON string into a object
     * 
     * @param <T> the type of the returned object
     * @param json the JSON string
     * @param outputType the expected output type
     * @return the object of the type T
     * @throws IOException if any problem occurs
     */
    public static <T> T deserialize(String json, Class<T> outputType) throws IOException {
        return new ObjectMapper().readValue(json, outputType);
    }
    
    /**
     * Deserialize a JSON string into a object
     * 
     * @param <T> the type of the returned object
     * @param json the JSON string
     * @param outputType the expected output type
     * @return the object of the type T
     * @throws IOException if any problem occurs
     */
    public static <T> T deserialize(String json, TypeReference<T> outputType) throws IOException {
        return new ObjectMapper().readValue(json, outputType);
    }
}
