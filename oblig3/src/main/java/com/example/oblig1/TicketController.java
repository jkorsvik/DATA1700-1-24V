package com.example.oblig1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// import TicketRepository from the same package
import com.example.oblig1.TicketRepository;
// import Ticket from the same package
import com.example.oblig1.Ticket;
// import TICKET_NOT_FOUND from the same package
import com.example.oblig1.TicketNotFoundException;

@RestController
public class TicketController {
    private final TicketRepository repository;

    TicketController(TicketRepository repository) {
        this.repository = repository;
    }

    // GET all tickets
    @GetMapping("/tickets")
    Iterable<Ticket> all() {
        return repository.findAll();
    }

    // POST a new ticket
    @PostMapping("/tickets")
    Ticket newTicket(@RequestBody Ticket newTicket) {
        return repository.save(newTicket);
    }

    // GET a single ticket
    @GetMapping("/tickets/{id}")
    Ticket one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    // DELETE a ticket
    @DeleteMapping("/tickets/{id}")
    void deleteTicket(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}