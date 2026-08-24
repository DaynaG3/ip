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

    public static void main(String[] args) {
        System.out.println(DIVIDER + "\n");
        System.out.println(banner + "\n");
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
            }

            System.out.println('\t' + command);
            System.out.println(DIVIDER);
        }
    }
}
