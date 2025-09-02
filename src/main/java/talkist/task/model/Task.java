package talkist.task.model;

public abstract class Task {
    private String description;
    private boolean done;

    protected abstract String typePrefix();

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

    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    public boolean isDone() {
        return done;
    }

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