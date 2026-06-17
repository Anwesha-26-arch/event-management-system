package com.anwesha.eventmanagementsystem.controller;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.anwesha.eventmanagementsystem.entity.Event;
import com.anwesha.eventmanagementsystem.service.EventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import com.anwesha.eventmanagementsystem.entity.EventStatus;
import java.util.Map;

@RestController
public class EventController {
	@Autowired
    private EventService eventService;

    @PostMapping("/events")
    public Event saveEvent(@Valid @RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
    @PutMapping("/events/{id}")
    public Event updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody Event event) {

        return eventService.updateEvent(id, event);
    }
    @DeleteMapping("/events/{id}")
    public String deleteEvent(@PathVariable Long id) {

        eventService.deleteEvent(id);

        return "Event Deleted Successfully";
    }
    
    @GetMapping("/events/search")
    public List<Event> searchEvents(@RequestParam String name) {
        return eventService.searchEvents(name);
    }
    
    @GetMapping("/events/page")
    public Page<Event> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "eventName") String sortBy) {

        return eventService.getEvents(page, size, sortBy);
    }
    
    @GetMapping("/events/status/{status}")
    public List<Event> getEventsByStatus(
            @PathVariable EventStatus status) {

        return eventService.getEventsByStatus(status);
    }
    
    @GetMapping("/events/stats")
    public Map<String, Long> getEventStats() {
        return eventService.getEventStats();
    }
}
