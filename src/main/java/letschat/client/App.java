package letschat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
    private static String token = "";

    public static void main(String[] args) {
        System.out.println("LET'S CHAT\n======================");

        server = new ServerConnection(host, port);

        try {
            System.out.printf("Connect to %s/%d...\n", host, port);
            server.connect();
            System.out.println("Type \":h\" for help!");
            String command = "";
            while (true) {
                System.out.print("$> ");
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
                        showUserChat();
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
        } catch (SocketTimeoutException e) {
            System.out.println("Connection timeout!");
        } catch (Exception e) {
            System.out.println("An error occurs!");
            e.printStackTrace();
        }
    }

    public static void showHelp() {
        System.out.println("Show help\n");
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

        byte[] byteResponse = new byte[4096];
        server.getInputStream().read(byteResponse);

        ResponseProtos.Response response = ResponseProtos.Response.parseFrom(byteResponse);

        switch (response.getType()) {
            case 0:
                System.out.println("Successfully!");
                break;
            default:
                System.out.println("Can not login!");
        }
    }

    public static void showUserChat() {
        System.out.print("User name: ");
    }
}
