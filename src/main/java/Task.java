abstract class Task {
    private String description;
    private boolean done;

    protected abstract String typePrefix();

    Task(String description) {
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

    @Override
    public String toString() {
        return String.format("[%s]%s",
                typePrefix(),
                base());
    }
}