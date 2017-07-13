package com.futureCorp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Resource {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resourceId;
    @Column(length = 500)
    private String description;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Topic topic;
    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();
    @Temporal(TemporalType.DATE)
    private Date lastUpdated = new Date();
    @Enumerated(EnumType.STRING)
    ResourceType resourceType;
    @Column(length = 200)
    private String link;


    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
