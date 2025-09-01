package talkist.task.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import talkist.parser.DateTimeParser;

public class EventTest {

	@Test
	public void testValidEventCreation() {
		LocalDateTime from = LocalDateTime.of(2025, 9, 10, 14, 0);
		LocalDateTime to = LocalDateTime.of(2025, 9, 10, 16, 0);
		Event event = new Event("Project Meeting", from, to);

		assertNotNull(event);
		String expected = String.format("[E][ ] Project Meeting (from: %s to: %s)",
				DateTimeParser.format(from),
				DateTimeParser.format(to));
		assertEquals(expected, event.toString());
	}

	@Test
	public void testNullFromThrowsException() {
		LocalDateTime to = LocalDateTime.of(2025, 9, 10, 16, 0);
		assertThrows(NullPointerException.class, () -> new Event("Project Meeting", null, to));
	}

	@Test
	public void testNullToThrowsException() {
		LocalDateTime from = LocalDateTime.of(2025, 9, 10, 14, 0);
		assertThrows(NullPointerException.class, () -> new Event("Project Meeting", from, null));
	}

	@Test
	public void testEmptyDescriptionThrowsException() {
		LocalDateTime from = LocalDateTime.of(2025, 9, 10, 14, 0);
		LocalDateTime to = LocalDateTime.of(2025, 9, 10, 16, 0);
		assertThrows(IllegalArgumentException.class, () -> new Event("", from, to));
	}

	@Test
	public void testMarkAsDoneAffectsToString() {
		LocalDateTime from = LocalDateTime.of(2025, 9, 10, 14, 0);
		LocalDateTime to = LocalDateTime.of(2025, 9, 10, 16, 0);
		Event event = new Event("Project Meeting", from, to);
		event.mark();
		assertTrue(event.toString().startsWith("[E][X]"));
	}
}
