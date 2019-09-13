package com.qa.portal.common.dto;

public class CvStatusDto extends QaBaseDto {

    private Integer id;

    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "CvStatusDto{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
