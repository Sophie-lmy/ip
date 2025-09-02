package talkist.task;

import java.util.ArrayList;
import talkist.task.model.Task;

/**
 * Represents a list of tasks. A <code>TaskList</code> can store, retrieve,
 * add, and remove tasks.
 */
public class TaskList {
	private final ArrayList<Task> tasks;

	/**
	 * Creates a TaskList with the given list of tasks.
	 *
	 * @param tasks an ArrayList of Task objects
	 */
	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Returns the list of tasks.
	 *
	 * @return ArrayList of Task objects
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	/**
	 * Adds a task to the list.
	 *
	 * @param task the Task object to add
	 */
	public void addTask(Task task) {
		tasks.add(task);
	}

	/**
	 * Removes the task at the specified index from the list.
	 *
	 * @param index the index of the task to remove
	 * @return the removed Task object
	 */
	public Task removeTask(int index) {
		return tasks.remove(index);
	}

	/**
	 * Retrieves the task at the specified index.
	 *
	 * @param index the index of the task to retrieve
	 * @return the Task object at the given index
	 */
	public Task getTask(int index) {
		return tasks.get(index);
	}

	/**
	 * Returns the number of tasks in the list.
	 *
	 * @return size of the task list
	 */
	public int size() {
		return tasks.size();
	}
}
