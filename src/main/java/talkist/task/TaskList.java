package talkist.task;

import java.util.ArrayList;

import talkist.task.model.Task;

public class TaskList {
	private final ArrayList<Task> tasks;

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void addTask(Task t) {
		tasks.add(t);
	}

	public Task removeTask(int index) {
		return tasks.remove(index);
	}

	public Task getTask(int index) {
		return tasks.get(index);
	}

	public int size() {
		return tasks.size();
	}
}