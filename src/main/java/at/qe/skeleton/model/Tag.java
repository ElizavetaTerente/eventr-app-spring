package at.qe.skeleton.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tag implements Persistable<String>, Serializable {

    private static final long serialVersionUID = 7L;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "location_tags",
            joinColumns = @JoinColumn(name="tag_name"),
            inverseJoinColumns = @JoinColumn(name="location_name"))
    private List<Location> locations = new ArrayList<Location>();

    @Id
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ManyToOne(optional = false)
    private User createUser;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean isNew() {
        return (null == createDate);
    }
}
