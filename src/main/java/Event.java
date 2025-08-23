public class Event extends Task {
    private String from;
    private String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String typePrefix() { return "E"; }
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", typePrefix(), base(), from, to);
    }
}
