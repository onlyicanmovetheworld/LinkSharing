package com.futureCorp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;
    private String name;
    @ManyToOne
    private User createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated= new Date();
    @Enumerated(EnumType.STRING)
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

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }


}
