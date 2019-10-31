package com.qa.portal.common.util.filemanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonPropertyUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(JsonPropertyUtil.class);

    public String getJsonContentForProperty(String propertyName, String jsonString) {
        try {
            final ObjectNode node = new ObjectMapper().readValue(jsonString, ObjectNode.class);
            return node.get(propertyName).textValue();
        }
        catch (Exception e) {
            LOGGER.error("Error accessing property from json string " + e.getMessage(), e);
            throw new QaPortalBusinessException("Error parsing JSON string");
        }
    }
}
