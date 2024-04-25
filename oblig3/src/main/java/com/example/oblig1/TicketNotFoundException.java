@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Integer id) {
        super("Could not find ticket " + id);
    }
}