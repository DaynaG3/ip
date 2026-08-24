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
    private static final String[] taskList = new String[100]; // Array to store list of tasks
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
            else if (command.equals("list")){
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
            }

            //Level 2. add a new task
            else {
                if (taskCount < taskList.length) {
                    taskList[taskCount] = command;
                    taskCount++;
                    //Level 1. Echo the command back to the user
                    System.out.println("added: " + command);
                }
            }

            System.out.println(DIVIDER);

        }
    }
}
