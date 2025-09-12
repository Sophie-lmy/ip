package talkist;

import java.util.ArrayList;

import talkist.parser.Parser;
import talkist.storage.Storage;
import talkist.task.TaskList;
import talkist.task.model.Task;

/**
 * Core logic of the Talkist chatbot.
 * Provides method to get response for user input.
 */
public class Talkist {

    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor initializes Talkist with storage file and loads existing tasks.
     * @param filePath path to the data file
     */
    public Talkist(String filePath) {
        storage = new Storage(filePath);
        ArrayList<Task> loadedTasks = storage.load();
        tasks = new TaskList(loadedTasks);
    }

    /**
     * Processes user input and returns Talkist's response.
     * @param input user's input string
     * @return response string from Talkist
     */
    public String getResponse(String input) {
        // Parser.execute returns a boolean exit flag in original version,
        // but here we only want the textual response
        return Parser.parse(input, tasks, storage);
    }
}