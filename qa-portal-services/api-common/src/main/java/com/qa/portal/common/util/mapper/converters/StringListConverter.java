package com.qa.portal.common.util.mapper.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qa.portal.common.exception.QaPortalBusinessException;
import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StringListConverter extends DozerConverter<String, List> {

    private final Logger LOGGER = LoggerFactory.getLogger(StringListConverter.class);

    public StringListConverter() {
        super(String.class, List.class);
    }

    @Override
    public List<String> convertTo(String s, List list) {
        try {
            if (s == null || s.length() == 0) {
                return new ArrayList<>();
            }
            else {
                ObjectMapper objectMapper = new ObjectMapper();
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                return objectMapper.readValue(s, typeFactory.constructCollectionType(List.class, String.class));
            }
        }
        catch (Exception e) {
            throw new QaPortalBusinessException("Object mapping failure");
        }
    }

    @Override
    public String convertFrom(List list, String s) {
        try {
            if (list == null) {
                list = new ArrayList();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(list);
        }
        catch (Exception e) {
            throw new QaPortalBusinessException("Object mapping failure");
        }
    }
}
