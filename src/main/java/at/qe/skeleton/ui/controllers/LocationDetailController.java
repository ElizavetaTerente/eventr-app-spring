package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.OpeningHour;
import at.qe.skeleton.model.Tag;
import at.qe.skeleton.model.Weekday;
import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.services.TagService;
import org.jboss.weld.context.RequestContext;
import org.omnifaces.util.Faces;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("view")
public class LocationDetailController implements Serializable {
    @Autowired
    private LocationService locationService;

    @Autowired
    private TagService tagService;

    private List<Tag> selectedTags;

    private Location location;

    private String mode;

    public void setLocation(Location location) {
        this.location = location;
        this.selectedTags = location.getTags();
        doReloadLocation();
    }

    public Location getLocation() {
        return location;
    }

    public void setMode(String mode) {
        if (mode.equals("add")) {
            this.location = new Location();

            List<OpeningHour> list = new ArrayList<>();

            OpeningHour _mo = new OpeningHour();
            _mo.setWeekday(Weekday.MO);
            _mo.setOpened(true);
            _mo.setOpen(LocalTime.parse("10:00"));
            _mo.setClose(LocalTime.parse("17:00"));
            OpeningHour _di = new OpeningHour();
            _di.setWeekday(Weekday.DI);
            _di.setOpened(true);
            _di.setOpen(LocalTime.parse("10:00"));
            _di.setClose(LocalTime.parse("17:00"));
            OpeningHour _mi = new OpeningHour();
            _mi.setWeekday(Weekday.MI);
            _mi.setOpened(true);
            _mi.setOpen(LocalTime.parse("10:00"));
            _mi.setClose(LocalTime.parse("17:00"));
            OpeningHour _do = new OpeningHour();
            _do.setWeekday(Weekday.DO);
            _do.setOpened(true);
            _do.setOpen(LocalTime.parse("10:00"));
            _do.setClose(LocalTime.parse("17:00"));
            OpeningHour _fr = new OpeningHour();
            _fr.setWeekday(Weekday.FR);
            _fr.setOpened(true);
            _fr.setOpen(LocalTime.parse("10:00"));
            _fr.setClose(LocalTime.parse("17:00"));
            OpeningHour _sa = new OpeningHour();
            _sa.setWeekday(Weekday.SA);
            _sa.setOpened(false);
            _sa.setOpen(LocalTime.parse("10:00"));
            _sa.setClose(LocalTime.parse("17:00"));
            OpeningHour _so = new OpeningHour();
            _so.setWeekday(Weekday.SO);
            _so.setOpened(false);
            _so.setOpen(LocalTime.parse("10:00"));
            _so.setClose(LocalTime.parse("17:00"));

            list.add(_mo);
            list.add(_di);
            list.add(_mi);
            list.add(_do);
            list.add(_fr);
            list.add(_sa);
            list.add(_so);

            this.location.setOpeningHours(list);
            this.location.setActive(true);
        }

        this.mode = mode;
    }

    public String getMode() {
        return this.mode;
    }

    public void doReloadLocation() {
        location = locationService.loadLocation(location.getName());
    }

    public void doSaveLocation() {
        location = this.locationService.saveLocation(location);
        location = null;

        PrimeFaces.current().executeScript("PF('locationEditDialog').hide()");
    }

    public void doDeleteLocation() {
        this.locationService.deleteLocation(location);
        location = null;
    }

    public void toggleActive(Location location) {
        location.setActive(location.isActive());
        this.locationService.saveLocation(location);
    }

    public List<Tag> completeTag(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Tag> countries = tagService.getAllTags();
        return countries.stream().filter(t -> t.getName().toLowerCase().contains(queryLowerCase))
                .collect(Collectors.toList());
    }
}
