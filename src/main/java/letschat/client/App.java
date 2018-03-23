package letschat.client;

import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class App {
    private static Scanner in = new Scanner(System.in);
    private static String host = "localhost";
    private static int port = 8080;
    private static ServerConnection server = null;
    private static boolean isLogin = false;
    private static String token = "";
    private static String currentUserName = "";

    public static void main(String[] args) {
        System.out.println("LET'S CHAT\n======================");

        server = new ServerConnection(host, port);

        try {
            System.out.printf("Connect to %s/%d...\n", host, port);
            server.connect();
            System.out.println("Type \":h\" for help!");
            String command = "";
            while (true) {
                System.out.print("~> ");
                command = in.nextLine().trim();

                if (command.equals(":q")) {
                    System.out.println("Good bye!");
                    server.close();
                    break;
                }

                switch (command) {
                    case ":h":
                        showHelp();
                        break;
                    case ":lg":
                        showLogin();
                        break;
                    case ":ctu":
                        if (isLogin) {
                            showUserChat();
                        } else {
                            System.out.println("Please login first!\n");
                        }
                        break;
                    case ":su":
                        showSignUp();
                        break;
                    case ":b":
                        System.out.println("Nothing to back!\n");
                        break;
                    case "":
                        break;
                    default:
                        System.out.println("Invalid command. Type \":h\" for help!\n");
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknown host!");
        } catch (Exception e) {
            System.out.println("An error occurs!");
            e.printStackTrace();
        }
    }

    public static void showHelp() {
        System.out.println("~ Commands:");
        System.out.printf("\t%-10s%s\n", ":h", "Show help");
        System.out.printf("\t%-10s%s\n", ":lg", "Login");
        System.out.printf("\t%-10s%s\n", ":su", "Sign up");
        System.out.printf("\t%-10s%s\n", ":ctu", "Chat to a user");
        System.out.printf("\t%-10s%s\n", ":cc", "Create channel (under construction)");
        System.out.printf("\t%-10s%s\n", ":jc", "Join channel (under construction)");
        System.out.printf("\t%-10s%s\n", ":sc", "See list of channel you are following (under construction)");
        System.out.printf("\t%-10s%s\n", ":ctc", "Chat to channel (under construction)");
        System.out.printf("\t%-10s%s\n", ":b", "Back");
        System.out.printf("\t%-10s%s\n", ":q", "Quit");
        System.out.println();
    }

    public static void showLogin() throws IOException, SocketTimeoutException {
        System.out.print("User: ");
        String userName = in.nextLine().trim();
        System.out.print("Password: ");
        String password = in.nextLine().trim();

        RequestProtos.Request request = RequestProtos.Request.newBuilder()
                .setType(0)
                .setUser(RequestProtos.User.newBuilder()
                        .setUsername(userName)
                        .setPassword(password)
                        .build())
                .build();

        System.out.printf("Logging in user [%s]...\n", userName);

        request.writeTo(server.getOutputStream());
        try {

            byte[] byteResponse = new byte[4096];
            server.getInputStream().read(byteResponse);

            ResponseProtos.Response response = ResponseProtos.Response.parseFrom(byteResponse);

            switch (response.getType()) {
                case 0:
                    if (response.getCode() == 0) {
                        System.out.println("Successfully!");
                        isLogin = true;
                        token = response.getToken();
                        currentUserName = userName;
                    } else {
                        System.out.println("Can not login!");
                    }
                    break;
                default:
                    System.out.println("Can not login!");
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Connection timeout!\n");
        }
    }

    public static void showSignUp() throws IOException {
        System.out.print("User: ");
        String userName = in.nextLine().trim();
        System.out.print("Password: ");
        String password = in.nextLine().trim();

        RequestProtos.Request request = RequestProtos.Request.newBuilder()
                .setType(1)
                .setUser(RequestProtos.User.newBuilder()
                        .setUsername(userName)
                        .setPassword(password)
                        .build())
                .build();

        System.out.printf("Signing up for user [%s]...\n", userName);

        request.writeTo(server.getOutputStream());

        byte[] byteResponse = new byte[4096];
        server.getInputStream().read(byteResponse);

        ResponseProtos.Response response = ResponseProtos.Response.parseFrom(byteResponse);

        switch (response.getType()) {
            case 1:
                if (response.getCode() == 0) {
                    System.out.println("Successfully! Please login!");
                } else {
                    System.out.println("Can not sign up!");
                }
                break;
            default:
                System.out.println("Can not sign up!");
        }
    }

    public static void showUserChat() {
        System.out.print("User name: ");
        String userName = in.nextLine().trim();
        System.out.printf("Checking user name [%s] ...\n", userName);

        RequestProtos.Request.Builder requestBuilder = RequestProtos.Request.newBuilder();
        ResponseProtos.Response.Builder responseBuilder = ResponseProtos.Response.newBuilder();
        RequestProtos.ChatToUser.Builder chatBuilder = RequestProtos.ChatToUser.newBuilder();

        RequestProtos.Request request = requestBuilder
                .setType(4)
                .setName(userName)
                .build();
        try {
            request.writeTo(server.getOutputStream());

            byte[] byteResponse = new byte[4096];
            server.getInputStream().read(byteResponse);

            ResponseProtos.Response response = ResponseProtos.Response.parseFrom(byteResponse);

            if (!(response.getType() == 4 && response.getCode() == 0)) {
                throw new Exception("User name is invalid!");
            }

            System.out.println("Let's start!");
            String message = "";
            RequestProtos.Request requestMsg = null;
            while (true) {
                System.out.printf("[%s]~> ", currentUserName);
                message = in.nextLine();

                requestMsg = requestBuilder
                        .setType(3)
                        .setChattouser(chatBuilder.setFromuser(currentUserName)
                                .setTouser(userName)
                                .setMessage(message)
                                .setTime(System.currentTimeMillis())
                                .build())
                        .setToken(token)
                        .build();
                requestMsg.writeTo(server.getOutputStream());

                byteResponse = new byte[4096];
                server.getInputStream().read(byteResponse);

                response = ResponseProtos.Response.parseFrom(byteResponse);

                if (!(response.getType() == 3 && response.getCode() == 0)) {
                    throw new Exception("Message sent unsuccessfully!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
