package com.qa.portal.reflection.persistence.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

@Entity
@Table(name = "question", schema = "training")
public class QuestionEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "question_sequence", sequenceName = "training.question_sequence", allocationSize = 1)
	private Integer id;

	@Column(name="body")
	private String body;

	@Column(name="category")
	private String category;

	@Column(name="num_options")
	private Integer numberOfOptions;

//	@OneToMany(mappedBy = "question")
//	private Set<ReflectionQuestionEntity> forms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getNumberOfOptions() {
		return numberOfOptions;
	}

	public void setNumberOfOptions(Integer numberOfOptions) {
		this.numberOfOptions = numberOfOptions;
	}

//	public Set<ReflectionQuestionEntity> getForms() {
//		return forms;
//	}
//
//	public void setForms(Set<ReflectionQuestionEntity> forms) {
//		this.forms = forms;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QuestionEntity that = (QuestionEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(body, that.body) &&
				Objects.equals(category, that.category) &&
				Objects.equals(numberOfOptions, that.numberOfOptions);
//				Objects.equals(numberOfOptions, that.numberOfOptions) &&
//				Objects.equals(forms, that.forms);
	}

	@Override
	public int hashCode() {
//		return Objects.hash(id, body, category, numberOfOptions, forms);
		return Objects.hash(id, body, category, numberOfOptions);
	}

	@Override
	public String toString() {
		return "QuestionEntity{" +
				"id=" + id +
				", body='" + body + '\'' +
				", category='" + category + '\'' +
				", numberOfOptions=" + numberOfOptions +
//				", forms=" + forms +
				", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
				", lastUpdatedBy='" + lastUpdatedBy + '\'' +
				", version=" + version +
				'}';
	}
}
