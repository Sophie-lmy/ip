package talkist.task.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTest {

	@Test
	public void testTodoCreation() {
		Todo todo = new Todo("Buy milk");
		assertEquals("[T][ ] Buy milk", todo.toString());
		assertFalse(todo.isDone());
	}

	@Test
	public void testMark() {
		Todo todo = new Todo("Buy milk");
		todo.mark();
		assertTrue(todo.isDone());
		assertEquals("[T][X] Buy milk", todo.toString());
	}

	@Test
	public void testUnmark() {
		Todo todo = new Todo("Buy milk");
		todo.mark();
		todo.unmark();
		assertFalse(todo.isDone());
		assertEquals("[T][ ] Buy milk", todo.toString());
	}

	@Test
	public void testEmptyDescriptionThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Todo(""));
		assertThrows(IllegalArgumentException.class, () -> new Todo("   "));
	}

	@Test
	public void testNullDescriptionThrowsException() {
		assertThrows(NullPointerException.class, () -> new Todo(null));
	}
}
