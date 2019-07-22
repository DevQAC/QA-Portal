package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema="training", name="qa_user")
public class QaUserEntity extends QaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="qa_user_sequence")
    @SequenceGenerator(name = "qa_user_sequence", sequenceName = "qa_user_sequence")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private QaUserEntity reviewer;

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

    public QaUserEntity getReviewer() {
        return reviewer;
    }

    public void setReviewer(QaUserEntity reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QaUserEntity that = (QaUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(reviewer, that.reviewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, reviewer);
    }

    @Override
    public String toString() {
        return "QaUserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", reviewer=" + reviewer +
                ", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
