package com.qa.portal.reflection.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
@Entity
@Table(name = "cohort_question", schema = "training")
public class CohortQuestionEntity extends QaBaseEntity {

	@Id
	@ManyToOne
	private QaCohortEntity cohort;
	
	@Id
	@ManyToOne
	private QuestionEntity question;

	public QaCohortEntity getCohort() {
		return cohort;
	}

	public void setCohort(QaCohortEntity cohort) {
		this.cohort = cohort;
	}

	public QuestionEntity getQuestion() {
		return question;
	}

	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cohort == null) ? 0 : cohort.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
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
		CohortQuestionEntity other = (CohortQuestionEntity) obj;
		if (cohort == null) {
			if (other.cohort != null)
				return false;
		} else if (!cohort.equals(other.cohort))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CohortQuestionEntity [cohort=" + cohort + ", question=" + question + "]";
	}
	
}
