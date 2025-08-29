import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeParser.parse(from);
        this.to = DateTimeParser.parse(to);
    }

    @Override
    protected String typePrefix() { return "E"; }
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", typePrefix(), base(),
                DateTimeParser.format(from),
                DateTimeParser.format(to));
    }
}
