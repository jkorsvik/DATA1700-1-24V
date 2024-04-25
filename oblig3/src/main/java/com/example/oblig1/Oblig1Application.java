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


@SpringBootApplication
public class Oblig1Application {

	// List of tickets
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public static void main(String[] args) {
		SpringApplication.run(Oblig1Application.class, args);
	}
	// API method for getting all tickets
	@GetMapping("/tickets")
	public List<Ticket> getAllTickets() {
		return tickets;
	}

	// API method for getting a ticket by id
	@GetMapping("/tickets/{id}")
	public Ticket getTicketById(@PathVariable Integer id) {
		for (Ticket ticket : tickets) {
			if (ticket.getId().equals(id)) {
				return ticket;
			}
		}
		return null; // Ticket not found
	}

	// API method for posting new tickets
	@PostMapping("/tickets")
	public Ticket postTicket(@RequestBody Ticket ticket) {
		tickets.add(ticket);
		return ticket;
	}

	// API method for deleting all tickets
	@DeleteMapping("/tickets")
	public void deleteAllTickets() {
		tickets.clear();
	}
	// Ticket class
	public class Ticket {
		// Ticket counter for id
		private static int counter = 0;

		private Integer id;
		private String film;
		private int antall;
		private String fornavn;
		private String etternavn;
		private String telefon;
		private String epost;
	
		public Ticket(String film, int antall, String fornavn, String etternavn, String telefon, String epost) {
			// Auto generate ID
			this.id = ++counter; // Auto generate ID
			this.film = film;
			this.antall = antall;
			this.fornavn = fornavn;
			this.etternavn = etternavn;
			this.telefon = telefon;
			this.epost = epost;
		}
	
		// getters and setters
	
		public String getFilm() {
			return this.film;
		}
	
		public void setFilm(String film) {
			this.film = film;
		}
	
		// Repeat for the other fields
		public int getAntall() {
			return this.antall;
		}
	
		public void setAntall(int antall) {
			this.antall = antall;
		}
	
		public String getFornavn() {
			return this.fornavn;
		}
	
		public void setFornavn(String fornavn) {
			this.fornavn = fornavn;
		}
	
		public String getEtternavn() {
			return this.etternavn;
		}
	
		public void setEtternavn(String etternavn) {
			this.etternavn = etternavn;
		}
	
		public String getTelefon() {
			return this.telefon;
		}
	
		public void setTelefon(String telefon) {
			this.telefon = telefon;
		}
	
		public String getEpost() {
			return this.epost;
		}
	
		public void setEpost(String epost) {
			this.epost = epost;
		}
	
	}
}
