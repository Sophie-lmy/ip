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
 *
 * AI-assisted: Used ChatGPT to provide possible response patterns with different personality.
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
				return "Sayonara~ see you soon! 👋";
			}

			if (input.equals("list")) {
				for (int i = 0; i < tasks.size(); i++) {
					response.append(String.format("%d. %s\n", i + 1, tasks.getTask(i).toString()));
				}
				response.append("All done! Hope it looks cute ✨");
				return response.toString();
			}

			if (input.startsWith("mark ")) {
				int index = Integer.parseInt(input.substring(5).trim()) - 1;
				assert index >= 0 && index < tasks.size() : "Mark index out of range";
				Task t = tasks.getTask(index);
				t.mark();
				response.append("Yay! I marked this task as done ✅\n").append(t.toString());
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("unmark ")) {
				int index = Integer.parseInt(input.substring(7).trim()) - 1;
				assert index >= 0 && index < tasks.size() : "Unmark index out of range";
				Task t = tasks.getTask(index);
				t.unmark();
				response.append("Okay~ this task is now not done\n").append(t.toString());
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("delete ")) {
				int index = Integer.parseInt(input.substring(7).trim()) - 1;
				assert index >= 0 && index < tasks.size() : "Delete index out of range";
				Task t = tasks.removeTask(index);
				response.append("Oops~ I removed this task\n  ").append(t.toString());
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("todo ")) {
				String desc = input.substring(5).trim();
				Task t = new Todo(desc);
				tasks.addTask(t);
				response.append("Added a new todo 📌\n").append(desc);
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
				response.append("Deadline task added ⏰ Hurry Up!\n").append(desc).append(" by ").append(by);
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
				assert !from.isAfter(to) : "Event start time must be before or equal to end time";
				Task t = new Event(desc, from, to);
				tasks.addTask(t);
				response.append("New Event 🎉🥳🎉\n").append(desc).append(" from ").append(from).append(" to ").append(to);
				storage.save(tasks.getTasks());
				return response.toString();
			}

			if (input.startsWith("find ")) {
				String keyword = input.substring(5).trim();
				ArrayList<Task> matches = tasks.find(keyword);
				if (matches.isEmpty()) {
					response.append("Hmm... no tasks found for: ").append(keyword).append(" 😿");
				} else {
					response.append("Here are the matching tasks in your list:\n");
					for (int i = 0; i < matches.size(); i++) {
						response.append(String.format("%d. %s\n", i + 1, matches.get(i).toString()));
					}
				}
				return response.toString();
			}

			if (input.startsWith("tag ")) {
				String[] parts = input.substring(4).trim().split(" ", 2);
				int index = Integer.parseInt(parts[0]) - 1;
				String tag = parts[1].trim();
				assert index >= 0 && index < tasks.size() : "Tag index out of range";

				Task task = tasks.getTask(index);
				task.setTag(tag);
				response.append("Got it! I've tagged this task:\n")
						.append(String.format("%d. %s", index + 1, task.toString()));
				storage.save(tasks.getTasks());
				return response.toString();
			}

			response.append("I didn't get that command. Try: list / todo / deadline / event / mark / unmark / bye");
		} catch (Exception e) {
			response.append("Invalid command or task number. Please try again.");
		}

		return response.toString();
	}
}
