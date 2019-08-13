package com.qa.portal.common.persistence.entity;

import javax.persistence.*;

@Entity
@Table(schema = "training", name = "comment")
public class CommentEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence")
    @SequenceGenerator(name = "comment_sequence",
            sequenceName = "training.comment_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "content")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
