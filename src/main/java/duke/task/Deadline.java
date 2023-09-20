package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;



/**
 * The task.Deadline class represents a task with a specific deadline.
 * It is a subclass of the Task class.
 */
public class Deadline extends Task {
    private static DateTimeFormatter formatterToTxtString = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected String by;
    protected LocalDateTime dateTime;

    /**
     * Constructs a new Deadline ask with a description and deadline date/time.
     *
     * @param description The description of the duke.task.
     * @param by          The deadline date/time in the format "dd/MM/yyyy HHmm".
     * @throws DukeException If the provided date/time format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            this.dateTime = getDateTime(by);
        } catch (DukeException e) {
            throw new DukeException("☹ OOPS!!! Invalid Date/Time format, use DD/MM/YYYY HHmm");
        }
    }

    /**
     * Parses the provided date/time string into a LocalDateTime object.
     *
     * @param dateTime The date/time string in the format "dd/MM/yyyy HHmm".
     * @return A LocalDateTime object representing the parsed date/time.
     * @throws DukeException If the provided date/time format is invalid.
     */
    public static LocalDateTime getDateTime(String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, formatterToTxtString);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Invalid Date/Time format, use DD/MM/YYYY HHmm");
        }
    }

    /**
     * Returns a formatted string representation of the Deadline task for saving to a file.
     *
     * @return A string in the format "[D] | [X] | Description | duke.task.Deadline".
     */
    @Override
    public String toTxtString() {
        return "[D] | [" + (this.isDone ? "X" : " ") + "] | " + this.description + " | "
                + this.dateTime.format(formatterToTxtString);
    }

    /**
     * Returns a string representation of the Deadline task for displaying to the user.
     *
     * @return A string in the format "[D] [Status] Description (by: Deadline)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatterToString = DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(formatterToString) + ")";
    }
}

