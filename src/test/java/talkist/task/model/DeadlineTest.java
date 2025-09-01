package talkist.task.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import talkist.parser.DateTimeParser;

public class DeadlineTest {

	@Test
	public void testDeadlineCreation() {
		LocalDateTime by = DateTimeParser.parse("2025-09-10 1600");
		Deadline deadline = new Deadline("Submit Assignment", by);
		assertEquals("[D][ ] Submit Assignment (by: Sep 10 2025 16:00)", deadline.toString());
		assertFalse(deadline.isDone());
	}

	@Test
	public void testMark() {
		LocalDateTime by = DateTimeParser.parse("2025-09-10 1600");
		Deadline deadline = new Deadline("Submit Assignment", by);
		deadline.mark();
		assertTrue(deadline.isDone());
		assertEquals("[D][X] Submit Assignment (by: Sep 10 2025 16:00)", deadline.toString());
	}

	@Test
	public void testUnmark() {
		LocalDateTime by = DateTimeParser.parse("2025-09-10 1600");
		Deadline deadline = new Deadline("Submit Assignment", by);
		deadline.mark();
		deadline.unmark();
		assertFalse(deadline.isDone());
		assertEquals("[D][ ] Submit Assignment (by: Sep 10 2025 16:00)", deadline.toString());
	}

	@Test
	public void testEmptyOrNullDescriptionThrowsException() {
		LocalDateTime by = DateTimeParser.parse("2025-09-10 1600");
		assertThrows(IllegalArgumentException.class, () -> new Deadline("", by));
		assertThrows(NullPointerException.class, () -> new Deadline(null, by));
	}

	@Test
	public void testNullByThrowsException() {
		assertThrows(NullPointerException.class, () -> new Deadline("Submit Assignment", null));
	}
}
