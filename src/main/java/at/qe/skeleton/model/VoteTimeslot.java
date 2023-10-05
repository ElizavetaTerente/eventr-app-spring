package at.qe.skeleton.model;

import java.time.format.DateTimeFormatter;

import javax.persistence.Embeddable;

@Embeddable
public class VoteTimeslot {
    private Timeslot timeslot;
    private Integer weight;

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        String tmp1 = timeslot.getStart().format(formatter);
        String tmp2 = timeslot.getEnd().format(formatter2);
        return "From: " + tmp1 + "; To: " +  tmp2 + ";";
    }

}
