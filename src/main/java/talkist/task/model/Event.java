package talkist.task.model;

import java.time.LocalDateTime;

import talkist.parser.DateTimeParser;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

   public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        if (from == null || to == null) {
            throw new NullPointerException("Event time cannot be null.");
        }
        this.from = from;
        this.to = to;
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
