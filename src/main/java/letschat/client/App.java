package letschat.client;

import java.util.Scanner;

public class App {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("LET'S CHAT\n======================");
        System.out.println("Type \":h\" for help!");
        String command = "";
        while (true) {
            System.out.print("$> ");
            command = in.nextLine().trim();

            if (command.equals(":q")) {
                System.out.println("Good bye!");
                break;
            }

            switch(command) {
                case ":h":
                    showHelp();
                    break;
                case ":lg":
                    showLogin();
                    break;
                case ":ctu":
                    showUserChat();
                    break;
                case ":b":
                    System.out.println("Nothing to back!");
                    break;
                default:
                    System.out.println("Invalid command. Type \":h\" for help!");
            }
        }
    }

    public static void showHelp() {
        System.out.println("Show help\n");
    }

    public static void showLogin() {
        System.out.print("User: ");
        String userName = in.nextLine().trim();
        System.out.print("Password: ");
        String password = in.nextLine().trim();

        //login
    }

    public static void showUserChat() {
        System.out.print("User name: ");
    }
}
