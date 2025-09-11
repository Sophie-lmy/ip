package talkist;

import java.util.ArrayList;

import talkist.parser.Parser;
import talkist.storage.Storage;
import talkist.task.TaskList;
import talkist.task.model.Task;
import talkist.ui.Ui;

public class Talkist {
    public static void main(String[] args) {
        Storage storage = new Storage("./data/Talkist.txt");
        ArrayList<Task> loadedTasks = storage.load();

        TaskList tasks = new TaskList(loadedTasks);
        Ui ui = new Ui();

        ui.show("This is Talkist, a chat bot based on DUKE. How can I help you?");

        boolean exit = false;
        while (!exit) {
            String input = ui.readCommand();
            exit = Parser.execute(input, tasks, ui, storage);
        }

        ui.close();
    }
}
