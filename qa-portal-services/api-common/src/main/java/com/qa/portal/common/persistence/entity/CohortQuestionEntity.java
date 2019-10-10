package com.qa.portal.common.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cohort_question", schema = "training")
@Deprecated
/**
 * TODO - To be removed once Self Reflection has been refactored to use the Question and QuestionCategory from the form-api service.
 */
public class CohortQuestionEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cohort_question_sequence")
    @SequenceGenerator(name = "cohort_question_sequence",
            sequenceName = "training.cohort_question_sequence",
            allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cohort_id")
    private QaCohortEntity cohort;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        return "CohortQuestionEntity [id=" + id + ", cohort=" + cohort + ", question=" + question + "]";
    }

}
