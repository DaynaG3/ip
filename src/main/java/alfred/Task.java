package alfred;

/**
 * Represents a task and its completion status.
 */
public class Task {

    private final String description;
    private final TaskType type;
    private final String deadline;
    private final String start;
    private final String end;
    private boolean isDone;

    /**
     * Creates an incomplete todo with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this(TaskType.TODO, description, null, null, null);
    }

    /**
     * Creates an incomplete task with its type-specific date and time details.
     */
    private Task(TaskType type, String description, String deadline, String start, String end) {
        this.type = type;
        this.description = description;
        this.deadline = deadline;
        this.start = start;
        this.end = end;
        this.isDone = false;
    }

    /**
     * Creates a todo without a date or time.
     *
     * @param description Description of the todo.
     * @return Newly created todo.
     */
    public static Task createTodo(String description) {
        return new Task(description);
    }

    /**
     * Creates a deadline with a date or time to be completed by.
     *
     * @param description Description of the deadline.
     * @param deadline Date or time by which the task must be completed.
     * @return Newly created deadline.
     */
    public static Task createDeadline(String description, String deadline) {
        return new Task(TaskType.DEADLINE, description, deadline, null, null);
    }

    /**
     * Creates an event with a start and end date or time.
     *
     * @param description Description of the event.
     * @param start Start date or time of the event.
     * @param end End date or time of the event.
     * @return Newly created event.
     */
    public static Task createEvent(String description, String start, String end) {
        return new Task(TaskType.EVENT, description, null, start, end);
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the icon representing the task's completion status.
     *
     * @return {@code X} if completed, or a space if not completed.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        String taskDetails = "";

        switch (type) {
        case DEADLINE:
            taskDetails = " (by: " + deadline + ")";
            break;
        case EVENT:
            taskDetails = " (from: " + start + " to: " + end + ")";
            break;
        case TODO:
            break;
        default:
            throw new IllegalStateException("Unknown task type: " + type);
        }

        return "[" + type.getSymbol() + "][" + getStatusIcon() + "] " + description + taskDetails;
    }
}
