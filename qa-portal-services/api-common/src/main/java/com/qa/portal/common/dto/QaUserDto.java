package com.qa.portal.common.dto;

import java.util.Objects;

public class QaUserDto extends QaBaseDto {

    private Integer id;

    private String userName;

    private String firstName;

    private String lastName;

    public void setId(Integer id) {
        this.id = id;
    }

    public QaUserDto() {
        super();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        QaUserDto qaUserDto = (QaUserDto) o;
        return Objects.equals(id, qaUserDto.id) && Objects.equals(userName, qaUserDto.userName)
                && Objects.equals(firstName, qaUserDto.firstName) && Objects.equals(lastName, qaUserDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, lastName);
    }

    @Override
    public String toString() {
        return "QaUserDto{" + "id=" + id + ", userName='" + userName + '\'' + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + ", lastUpdatedTimestamp=" + lastUpdatedTimestamp
                + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + '}';
    }
}
