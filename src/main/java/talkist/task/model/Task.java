package talkist.task.model;

/**
 * A <code>Task</code> is an object with a description String and
 * a boolean representing completion status.
 */
public abstract class Task {
    private String description;
    private boolean done;

    /**
     * Returns the type prefix of the task.
     * "T" for Todo, "D" for Deadline, "E" for Event
     *
     * @return the task type prefix
     */
    protected abstract String typePrefix();

    /**
     * Creates a new task with the given description.
     *
     * @param description description of the task
     * @throws NullPointerException     if description is null
     * @throws IllegalArgumentException if description is empty or blank
     */
    public Task(String description) {
        if (description == null) {
            throw new NullPointerException("Description cannot be null");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns the base string representation of the task, including
     * its completion status and description.
     *
     * @return formatted string with status and description
     */
    protected String base() {
        return String.format("[%s] %s",
                done ? "X" : " ",
                description);
    }

    /**
     * Returns the description of this task.
     *
     * @return description string of the task
     */
    public String getDescription() {
        return this.description;
    }
}