package com.anwesha.eventmanagementsystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anwesha.eventmanagementsystem.entity.Event;
import com.anwesha.eventmanagementsystem.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.anwesha.eventmanagementsystem.entity.EventStatus;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventService {
	 @Autowired
	    private EventRepository eventRepository;

	    public Event saveEvent(Event event) {
	        return eventRepository.save(event);
	    }

	    public List<Event> getAllEvents() {
	        return eventRepository.findAll();
	    }
	    public Event updateEvent(Long id, Event updatedEvent) {

	        Event event = eventRepository.findById(id).orElse(null);

	        if(event != null) {
	            event.setEventName(updatedEvent.getEventName());
	            event.setEventDate(updatedEvent.getEventDate());
	            event.setVenue(updatedEvent.getVenue());
	            event.setOrganizer(updatedEvent.getOrganizer());

	            return eventRepository.save(event);
	        }

	        return null;
	    }

	    public void deleteEvent(Long id) {
	        eventRepository.deleteById(id);
	    }
	    
	    public List<Event> searchEvents(String name) {
	        return eventRepository.findByEventNameContainingIgnoreCase(name);
	    }
	    
	    public Page<Event> getEvents(int page, int size, String sortBy) {

	        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        return eventRepository.findAll(pageable);
	    }
	    
	    public List<Event> getEventsByStatus(EventStatus status) {
	        return eventRepository.findByStatus(status);
	    }
	    
	    public Map<String, Long> getEventStats() {

	        Map<String, Long> stats = new HashMap<>();

	        stats.put("totalEvents", eventRepository.count());

	        stats.put("upcomingEvents",
	                eventRepository.countByStatus(EventStatus.UPCOMING));

	        stats.put("ongoingEvents",
	                eventRepository.countByStatus(EventStatus.ONGOING));

	        stats.put("completedEvents",
	                eventRepository.countByStatus(EventStatus.COMPLETED));

	        stats.put("cancelledEvents",
	                eventRepository.countByStatus(EventStatus.CANCELLED));

	        return stats;
	    }
}
