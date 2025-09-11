package talkist.task.model;

import java.time.LocalDateTime;

import talkist.parser.DateTimeParser;

/**
 * Represents an Event task, which has a description and a start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates a new Event task with the given description, start and end times.
     *
     * @param description description of the event
     * @param from start time of the event
     * @param to end time of the event
     * @throws NullPointerException if from or to is null
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        if (from == null || to == null) {
            throw new NullPointerException("Event time cannot be null.");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type prefix of this task: "E" for Event.
     *
     * @return task type prefix
     */
    @Override
    protected String typePrefix() {
        return "E";
    }

    /**
     * Returns a string representation of the Event task, including its status,
     * description, start and end times.
     *
     * @return formatted string of the task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", typePrefix(), base(),
                DateTimeParser.format(from), DateTimeParser.format(to));
    }
}
