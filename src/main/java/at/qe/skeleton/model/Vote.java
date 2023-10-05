package at.qe.skeleton.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Vote implements Persistable<Long>, Serializable {
    private static final long serialVersionUID = 82L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "voting_name")
    private Voting voting;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "vote_timeslots", joinColumns = @JoinColumn(name = "vote_id"))
    private List<VoteTimeslot> voteTimeslots = new ArrayList<VoteTimeslot>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "vote_locations", joinColumns = @JoinColumn(name = "vote_id"))
    private List<VoteLocation> voteLocations = new ArrayList<VoteLocation>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @ManyToOne(optional = false)
    private User createUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<VoteLocation> getVoteLocations() {
        return voteLocations;
    }

    public void setVoteLocations(List<VoteLocation> voteLocations) {
        this.voteLocations = voteLocations;
    }

    public List<VoteTimeslot> getVoteTimeslots() {
        return voteTimeslots;
    }

    public void setVoteTimeslots(List<VoteTimeslot> voteTimeslots) {
        this.voteTimeslots = voteTimeslots;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    @Override
    public boolean isNew() {
        return null == getCreateDate();
    }
}
