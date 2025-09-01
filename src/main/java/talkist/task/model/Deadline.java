package talkist.task.model;

import java.time.LocalDateTime;

import talkist.parser.DateTimeParser;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        if (by == null) {
            throw new NullPointerException("Deadline time cannot be null.");
        }
        this.by = by;
    }

    @Override
    protected String typePrefix() { return "D"; }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typePrefix(), base(), DateTimeParser.format(by));
    }
}
