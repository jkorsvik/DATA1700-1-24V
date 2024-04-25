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