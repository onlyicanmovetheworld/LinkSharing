package com.futureCorp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subscriptionId;
    @ManyToOne
    private Topic topic;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    Seriousness seriousness = Seriousness.Serious;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
