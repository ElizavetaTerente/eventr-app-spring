package at.qe.skeleton.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event implements Persistable<String>, Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    private String name;
    private LocalDateTime start;
    private String endString;
    private String startString;
    private LocalDateTime end;
    private LocalDateTime voting_start;
    private LocalDateTime voting_end;
    private Integer numParticipants;

    @ManyToOne(optional = true)
    private Location location;

    @ManyToOne(optional = false)
    private User createUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @ManyToOne(optional = true)
    private User updateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "event_participants", joinColumns = @JoinColumn(name = "event_name"), inverseJoinColumns = @JoinColumn(name = "participant_name"))
    private List<User> participants = new ArrayList<User>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public String getStartString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return start.format(formatter);
    }

    public String getEndString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return end.format(formatter);
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
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

    public LocalDateTime getVoting_start() {
        return voting_start;
    }

    public void setVoting_start(LocalDateTime voting_start) {
        this.voting_start = voting_start;
    }

    public LocalDateTime getVoting_end() {
        return voting_end;
    }

    public void setVoting_end(LocalDateTime voting_end) {
        this.voting_end = voting_end;
    }

    public Integer getNumParticipants() {
        return this.participants.size();
    }

    public void setNumParticipants(Integer numParticipants) {
        this.numParticipants = numParticipants;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
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