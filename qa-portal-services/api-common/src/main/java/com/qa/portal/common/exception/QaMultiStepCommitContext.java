package com.qa.portal.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;

public class QaMultiStepCommitContext {

    private Object contextObject;

    private Integer stepNumber;

    private String serviceName;

    private Class<?> clazz;

    public QaMultiStepCommitContext(String serviceName, Object contextObject, Class<?> clazz, Integer stepNumber) {
        this.contextObject = contextObject;
        this.stepNumber = stepNumber;
        this.serviceName = serviceName;
        this.clazz = clazz;
    }

    public String getContextObjectJson() {
        return getJson();
    }

    public Class<?> getContextObjectType() {
        return clazz;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    private String getJson() {
        ObjectMapper om = new ObjectMapper();
        try {
            StringWriter sw = new StringWriter();
            om.writeValue(sw, contextObject);
            return sw.toString();
        }
        catch (Exception e) {
            throw new QaPortalBusinessException("Cannot get json for multi step commit context");
        }
    }
}
