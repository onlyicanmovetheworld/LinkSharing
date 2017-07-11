package com.futureCorp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;
    private String name;
    @ManyToOne
    private User createdBy;
    @Temporal(TemporalType.DATE)
    private Date dateCreated = new Date();
    @Temporal(TemporalType.DATE)
    private Date dateUpdated= new Date();
    private Visibility visibility =Visibility.Public;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
