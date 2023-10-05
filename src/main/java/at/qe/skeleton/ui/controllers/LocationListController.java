package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.Timeslot;
import at.qe.skeleton.model.Voting;
import at.qe.skeleton.services.LocationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("view")
public class LocationListController implements Serializable {

    @Autowired
    private LocationService locationService;

    private List<Location> locations;
    private List<Location> activeLocations;
    private List<Location> filteredLocations;

    @PostConstruct
    public void init() {
        this.locations = locationService.getAllLocations();
    }

    public List<Location> getLocations() {
        return locationService.getAllLocations();
    }

    public List<Location> getActiveLocations() {
        return locationService.getActiveLocations();
    }

    public List<Location> getFilteredLocations() {
        return this.filteredLocations;
    }

    public void setFilteredLocations(List<Location> filteredLocations) {
        this.filteredLocations = filteredLocations;
    }

    public void doAddLocation(Location location) {
        locations.add(location);
    }

    public List<Location> getLocationsFilterdByTimslots(List<Timeslot> timeslots) {
        List<Location> start = locationService.getActiveLocations();
        List<Location> target = new ArrayList();

        if (timeslots == null) {
            return start;
        }

        for (Location location : start) {
            int counti = 0;
            for (Timeslot timeslot : timeslots) {
                switch (timeslot.getStart().getDayOfWeek()) {
                    case MONDAY:
                        if (location.getOpeningHours().get(0).getOpened()
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(0).getOpen()) >= 0
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(0).getClose()) <= 0
                                && timeslot.getEnd().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(0).getClose()) <= 0) {
                            counti = counti + 1;
                        }
                        break;

                    case TUESDAY:
                        if (location.getOpeningHours().get(1).getOpened()
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(1).getOpen()) >= 0
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(1).getClose()) <= 0
                                && timeslot.getEnd().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(1).getClose()) <= 0) {
                            counti = counti + 1;
                        }
                        break;
                    case WEDNESDAY:
                        if (location.getOpeningHours().get(2).getOpened()
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(2).getOpen()) >= 0
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(2).getClose()) <= 0
                                && timeslot.getEnd().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(2).getClose()) <= 0) {
                            counti = counti + 1;
                        }
                        break;
                    case THURSDAY:
                        if (location.getOpeningHours().get(3).getOpened()
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(3).getOpen()) >= 0
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(3).getClose()) <= 0
                                && timeslot.getEnd().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(3).getClose()) <= 0) {
                            counti = counti + 1;
                        }
                        break;
                    case FRIDAY:
                        if (location.getOpeningHours().get(4).getOpened()
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(4).getOpen()) >= 0
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(4).getClose()) <= 0
                                && timeslot.getEnd().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(4).getClose()) <= 0) {
                            counti = counti + 1;
                        }
                        break;
                    case SATURDAY:
                        if (location.getOpeningHours().get(5).getOpened()
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(5).getOpen()) >= 0
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(5).getClose()) <= 0
                                && timeslot.getEnd().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(5).getClose()) <= 0) {
                            counti = counti + 1;
                        }
                        break;
                    case SUNDAY:
                        if (location.getOpeningHours().get(6).getOpened()
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(6).getOpen()) >= 0
                                && timeslot.getStart().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(6).getClose()) <= 0
                                && timeslot.getEnd().toLocalTime()
                                        .compareTo(location.getOpeningHours().get(6).getClose()) <= 0) {
                            counti = counti + 1;
                        }
                        break;
                }
            }
            if (counti == timeslots.size()) {
                target.add(location);
            }
        }

        return target;
    }

}