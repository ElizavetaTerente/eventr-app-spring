package at.qe.skeleton.services;

import at.qe.skeleton.model.Event;
import at.qe.skeleton.model.User;
import at.qe.skeleton.model.UserRole;
import at.qe.skeleton.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Scope("application")
public class EventService {

    @Autowired

    private EventRepository eventRepository;
    @Autowired

    private UserService userService;

    @Autowired
    private MailService mailService;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event loadEvent(String eventName) {
        return eventRepository.findFirstByName(eventName);
    }

    public Event saveEvent(Event event) {
        if (event.isNew()) {
            event.setCreateDate(new Date());
            event.setCreateUser(userService.getAuthenticatedUser());
        } else {
        }
        return eventRepository.save(event);
    }

    public void deleteEvent(Event event) {
        if (userService.getAuthenticatedUser().equals(event.getCreateUser())
                || userService.getAuthenticatedUser().getRoles().contains(UserRole.ADMIN)) {
            eventRepository.delete(event);
        }
    }

    public void createEvent(Event cEvent) {
        Event event = new Event();
        event.setName(cEvent.getName());
        event.setCreateDate(new Date());
        event.setCreateUser(userService.getAuthenticatedUser());
        event.setStart(cEvent.getStart());
        event.setEnd(cEvent.getEnd());
        eventRepository.save(event);

    }

    public List<Event> getUserHistory(User user) {
        return eventRepository.findHistoryByUser(user);
    }

    public List<Event> getFutureEvents(User user) {
        return eventRepository.findFutureEventsByUser(user);
    }

}
