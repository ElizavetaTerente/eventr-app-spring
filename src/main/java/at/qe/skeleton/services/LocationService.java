package at.qe.skeleton.services;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.Voting;
import at.qe.skeleton.repositories.LocationRepository;
import at.qe.skeleton.repositories.UserRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@Scope("application")
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserService userService;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationByName(String name) { return locationRepository.findLocationByName(name); }

    public List<Location> getActiveLocations() {
        return locationRepository.findByActiveTrue();
    }

    public Location loadLocation(String locationName) {
        return locationRepository.findLocationByName(locationName);
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public Location saveLocation(Location location) {
        if (location.isNew()) {
            location.setCreateDate(new Date());
            location.setCreateUser(userService.getAuthenticatedUser());
        } else {
            location.setUpdateDate(new Date());
            location.setUpdateUser(userService.getAuthenticatedUser());
        }
        return locationRepository.save(location);
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void deleteLocation(Location location) {
        locationRepository.delete(location);
    }

}
