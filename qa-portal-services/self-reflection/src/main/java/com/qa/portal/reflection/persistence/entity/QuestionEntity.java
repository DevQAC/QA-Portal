package com.qa.portal.reflection.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

@Entity
@Table(name = "question", schema = "training")
public class QuestionEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "question_sequence", sequenceName = "training.question_sequence", allocationSize = 1)
	private Integer id;

	private String body;

	private String category;

	@OneToMany(mappedBy = "reflection")
	private Set<ReflectionQuestionEntity> forms;

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

	public Set<ReflectionQuestionEntity> getForms() {
		return forms;
	}

	public void setForms(Set<ReflectionQuestionEntity> forms) {
		this.forms = forms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((forms == null) ? 0 : forms.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionEntity other = (QuestionEntity) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (forms == null) {
			if (other.forms != null)
				return false;
		} else if (!forms.equals(other.forms))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionEntity [id=" + id + ", body=" + body + ", category=" + category + ", forms=" + forms + "]";
	}

}
