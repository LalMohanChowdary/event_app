package org.jsp.eventapp.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.eventapp.DAO.EventDao;
import org.jsp.eventapp.entity.Event;
import org.jsp.eventapp.exceptionclasses.InvalidEventIdException;
import org.jsp.eventapp.exceptionclasses.NoEventFoundException;
import org.jsp.eventapp.responsestructure.ResponseStructure;
import org.jsp.eventapp.service.EventService;
import org.jsp.eventapp.util.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventDao eventDao;

	@Override
	public ResponseEntity<?> saveEvent(Event event) {
		event.setStatus(EventStatus.UP_COMING);
		Event dbevent = eventDao.saveEvent(event);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Event Saved Sucessfully").body(dbevent).build());
	}

	@Override
	public ResponseEntity<?> findAllEvents() {

		List<Event> el = eventDao.findAllEvents();

		if (el.isEmpty()) {
			throw NoEventFoundException.builder().message("No Event Found In The DataBase").build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Events Found Sucessfully").body(el).build());
	}

	@Override
	public ResponseEntity<?> findAllUpcomingEvents() {

		List<Event> upcoming = eventDao.findAllUpcomingEvents();

		if (upcoming.isEmpty()) {
			throw NoEventFoundException.builder().message("No Upcoming Events Found").build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.body(upcoming).message("Events That Are Upcoming").build());
	}

	@Override
	public ResponseEntity<?> findAllOngoingEvents() {
		List<Event> ongoing = eventDao.findAllOngoingEvents();

		if (ongoing.isEmpty()) {
			throw NoEventFoundException.builder().message("No OnGoing Events Found In Database").build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.body(ongoing).message("Events That Are OnGoing").build());
	}

	@Override
	public ResponseEntity<?> findAllDeletedEvents() {

		List<Event> deleted = eventDao.findAllDeletedEvents();
		if (deleted.isEmpty())
			throw NoEventFoundException.builder().message("No Deleted Status Event").build();
		return ResponseEntity.status(HttpStatus.FOUND).body(ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.body(deleted).message("Deleted Events In Database Are" + deleted).build());
	}

	@Override
	public ResponseEntity<?> findAllCompletedEvents() {
		List<Event> completed = eventDao.findAllCompletedEvents();
		if (completed.isEmpty())
			throw NoEventFoundException.builder().message("No Completed Status Events").build();

		return ResponseEntity.status(HttpStatus.FOUND).body(ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("Completed Status Employees Are" + completed).build());
	}

	@Override
	public ResponseEntity<?> setStatusToOngoing(int id) {

		Optional<Event> event = eventDao.findEventById(id);
		if (event.isEmpty())
			throw InvalidEventIdException.builder().message("Invalid Event " + id).build();

		Event dbevent = event.get();

		dbevent.setStatus(EventStatus.ON_GOING);
		eventDao.updateEvent(dbevent);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder()
				.status(HttpStatus.CREATED.value()).body(dbevent).message("Event Updated To On_Going").build());
	}

	@Override
	public ResponseEntity<?> setStatusToCompleted(int id) {

		Optional<Event> event = eventDao.findEventById(id);
		if (event.isEmpty())
			throw InvalidEventIdException.builder().message("No Event For Updation").build();
		Event dbevent = event.get();
		dbevent.setStatus(EventStatus.COMPLETED);
		eventDao.updateEvent(dbevent);

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder()
				.status(HttpStatus.CREATED.value()).body(dbevent).message("Event Updated To Completed").build());
	}

	@Override
	public ResponseEntity<?> setStatusToDeleted(int id) {

		Optional<Event> event = eventDao.findEventById(id);
		if (event.isEmpty())
			throw InvalidEventIdException.builder().message("No Event Found For Updation").build();

		Event dbevent = event.get();
		dbevent.setStatus(EventStatus.DELETED);
		eventDao.updateEvent(dbevent);

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder()
				.status(HttpStatus.CREATED.value()).body(dbevent).message("Event Updated To Completed").build());
	}

	@Override
	public ResponseEntity<?> findEventsBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime) {

		List<Event> events = eventDao.findAllEvents();
		if (events.isEmpty()) {
			throw NoEventFoundException.builder().message("No Events Found In The DataBase").build();
		}

		ArrayList<Event> eventlist = new ArrayList<>();
		for (Event e : events) {
			if (e.getFromDateTime().isAfter(fromDateTime) && e.getToDateTime().isBefore(toDateTime)) {
				eventlist.add(e);
			}
		}

		if (eventlist.isEmpty()) {
			throw NoEventFoundException.builder().message("No Events Found In Given Range").build();
		}

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(ResponseStructure.builder()
				.status(HttpStatus.FOUND.value())
				.body(eventlist)
				.message("Events Found In Range").build());
	}
}
