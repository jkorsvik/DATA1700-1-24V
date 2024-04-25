package com.example.oblig1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// import local Ticket class
import com.example.oblig1.Ticket;

@SpringBootApplication
@RestController
public class Oblig1Application {

	// List of tickets
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public static void main(String[] args) {
		SpringApplication.run(Oblig1Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000", "http://localhost:8080",
								"https://studious-system-7px779946wxcw6w7-8080.app.github.dev")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(false);
			}
		};
	}

	// API method for getting all tickets
	@GetMapping("/get_tickets")
	public ResponseEntity<?> getAllTickets() {
		try {
			if (tickets.isEmpty()) {
				return new ResponseEntity<>("No tickets found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(tickets, HttpStatus.OK);
		} catch (Exception e) {
			// Handle the exception and return an error response
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// API method for getting a ticket by id
	@GetMapping("/get_tickets/{id}")
	public Ticket getTicketById(@PathVariable Integer id) {
		for (Ticket ticket : tickets) {
			if (ticket.getId().equals(id)) {
				return ticket;
			}
		}
		return null; // Ticket not found
	}

	// Read tickets from JSON file at startup
	{
		// Look for a JSON file with the tickets
		File file = new File("tickets.json");
		if (file.exists()) {
			try {
				// Read the JSON file and map it to a list of tickets
				ObjectMapper objectMapper = new ObjectMapper();
				tickets = objectMapper.readValue(file, new TypeReference<List<Ticket>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Method to update the JSON file whenever there is a change
	private void updateTicketsFile() {
		try {
			// Write the updated list of tickets to the JSON file
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new File("tickets.json"), tickets);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// API method for posting new tickets
	@PostMapping("/post_tickets")
	public Ticket postTicket(@RequestBody Ticket ticket) {
		tickets.add(ticket);
		updateTicketsFile(); // Update the JSON file
		return ticket;
	}

	// API method for deleting all tickets
	@DeleteMapping("/delete_tickets")
	public void deleteAllTickets() {
		tickets.clear();
		updateTicketsFile(); // Update the JSON file
	}

	
}