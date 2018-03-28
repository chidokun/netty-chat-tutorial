package letschat.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import letschat.common.Constant;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static letschat.common.Constant.NOTHING;

public class ClientHandler extends SimpleChannelInboundHandler<ResponseProtos.Response> {
    private static DateFormat fm = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]");
    private static Scanner in = new Scanner(System.in);
    private static RequestProtos.Request.Builder requestBuilder = RequestProtos.Request.newBuilder();
    private static String token = "";

    private static Channel channel;
    private static String host = "localhost";
    private static int port = 8080;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ResponseProtos.Response response) {

        switch (response.getType()) {
            case SUCCESS:
                System.out.println("Successfully!");
                break;
            case NOTLOGIN:
                System.out.println("Please login first!");
                break;
            case TOKEN:
                System.out.println("Login successfully!");
                token = response.getMessage().getContent();
                break;
            case DUPLICATE:

                System.out.println("Duplicate name!");
                break;
            case FAILURE:

                System.out.println("from: " + response.getMessage().getFrom() + "\n\t" + response.getMessage().getContent());
                break;
            case MESSAGE:
                String time = "";
                if (response.getTime() != 0)
                    time = fm.format(response.getTime()) + " " + response.getMessage().getFrom() + ": ";
                System.out.println(time +
                        response.getMessage().getContent());
                break;
            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    public static boolean mainProgram(String command) {
        try {

            if (command.equalsIgnoreCase(":q")) {
                System.out.println("Good bye!");
//                workerGroup.shutdownGracefully();
//                System.exit(0);
                return false;
            }

            switch (command) {
                case Constant.HELP:
                    showHelp();
                    break;
                case Constant.LOGIN:
                    showLogin();
                    break;
                case Constant.CHAT:
                    showUserChat();
                    break;
                case Constant.LOGOUT:
                    logOut();
                    break;
                case Constant.SIGNUP:
                    showSignUp();
                    break;
                case Constant.BACK:
                    System.out.println("Nothing to back!\n");
                    break;
                case Constant.GETMESSAGE:
                    getMessages();
                    break;
                case Constant.JOINCHANNEL:
                    joinChannel();
                    break;
                case Constant.LISTCHANNEL:
                    listChannel();
                    break;
                case Constant.EXITCHANNEL:
                    exitChannel();
                    break;
                case Constant.CHATCHANNEL:
                    chatChannel();
                    break;
                case Constant.NOTHING:
                    break;
                default:
                    System.out.println("Invalid command. Type \":h\" for help!\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void getMessages() {
        System.out.println("Get messages");
        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.GETMESSAGE)//5
                .setToken(token)
                .build();

        try {
            channel.writeAndFlush(request).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean connect(Bootstrap b) {
        try {
            System.out.print("Host: ");
            String _host = in.nextLine();
            if (_host.isEmpty()) {
                host = "localhost";
                System.out.println("Default host: " + host);
            } else {
                host = _host;
            }

            System.out.print("Port: ");
            String _port = in.nextLine();
            if (_port.isEmpty()) {
                port = 8080;
                System.out.println("Default port: " + port);
            } else {
                port = Integer.parseInt(_port);
            }

            ChannelFuture future = b.connect(host, port);
            System.out.printf("Connecting to %s/%d ... ", host, port);

            if (future.await().isSuccess()) {
                channel = future.sync().channel();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void showHelp() {
        System.out.println("~ Commands:");
        System.out.printf("\t%-10s%s\n", ":h", "Show help");
        System.out.printf("\t%-10s%s\n", ":lg", "Login");
        System.out.printf("\t%-10s%s\n", ":lo", "Logout");
        System.out.printf("\t%-10s%s\n", ":su", "Sign up");
        System.out.printf("\t%-10s%s\n", ":c", "Chat to a user or channel");
        System.out.printf("\t%-10s%s\n", ":gm", "Get message when offline");
        System.out.printf("\t%-10s%s\n", ":j", "Join a channel");
        System.out.printf("\t%-10s%s\n", ":l", "List of current channels");
        System.out.printf("\t%-10s%s\n", ":x", "Exit a channel");
        System.out.printf("\t%-10s%s\n", ":b", "Back");
        System.out.printf("\t%-10s%s\n", ":q", "Quit");
        System.out.println();
    }

    private static String inputString(String message) {
        String input;
        do {
            System.out.print(message);
            input = in.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static void showLogin() throws InterruptedException {
        String usrName = inputString("User: ");
        if (usrName.contains(":b")) {
            return;
        }
        String password = inputString("Password: ");
        if (password.contains(":b")) {
            return;
        }

        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.LOGIN)//0)
                .setToken(token)
                .setUser(RequestProtos.User.newBuilder()
                        .setUsername(usrName)
                        .setPassword(password)
                        .build())
                .build();

        System.out.printf("Logging in user [%s]...\n", usrName);

        channel.writeAndFlush(request).await();
    }

    public static void showSignUp() throws InterruptedException {
        String userName = inputString("User: ");
        if (userName.contains(":b")) {
            return;
        }
        String password = inputString("Password: ");
        if (password.contains(":b")) {
            return;
        }


        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.SIGNUP)//1)
                .setToken("")
                .setUser(RequestProtos.User.newBuilder()
                        .setUsername(userName)
                        .setPassword(password)
                        .build())
                .build();

        System.out.printf("Signing up for user [%s]...\n", userName);
        channel.writeAndFlush(request).await();
    }

    private static void logOut() throws InterruptedException {
        RequestProtos.Request request = RequestProtos.Request.newBuilder()
                .setType(RequestProtos.RequestType.LOGOUT)
                .setToken(token)
                .build();
        channel.writeAndFlush(request).await();
    }

    private static void showUserChat() throws InterruptedException {
        String userName = inputString("User name: ");
        if (userName.contains(":b")) {
            return;
        }

        System.out.println("Let start! Type and press \"Enter\" to send message!\n");
        String message;
        while (true) {
            message = inputString("~~> ");
            if (message.trim().equals(":b")) {
                return;
            }

            // send message

            RequestProtos.Request request = requestBuilder
                    .setType(RequestProtos.RequestType.CHAT)//4)
                    .setToken(token)
                    .setChat(RequestProtos.Chat.newBuilder()
                            .setTo(userName)
                            .setMessage(message)
                            .build())
                    .build();
            channel.writeAndFlush(request).await();
        }
    }

    private static void joinChannel() throws InterruptedException {
        String channelName = inputString("Channel: ");
        if (channelName.contains(":b")) {
            return;
        }

        // gui request join kenh
        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.JOINCHANNEL)//5
                .setToken(token)
                .setChannel(channelName)
                .build();
        channel.writeAndFlush(request).await();

        // lap nhan gui tin nhan
//
//        }
    }

    private static void listChannel() throws InterruptedException {
        //gui request list kenh
        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.LISTCHANNEL)//5
                .setToken(token)
                .build();
        channel.writeAndFlush(request).await();
    }

    private static void chatChannel() {
        String channelName = inputString("Channel: ");
        if (channelName.contains(":b")) {
            return;
        }


        System.out.println("Let start! Type and press \"Enter\" to send message!\n");
        String message;
        while (true) {

            message = inputString("~~> ");
            if (message.trim().equals(":b")) {
                return;
            }

            // send message
            RequestProtos.Request request = requestBuilder
                    .setType(RequestProtos.RequestType.CHATCHANNEL)//3)
                    .setToken(token)
                    .setChat(RequestProtos.Chat.newBuilder()
                            .setTo(channelName)
                            .setMessage(message)
                            .build())
                    .setChannel(channelName)
                    .build();

            try {
                channel.writeAndFlush(request).await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void exitChannel() throws InterruptedException {
        String channelName = inputString("Channel: ");
        if (channelName.contains(":b")) {
            return;
        }

        // gui request exit kenh
        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.EXITCHANNEL)//5
                .setToken(token)
                .setChannel(channelName)
                .build();
        channel.writeAndFlush(request).await();
    }
//
}