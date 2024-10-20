package org.jsp.eventapp.DAO;

import java.util.List;
import java.util.Optional;

import org.jsp.eventapp.entity.Event;

public interface EventDao {

	Event saveEvent(Event event);

	Event updateEvent(Event event);

	Optional<Event> findEventById(int id);

	List<Event> findAllEvents();

	List<Event> findAllUpcomingEvents();

	List<Event> findAllOngoingEvents();

	List<Event> findAllCompletedEvents();

	List<Event> findAllDeletedEvents();

	
	
	
	
	
}
