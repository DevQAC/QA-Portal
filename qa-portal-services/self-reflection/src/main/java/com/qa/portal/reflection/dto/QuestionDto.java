package com.qa.portal.reflection.dto;

import com.qa.portal.common.dto.QaBaseDto;

import java.util.Objects;

public final class QuestionDto extends QaBaseDto {

    private Integer id;

    private String body;

    private String category;

    private Integer numberOfOptions;

    public Integer getNumberOfOptions() {
		return numberOfOptions;
	}

	public void setNumberOfOptions(Integer numberOfOptions) {
		this.numberOfOptions = numberOfOptions;
	}

	public Integer getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getCategory() {
        return category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(body, that.body) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, category);
    }
}
