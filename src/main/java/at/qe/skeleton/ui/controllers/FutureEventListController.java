package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.Event;
import at.qe.skeleton.model.User;
import at.qe.skeleton.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Scope("view")
public class FutureEventListController implements Serializable {

    @Autowired
    private EventService eventService;

    public List<Event> getFutureEvents(User user){
        return eventService.getFutureEvents(user);
    }
    
}
