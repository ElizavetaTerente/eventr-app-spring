package at.qe.skeleton.model;

import org.springframework.data.domain.Persistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Location implements Persistable<String>, Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    private String name;
    @Column(columnDefinition = "TEXT")
    private String street;
    private String city;
    private String description;
    private String url;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @ManyToOne(optional = false)
    private User createUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @ManyToOne(optional = true)
    private User updateUser;

    private boolean active;

    @ElementCollection
    @CollectionTable(name = "Location_OpeningHours", joinColumns = @JoinColumn(name = "name"))
    private List<OpeningHour> openingHours;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "location_tags",
            joinColumns = @JoinColumn(name="location_name"),
            inverseJoinColumns = @JoinColumn(name="tag_name"))
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "voting_possibilities",
            joinColumns = @JoinColumn(name="possibility_name"),
            inverseJoinColumns = @JoinColumn(name="voting_name"))
    private List<Voting> votings = new ArrayList<Voting>();

    public List<Voting> getVotings() {
        return votings;
    }

    public void setVotings(List<Voting> votings) {
        this.votings = votings;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        System.out.print(tags);
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public List<OpeningHour> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<OpeningHour> openingHours) {
        this.openingHours = openingHours;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getId() {
        return getName();
    }

    public void setId(String id) {
        setName(id);
    }

    @Override
    public boolean isNew() {
        return (null == createDate);
    }
}