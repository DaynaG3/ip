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
                System.out.println("Here are the tasks in your list:");
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
                            System.out.println("OK, I've marked this task as not done yet:");
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
                            System.out.println("Nice! I've marked this task as done:");
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
            } else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = new Task(command);
                    taskCount++;
                    System.out.println("added: " + command);
                }
            }

            System.out.println(DIVIDER);
        }
    }
}
