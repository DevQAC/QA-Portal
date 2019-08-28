package com.qa.portal.cv.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cv_version")
public class CvVersion {

    @Id
    private Integer id;

    private String userName;

}
