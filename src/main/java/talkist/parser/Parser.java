package talkist.parser;

import talkist.storage.Storage;
import talkist.task.TaskList;
import talkist.task.model.Deadline;
import talkist.task.model.Event;
import talkist.task.model.Task;
import talkist.task.model.Todo;
import talkist.ui.Ui;

import java.time.LocalDateTime;

/**
 * Contains the method to read, execute and respond to user commands.
 */
public class Parser {
	/**
	 * Executes a command string on the task list.
	 *
	 * @param input   user input
	 * @param tasks   TaskList object
	 * @param ui      Ui object
	 * @param storage Storage object
	 * @return true if the program should exit, false otherwise
	 */
	public static boolean execute(String input, TaskList tasks, Ui ui, Storage storage) {
		try {
			if (input.equals("bye")) {
				ui.show("Bye! See you soon.");
				return true;
			}

			if (input.equals("list")) {
				for (int i = 0; i < tasks.size(); i++) {
					ui.show(String.format("%d. %s", i + 1, tasks.getTask(i).toString()));
				}
				return false;
			}

			if (input.startsWith("mark ")) {
				int index = Integer.parseInt(input.substring(5).trim()) - 1;
				Task t = tasks.getTask(index);
				t.mark();
				ui.show("I've marked this task as done. Please check.");
				ui.show(t.toString());
				storage.save(tasks.getTasks());
				return false;
			}

			if (input.startsWith("unmark ")) {
				int index = Integer.parseInt(input.substring(7).trim()) - 1;
				Task t = tasks.getTask(index);
				t.unmark();
				ui.show("I've marked this task as not done. Please check.");
				ui.show(t.toString());
				storage.save(tasks.getTasks());
				return false;
			}

			if (input.startsWith("delete ")) {
				int index = Integer.parseInt(input.substring(7).trim()) - 1;
				Task t = tasks.removeTask(index);
				ui.show("Noted. I've removed this task:");
				ui.show("  " + t.toString());
				storage.save(tasks.getTasks());
				return false;
			}

			if (input.startsWith("todo ")) {
				String desc = input.substring(5).trim();
				Task t = new Todo(desc);
				tasks.addTask(t);
				ui.show("added todo: " + desc);
				storage.save(tasks.getTasks());
				return false;
			}

			if (input.startsWith("deadline ")) {
				String rest = input.substring(9).trim();
				int at = rest.indexOf("/by");
				String desc = rest.substring(0, at).trim();
				String byStr = rest.substring(at + 3).trim();
				LocalDateTime by = DateTimeParser.parse(byStr);
				Task t = new Deadline(desc, by);
				tasks.addTask(t);
				ui.show("added Deadline: " + desc + " by " + by);
				storage.save(tasks.getTasks());
				return false;
			}

			if (input.startsWith("event ")) {
				String rest = input.substring(6).trim();
				int fromAt = rest.indexOf("/from");
				int toAt = rest.indexOf("/to");
				String desc = rest.substring(0, fromAt).trim();
				String fromStr = rest.substring(fromAt + 5, toAt).trim();
				String toStr = rest.substring(toAt + 3).trim();
				LocalDateTime from = DateTimeParser.parse(fromStr);
				LocalDateTime to = DateTimeParser.parse(toStr);
				Task t = new Event(desc, from, to);
				tasks.addTask(t);
				ui.show("added Event: " + desc + " from " + from + " to " + to);
				storage.save(tasks.getTasks());
				return false;
			}

			ui.show("I didn't get that command. Try: list / todo / deadline / event / mark / unmark / bye");
		} catch (Exception e) {
			ui.show("Invalid command or task number. Please try again.");
		}
		return false;
	}
}