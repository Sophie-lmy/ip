package talkist.task.model;

import java.time.LocalDateTime;
import talkist.parser.DateTimeParser;

/**
 * Represents a Deadline task, which has a description and a due time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a new Deadline task with the given description and due time.
     *
     * @param description description of the deadline
     * @param by due date/time of the deadline
     * @throws NullPointerException if by is null
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        if (by == null) {
            throw new NullPointerException("Deadline time cannot be null.");
        }
        this.by = by;
    }

    /**
     * Returns the type prefix of this task: "D" for Deadline.
     *
     * @return task type prefix
     */
    @Override
    protected String typePrefix() {
        return "D";
    }

    /**
     * Returns a string representation of the Deadline task, including its status,
     * description, and due time.
     *
     * @return formatted string of the task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typePrefix(), base(), DateTimeParser.format(by));
    }
}
