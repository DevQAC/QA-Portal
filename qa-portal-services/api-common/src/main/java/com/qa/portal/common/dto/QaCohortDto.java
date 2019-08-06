package com.qa.portal.common.dto;

import java.time.LocalDate;
import java.util.Objects;

public class QaCohortDto extends QaBaseDto {
    private Integer id;

    private String name;

    private LocalDate startDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QaCohortDto that = (QaCohortDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate);
    }

    @Override
    public String toString() {
        return "QaCohortDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
