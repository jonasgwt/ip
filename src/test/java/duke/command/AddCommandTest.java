package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    private TaskList tasks;
    private Storage storage;
    private final Ui ui;

    public AddCommandTest() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        try {
            this.storage = new Storage();
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void addTodoTest() {
        try {
            Command command = new AddCommand("T", "test test", null);
            command.execute(tasks, ui, storage);
            Task task = tasks.get(0);
            assertEquals(new ToDo("test test").toString(), task.toString());
        } catch (DukeException e) {
            fail(e);
        }
        tasks = new TaskList();
    }

    @Test
    public void addDeadlineTest() {
        try {
            Command command = new AddCommand("D", "test test", LocalDate.parse("2019-10-15"));
            command.execute(tasks, ui, storage);
            Task task = tasks.get(0);
            assertEquals(new Deadline("test test", LocalDate.parse("2019-10-15")).toString(), task.toString());
        } catch (DukeException e) {
            fail(e);
        }
        tasks = new TaskList();
    }

    @Test
    public void addEventTest() {
        try {
            Command command = new AddCommand("E", "test test", LocalDate.parse("2019-10-15"));
            command.execute(tasks, ui, storage);
            Task task = tasks.get(0);
            assertEquals(new Event("test test", LocalDate.parse("2019-10-15")).toString(), task.toString());
        } catch (DukeException e) {
            fail(e);
        }
        tasks = new TaskList();
    }

}
