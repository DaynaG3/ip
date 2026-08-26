import java.util.Scanner;

/**
 * Runs the Alfred command-line chatbot.
 */
public class Alfred {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String banner = 
        "         █████╗ ██╗     ███████╗██████╗ ███████╗██████╗\n" + 
        "        ██╔══██╗██║     ██╔════╝██╔══██╗██╔════╝██╔══██╗\n" +
        "        ███████║██║     █████╗  ██████╔╝█████╗  ██║  ██║\n" +
        "        ██╔══██║██║     ██╔══╝  ██╔══██╗██╔══╝  ██║  ██║\n" +
        "        ██║  ██║███████╗██║     ██║  ██║███████╗██████╔╝\n" +
        "        ╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝  ╚═╝╚══════╝╚═════╝ ";
    private static final String BAT_LOGO =
              "              *         *      *         *               \n"
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
    private static final Task[] taskList = new Task[100]; // Array to store tasks
    private static int taskCount = 0; // Counter to keep track of the number of tasks

    public static void main(String[] args) {
        System.out.println(DIVIDER + "\n");
        System.out.println(banner);
        System.out.println(DIVIDER + "\n");
        System.out.println("Welcome home Master Wayne.\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println(DIVIDER);

            //exit the program
            if (command.equals("bye")) {
                System.out.println("Good luck Master Wayne.\n");
                System.out.println(BAT_LOGO);
                System.out.println(DIVIDER);
                break;
            }

            //Level 2. list out tasks
            else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + taskList[i]);
                }
            }

            //Level 3. mark a task as not completed
            else if (command.startsWith("unmark")) {
                String[] parts = command.trim().split("\\s+");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            int taskIndex = taskNumber - 1;
                            Task task = taskList[taskIndex];
                            task.markAsNotDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + task);
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid task number.");
                    }
                } else {
                    System.out.println("Please provide a task number to mark as not done.");
                }
            }

            //Level 3. mark a task as completed
            else if (command.startsWith("mark")) {
                String[] parts = command.trim().split("\\s+");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            int taskIndex = taskNumber - 1;
                            Task task = taskList[taskIndex];
                            task.markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + task);
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid task number.");
                    }
                } else {
                    System.out.println("Please provide a task number to mark as done.");
                }
            }

            //Level 2. add a new task
            else {
                if (taskCount < taskList.length) {
                    taskList[taskCount] = new Task(command);
                    taskCount++;
                    //Level 1. Echo the command back to the user
                    System.out.println("added: " + command);
                }
            }

            System.out.println(DIVIDER);

        }
    }
}
