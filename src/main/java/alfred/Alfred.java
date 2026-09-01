package alfred;

import java.util.Scanner;

/**
 * Runs the Alfred command-line chatbot.
 */
public class Alfred {

    private static final int MAX_TASKS = 100;
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BANNER
            = "         █████╗ ██╗     ███████╗██████╗ ███████╗██████╗\n"
            + "        ██╔══██╗██║     ██╔════╝██╔══██╗██╔════╝██╔══██╗\n"
            + "        ███████║██║     █████╗  ██████╔╝█████╗  ██║  ██║\n"
            + "        ██╔══██║██║     ██╔══╝  ██╔══██╗██╔══╝  ██║  ██║\n"
            + "        ██║  ██║███████╗██║     ██║  ██║███████╗██████╔╝\n"
            + "        ╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝  ╚═╝╚══════╝╚═════╝ ";
    private static final String BAT_LOGO
            = "              *         *      *         *               \n"
            + "          ***          **********          ***           \n"
            + "       *****           **********           *****        \n"
            + "     *******           **********           *******      \n"
            + "   **********         ************         **********    \n"
            + "  ****************************************************   \n"
            + " ******************************************************  \n"
            + "******************************************************** \n"
            + "******************************************************** \n"
            + "******************************************************** \n"
            + " ******************************************************  \n"
            + "  ********      ************************      ********   \n"
            + "   *******       *     *********      *       *******    \n"
            + "     ******             *******              ******      \n"
            + "       *****             *****              *****        \n"
            + "          ***             ***              ***           \n"
            + "            **             *              **             \n";
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    /**
     * Starts Alfred and processes commands until the user exits.
     *
     * @param args Command-line arguments. Alfred does not use them.
     */
    public static void main(String[] args) {
        System.out.println(DIVIDER + "\n");
        System.out.println(BANNER);
        System.out.println(DIVIDER + "\n");
        System.out.println("Welcome home Master Wayne.\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println(DIVIDER);

            if (command.equals("bye")) {
                System.out.println("Good luck Master Wayne.\n");
                System.out.println(BAT_LOGO);
                System.out.println(DIVIDER);
                break;
            } else if (command.equals("list")) {
                System.out.println("These are your tasks Master Wayne:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("unmark")) {
                String[] parts = command.trim().split("\\s+");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            int taskIndex = taskNumber - 1;
                            Task task = tasks[taskIndex];
                            task.markAsNotDone();
                            System.out.println("Alright Master Wayne, I have unmarked this task as requested:");
                            System.out.println("  " + task);
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Please provide a valid task number.");
                    }
                } else {
                    System.out.println("Please provide a task number to mark as not done.");
                }
            } else if (command.startsWith("mark")) {
                String[] parts = command.trim().split("\\s+");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            int taskIndex = taskNumber - 1;
                            Task task = tasks[taskIndex];
                            task.markAsDone();
                            System.out.println("Excellent work Master Wayne! I've marked this task as done:");
                            System.out.println("  " + task);
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Please provide a valid task number.");
                    }
                } else {
                    System.out.println("Please provide a task number to mark as done.");
                }
            } else if (command.equals("todo") || command.startsWith("todo ")) {
                addTodo(command);
            } else if (command.equals("deadline") || command.startsWith("deadline ")) {
                addDeadline(command);
            } else if (command.equals("event") || command.startsWith("event ")) {
                addEvent(command);
            } else {
                System.out.println("Pardon me Master Wayne, I did not quite get that.");
            }

            System.out.println(DIVIDER);
        }
    }

    /**
     * Parses and adds a todo command if it contains a description.
     *
     * @param command Full command entered by the user.
     */
    private static void addTodo(String command) {
        String description = command.substring("todo".length()).trim();
        if (description.isEmpty()) {
            System.out.println("The description of a todo cannot be empty.");
            return;
        }

        addTask(Task.createTodo(description));
    }

    /**
     * Parses and adds a deadline command containing a description and
     * {@code /by} value.
     *
     * @param command Full command entered by the user.
     */
    private static void addDeadline(String command) {
        String arguments = command.substring("deadline".length()).trim();
        int byPosition = arguments.indexOf("/by");
        if (byPosition < 0) {
            printDeadlineError("the /by marker is missing");
            return;
        }

        String description = arguments.substring(0, byPosition).trim();
        String deadline = arguments.substring(byPosition + "/by".length()).trim();
        if (description.isEmpty()) {
            printDeadlineError("the task description is missing");
            return;
        }
        if (deadline.isEmpty()) {
            printDeadlineError("the deadline date or time is missing");
            return;
        }

        addTask(Task.createDeadline(description, deadline));
    }

    /**
     * Displays a detailed deadline error with the required command format.
     *
     * @param reason Explanation of why the deadline command is invalid.
     */
    private static void printDeadlineError(String reason) {
        System.out.println("Unable to add the deadline: " + reason + ".");
        System.out.println("Use this format: deadline <description> /by <date or time>");
        System.out.println("Example: deadline return book /by tomorrow");
    }

    /**
     * Parses and adds an event command containing a description, start, and end
     * value.
     *
     * @param command Full command entered by the user.
     */
    private static void addEvent(String command) {
        String arguments = command.substring("event".length()).trim();
        int fromPosition = arguments.indexOf("/from");
        if (fromPosition < 0) {
            printEventError("the /from marker is missing");
            return;
        }

        int toPosition = arguments.indexOf("/to", fromPosition + "/from".length());
        if (toPosition < 0) {
            printEventError("the /to marker is missing");
            return;
        }

        String description = arguments.substring(0, fromPosition).trim();
        String start = arguments.substring(fromPosition + "/from".length(), toPosition).trim();
        String end = arguments.substring(toPosition + "/to".length()).trim();
        if (description.isEmpty()) {
            printEventError("the task description is missing");
            return;
        }
        if (start.isEmpty()) {
            printEventError("the start date or time is missing");
            return;
        }
        if (end.isEmpty()) {
            printEventError("the end date or time is missing");
            return;
        }

        addTask(Task.createEvent(description, start, end));
    }

    /**
     * Displays a detailed event error with the required command format.
     *
     * @param reason Explanation of why the event command is invalid.
     */
    private static void printEventError(String reason) {
        System.out.println("Unable to add the event: " + reason + ".");
        System.out.println("Use this format: event <description> /from <start> /to <end>");
        System.out.println("Example: event project meeting /from Mon 2pm /to 4pm");
    }

    /**
     * Adds a task to the task list and displays the updated task count.
     *
     * @param task Task to add.
     */
    private static void addTask(Task task) {
        if (taskCount >= tasks.length) {
            System.out.println("The task list is full.");
            return;
        }

        tasks[taskCount] = task;
        taskCount++;
        System.out.println("Understood Master Wayne, I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}
