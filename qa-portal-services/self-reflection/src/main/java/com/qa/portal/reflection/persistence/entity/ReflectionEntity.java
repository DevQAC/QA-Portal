package com.qa.portal.reflection.persistence.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.QaTraineeEntity;
import com.qa.portal.common.persistence.entity.QaTrainerEntity;

@Entity
@Table(name = "reflection", schema = "training")
public class ReflectionEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "reflection_sequence", sequenceName = "training.reflection_sequence", allocationSize = 1)
	private Integer id;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "reviewer_id")
	private QaTrainerEntity reviewer;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "responder_id")
	private QaTraineeEntity responder;

	private LocalDate date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QaTrainerEntity getReviewer() {
		return reviewer;
	}

	public void setReviewer(QaTrainerEntity reviewer) {
		this.reviewer = reviewer;
	}

	public QaTraineeEntity getResponder() {
		return responder;
	}

	public void setResponder(QaTraineeEntity responder) {
		this.responder = responder;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((responder == null) ? 0 : responder.hashCode());
		result = prime * result + ((reviewer == null) ? 0 : reviewer.hashCode());
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
		ReflectionEntity other = (ReflectionEntity) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (responder == null) {
			if (other.responder != null)
				return false;
		} else if (!responder.equals(other.responder))
			return false;
		if (reviewer == null) {
			if (other.reviewer != null)
				return false;
		} else if (!reviewer.equals(other.reviewer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReflectionEntity [id=" + id + ", reviewer=" + reviewer + ", responder=" + responder + ", date=" + date
				+ "]";
	}

}
