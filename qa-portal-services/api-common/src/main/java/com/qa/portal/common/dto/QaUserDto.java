package com.qa.portal.common.dto;

import java.util.Objects;

public class QaUserDto extends QaBaseDto {
    private Integer id;

    private String userName;

    private QaUserDto reviewer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public QaUserDto getReviewer() {
        return reviewer;
    }

    public void setReviewer(QaUserDto reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QaUserDto qaUserDto = (QaUserDto) o;
        return Objects.equals(id, qaUserDto.id) &&
                Objects.equals(userName, qaUserDto.userName) &&
                Objects.equals(reviewer, qaUserDto.reviewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, userName, reviewer);
    }

    @Override
    public String toString() {
        return "QaUserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", reviewer=" + reviewer +
                ", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
