package talkist.parser;

import talkist.storage.Storage;
import talkist.task.TaskList;
import talkist.task.model.Deadline;
import talkist.task.model.Event;
import talkist.task.model.Task;
import talkist.task.model.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Contains the method to read and respond to user commands for GUI usage.
 * Returns response strings instead of printing to console.
 */
public class Parser {

	/**
	 * Parses user input and executes the command on the task list.
	 * Returns a string response for GUI display.
	 *
	 * @param input   user input
	 * @param tasks   TaskList object
	 * @param storage Storage object
	 * @return the response string to display
	 */
	public static String parse(String input, TaskList tasks, Storage storage) {
		StringBuilder response = new StringBuilder();

		try {
			if (input.equals("bye")) {
				response.append("Bye! See you soon.");
				return response.toString();
			}

			if (input.equals("list")) {
				for (int i = 0; i < tasks.size(); i++) {
					response.append(String.format("%d. %s\n", i + 1, tasks.getTask(i).toString()));
				}
				return response.toString();
			}

			if (input.startsWith("mark ")) {
				int index = Integer.parseInt(input.substring(5).trim()) - 1;
				Task t = tasks.getTask(index);
				t.mark();
				response.append("I've marked this task as done. Please check.\n").append(t.toString());
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("unmark ")) {
				int index = Integer.parseInt(input.substring(7).trim()) - 1;
				Task t = tasks.getTask(index);
				t.unmark();
				response.append("I've marked this task as not done. Please check.\n").append(t.toString());
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("delete ")) {
				int index = Integer.parseInt(input.substring(7).trim()) - 1;
				Task t = tasks.removeTask(index);
				response.append("Noted. I've removed this task:\n  ").append(t.toString());
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("todo ")) {
				String desc = input.substring(5).trim();
				Task t = new Todo(desc);
				tasks.addTask(t);
				response.append("Added todo: ").append(desc);
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("deadline ")) {
				String rest = input.substring(9).trim();
				int at = rest.indexOf("/by");
				String desc = rest.substring(0, at).trim();
				String byStr = rest.substring(at + 3).trim();
				LocalDateTime by = DateTimeParser.parse(byStr);
				Task t = new Deadline(desc, by);
				tasks.addTask(t);
				response.append("Added Deadline: ").append(desc).append(" by ").append(by);
				storage.save(tasks.getTasks());
				return response.toString();
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
				response.append("Added Event: ").append(desc).append(" from ").append(from).append(" to ").append(to);
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("find ")) {
				String keyword = input.substring(5).trim();
				ArrayList<Task> matches = tasks.find(keyword);
				if (matches.isEmpty()) {
					response.append("No tasks found matching: ").append(keyword);
				} else {
					response.append("Here are the matching tasks in your list:\n");
					for (int i = 0; i < matches.size(); i++) {
						response.append(String.format("%d. %s\n", i + 1, matches.get(i).toString()));
					}
				}
				return response.toString();
			}

			response.append("I didn't get that command. Try: list / todo / deadline / event / mark / unmark / bye");
		} catch (Exception e) {
			response.append("Invalid command or task number. Please try again.");
		}

		return response.toString();
	}
}
