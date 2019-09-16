package com.qa.portal.common.dto;

import java.util.Objects;

public class QaUserDto extends QaBaseDto {

    private Integer id;

    private String userName;

    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QaUserDto userDto = (QaUserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(userName, userDto.userName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "QaUserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
