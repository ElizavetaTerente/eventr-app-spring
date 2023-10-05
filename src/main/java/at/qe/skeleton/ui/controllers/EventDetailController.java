package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.*;
import at.qe.skeleton.services.EventService;
import at.qe.skeleton.ui.beans.SessionInfoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("view")
public class EventDetailController implements Serializable {

    @Autowired
    private SessionInfoBean sessionInfoBean;

    @Autowired
    private EventService eventService;

    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        doReloadEvent();
    }

    public void doReloadEvent() {
        event = eventService.loadEvent(event.getName());
    }

    public void signOut() {
        event.getParticipants().remove(sessionInfoBean.getCurrentUser());
        eventService.saveEvent(event);
    }

}