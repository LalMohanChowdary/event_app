package org.jsp.eventapp.controller;

import java.time.LocalDateTime;

import org.jsp.eventapp.entity.Event;
import org.jsp.eventapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@Operation(summary = "To Fetch All The Events", description = "This API Will Fetch All The Events Available In The DataBase")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All Events Found Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Events Present In DataBase") })
	@GetMapping
	public ResponseEntity<?> findAllEvents() {
		return eventService.findAllEvents();
	}

	@Operation(summary = "To Save All The Events", description = "This API Will Accept An Event JSON Object And Save The Events In The DataBase Table And Returns The Event ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Saved Sucessfully"), })
	@PostMapping
	public ResponseEntity<?> saveEvent(@RequestBody Event event) {
		return eventService.saveEvent(event);
	}

	@Operation(summary = "To Fetch All The UP_COMING Events", description = "This API Will Fetch All UP_COMING The Events Available In The DataBase")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All ON_GOING Events Found Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Events Present In DataBase With status UP_COMING") })
	@GetMapping("/upcoming")
	public ResponseEntity<?> findAllUpcomingEvents() {
		return eventService.findAllUpcomingEvents();
	}

	@Operation(summary = "To Fetch All The ON_GOING Events", description = "This API Will Fetch All The ON_GOING Events Available In The DataBase")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All ON_GOING Events Found Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No ON_GOING Events Present In DataBase") })
	@GetMapping("/ongoing")
	public ResponseEntity<?> findAllOngoingEvents() {
		return eventService.findAllOngoingEvents();
	}

	@Operation(summary = "To Fetch All DELETED  Events", description = "This API Will Fetch All DELETED The Events Available In The DataBase")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All Events DELETED Found Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No DELETED Events Present In DataBase") })
	@GetMapping("/deleted")
	public ResponseEntity<?> findAllDeletedEvents() {
		return eventService.findAllDeletedEvents();
	}

	@Operation(summary = "To Fetch All  COMPLETED Events", description = "This API Will Fetch All The COMPLETED Events Available In The DataBase")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All COMPLETED Events Found Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No COMPLETED Events Present In DataBase") })
	@GetMapping("/completed")
	public ResponseEntity<?> findAllCompletedEvents() {
		return eventService.findAllCompletedEvents();
	}

	@Operation(summary = "Set Event Status To ON_GOING", description = "This API Will Accept An Event Id And Update The Status Of Event To ON_GOING In The DataBase Table And Returns The Event ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Updated Sucessfully"),
			@ApiResponse(responseCode = "400", description = "Event Id Not Found") })
	@PatchMapping("/ongoing/{id}")
	public ResponseEntity<?> setStatusToOnGoing(@PathVariable int id) {
		return eventService.setStatusToOngoing(id);
	}

	@Operation(summary = "Set Event Status To COMPLETED", description = "This API Will Accept An Event Id And Update The Status Of Event To COMPLETED In The DataBase Table And Returns The Event ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Updated Sucessfully"),
			@ApiResponse(responseCode = "404", description = "No Events Present In DataBase") })
	@PatchMapping("/completed/{id}")
	public ResponseEntity<?> setStatusToCompleted(@PathVariable int id) {
		return eventService.setStatusToCompleted(id);
	}

	@Operation(summary = "Set Event Status To DELETED", description = "This API Will Accept An Event Id And Update The Status Of Event To DELETED In The DataBase Table And Returns The Event ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Updated Sucessfully"),
			@ApiResponse(responseCode = "404", description = "Event Id Not Found")})
	@Hidden
	@PatchMapping("/deleted/{id}")
	public ResponseEntity<?> setStatusToDeleted(@PathVariable int id) {
		return eventService.setStatusToDeleted(id);
	}

	@Operation(summary = "To Fetch All The Events Between The Date", description = "This API Will Fetch All The Events Available In The DataBase WithIn The Range")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All Events Found Sucessfully WithIn The Date"),
			@ApiResponse(responseCode = "404", description = "No Events Present In DataBase") })
	@GetMapping(value = "/indate")
	public ResponseEntity<?> findEventsBetweenDates(@RequestParam LocalDateTime fromDateTime,
			@RequestParam LocalDateTime toDateTime) {
		return eventService.findEventsBetweenDates(fromDateTime, toDateTime);
	}

}
