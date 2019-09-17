package com.qa.portal.common.exception;

public class QaPortalMultiStepCommitException extends RuntimeException {

    private QaMultiStepCommitContext contextObject;

    public QaPortalMultiStepCommitException(QaMultiStepCommitContext contextObject, String message) {
        super(message);
        this.contextObject = contextObject;
    }

    public QaMultiStepCommitContext getContextObject() {
        return contextObject;
    }
}
