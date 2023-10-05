package at.qe.skeleton.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Voting implements Persistable<String>, Serializable {

	private static final long serialVersionUID = 8L;

	@Id
	@Column(length = 100)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date votingStop;
	private boolean votingActive;

	@ManyToOne(optional = false)
	private User createUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@ManyToOne(optional = true)
	private User updateUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "voting_possibilities", joinColumns = @JoinColumn(name = "voting_name"), inverseJoinColumns = @JoinColumn(name = "possibility_name"))
	private List<Location> possibilities = new ArrayList<Location>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "voting_participants", joinColumns = @JoinColumn(name = "voting_name"), inverseJoinColumns = @JoinColumn(name = "participant_name"))
	private List<User> participants = new ArrayList<User>();

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "voting_timeslots", joinColumns = @JoinColumn(name = "voting_name"))
	private List<Timeslot> timeslots = new ArrayList<Timeslot>();

	@OneToMany(mappedBy = "voting", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vote> votes;

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public Date getVotingStop() {
		return votingStop;
	}

	public void setVotingStop(Date votingStop) {
		this.votingStop = votingStop;
	}

	public boolean isVotingActive() {
		return votingActive;
	}

	public void setVotingActive(boolean votingActive) {
		this.votingActive = votingActive;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public List<Location> getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(List<Location> possibilities) {
		this.possibilities = possibilities;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public List<Timeslot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(List<Timeslot> timeslots) {
		this.timeslots = timeslots;
	}

	@Override
	public String getId() {
		return getName();
	}

	@Override
	public boolean isNew() {
		return null == getCreateDate();
	}

	public void cancel() {
		this.getParticipants().clear();
		this.getVotes().clear();
		this.getPossibilities().clear();
		this.getTimeslots().clear();
	}

	public List<VoteLocation> currentVotingStatusForLocations() {
		List<VoteLocation> target = new ArrayList<>();

		for (Location location : this.getPossibilities()) {
			VoteLocation voteLocation = new VoteLocation();
			voteLocation.setLocation(location);
			voteLocation.setWeight(0);
			target.add(voteLocation);
		}
		for (Vote vote : this.getVotes()) {
			for (VoteLocation voteLocation : vote.getVoteLocations()) {
				for (VoteLocation targetLocation : target) {
					if (voteLocation.getLocation().getName().equals(targetLocation.getLocation().getName())) {
						targetLocation.setWeight(targetLocation.getWeight() + voteLocation.getWeight());
					}
				}
			}
		}
		return target;
	}

	public List<VoteTimeslot> currentVotingStatusForTime() {
		List<VoteTimeslot> target = new ArrayList<>();

		for (Timeslot timeslot : this.getTimeslots()) {
			VoteTimeslot voteTimeslot = new VoteTimeslot();
			voteTimeslot.setTimeslot(timeslot);
			voteTimeslot.setWeight(0);
			target.add(voteTimeslot);
		}

		for (Vote vote : this.getVotes()) {
			for (VoteTimeslot voteTimeslot : vote.getVoteTimeslots()) {
				for (VoteTimeslot targetTimeslot : target) {
					if (voteTimeslot.getTimeslot().getEnd().equals(targetTimeslot.getTimeslot().getEnd()) && voteTimeslot.getTimeslot().getStart().equals(targetTimeslot.getTimeslot().getStart())) {
						targetTimeslot.setWeight(targetTimeslot.getWeight() + voteTimeslot.getWeight());
					}
				}
			}
		}

		return target;
	}
}
