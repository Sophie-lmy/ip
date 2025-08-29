import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    Deadline(String description, String by) {
        super(description);
        this.by = DateTimeParser.parse(by);
    }

    @Override
    protected String typePrefix() { return "D"; }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typePrefix(), base(), DateTimeParser.format(by));
    }
}
