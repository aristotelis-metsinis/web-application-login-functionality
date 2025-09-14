package com.consoleconnect.pccwglobal.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Utility class for working with JSON files and objects.
 *
 * This class provides convenient methods to:
 * - Read a JSON file into a Map
 * - Convert a Map back into a JSON string
 *
 * Internally, it uses Jackson's ObjectMapper for parsing and serialization.
 *
 * Author: QA Automation Team
 * Version: 1.0.0
 */
public class JsonUtils {
    /** Logger instance for debugging and tracking JSON operations. */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /** Shared ObjectMapper instance for JSON parsing and serialization. */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Reads a JSON file and converts it into a Map.
     *
     * @param filePath the path to the JSON file
     * @return a Map representation of the JSON content
     * @throws IOException if the file cannot be read or parsed
     */
    public static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
        logger.info("Reading JSON file from path: {}", filePath);
        Map<String, Object> jsonMap = mapper.readValue(new File(filePath), new TypeReference<>() {});
        logger.debug("Successfully parsed JSON file into Map: {}", jsonMap);
        return jsonMap;
    }

    /**
     * Converts a Map into a formatted JSON string.
     *
     * @param map the Map to convert
     * @return a JSON string representation of the map
     * @throws IOException if conversion fails
     */
    public static String toJsonString(Map<String, Object> map) throws IOException {
        logger.info("Converting Map to JSON string");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        logger.debug("Generated JSON string: {}", jsonString);
        return jsonString;
    }
}