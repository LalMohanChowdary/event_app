package org.jsp.eventapp.service;

import java.time.LocalDateTime;

import org.jsp.eventapp.entity.Event;
import org.springframework.http.ResponseEntity;

public interface EventService {

	ResponseEntity<?> saveEvent(Event event);

	ResponseEntity<?> findAllEvents();

	ResponseEntity<?> findAllUpcomingEvents();

	ResponseEntity<?> findAllOngoingEvents();

	ResponseEntity<?> findAllDeletedEvents();

	ResponseEntity<?> findAllCompletedEvents();

	ResponseEntity<?> setStatusToOngoing(int id);

	ResponseEntity<?> setStatusToCompleted(int id);

	ResponseEntity<?> setStatusToDeleted(int id);

	ResponseEntity<?> findEventsBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime);

	
}
