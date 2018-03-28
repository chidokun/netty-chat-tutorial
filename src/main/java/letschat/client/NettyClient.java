package letschat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import letschat.client.ClientHandler;
import letschat.common.Constant;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;

import java.util.Scanner;

import static letschat.common.Constant.HELP;
import static letschat.common.Constant.SIGNUP;

public class NettyClient {

//
//    private static String host = "localhost";
//    private static int port = 8080;
//    private static String token = "";
//    public static String currentUserName = "";
//    public static String toUserName = "";
//    public static boolean isValidUserName = false;
    private static ClientHandler clientHandler = new ClientHandler();
    private static Bootstrap bootstrap = null;
    private static NioEventLoopGroup workerGroup = null;
//    private static RequestProtos.Request.Builder requestBuilder = RequestProtos.Request.newBuilder();

    public static void main(String[] args) throws InterruptedException {
        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ProtobufVarint32FrameDecoder());
                pipeline.addLast(new ProtobufDecoder(ResponseProtos.Response.getDefaultInstance()));
                pipeline.addLast(new ProtobufDecoder(RequestProtos.Request.getDefaultInstance()));
                pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                pipeline.addLast(new ProtobufEncoder());
                pipeline.addLast(clientHandler);
            }
        });

        System.out.println("LET'S CHAT\n======================");

        while (true) {
            if (clientHandler.connect(bootstrap)) {
                System.out.println("DONE");
                break;
            } else {
                System.out.println("ERROR\nPlease try again!");
            }
        }

        System.out.println("Type \":h\" for help!");
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("~> ");
            command = in.nextLine().trim();
            if (!clientHandler.mainProgram(command)){
                workerGroup.shutdownGracefully();
                System.exit(0);
            }

        }
<<<<<<< HEAD
=======
    }

    private static void exitChannel() throws InterruptedException {
        String channelName = inputString("Channel: ");
        if (channelName.contains(":b")) { return; }

        // gui request exit kenh
        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.EXITCHANNEL)//5
                .setName(channelName)
                .setToken(token)
                .build();
        channel.writeAndFlush(request).await();
    }
>>>>>>> cf671a0b8f506e242bec1370e81a9cce2da4c75e

    private static void listChannel() throws  InterruptedException{
        //gui request list kenh
        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.LISTCHANNEL)//5
                .setToken(token)
                .build();
        channel.writeAndFlush(request).await();
    }

<<<<<<< HEAD
//    public static void mainProgram(String command) {
//        try {
//
//            if (command.equalsIgnoreCase(":q")) {
//                System.out.println("Good bye!");
//                workerGroup.shutdownGracefully();
//                System.exit(0);
//            }
//
//            switch (command) {
//                case Constant.HELP:
//                    showHelp();
//                    break;
//                case Constant.LOGIN:
//                    showLogin();
//                    break;
//                case Constant.CHAT:
//                    showUserChat();
//
//                    break;
//                case Constant.LOGOUT:
//                    logOut();
//                    break;
//                case Constant.SIGNUP:
//                    showSignUp();
//                    break;
//                case Constant.BACK:
//                    System.out.println("Nothing to back!\n");
//                    break;
//                case Constant.GETMESSAGE:
//                    getMessages();
//                    break;
//                case Constant.JOINCHANNEL:
//                    joinChannel();
//                    break;
//                case Constant.LISTCHANNEL:
//                    listChannel();
//                    break;
//                case Constant.EXITCHANNEL:
//                    exitChannel();
//                case "":
//                    break;
//                default:
//                    System.out.println("Invalid command. Type \":h\" for help!\n");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void exitChannel() {
//    }
//
//    private static void listChannel() {
//
//    }
//
//    private static void joinChannel() throws InterruptedException {
//        String channelName = inputString("Channel: ");
//        if (channelName.contains(":b")) {
//            return;
//        }
//
//
//        // lap nhan gui tin nhan
//        String message;
//        while (true) {
//            message = inputString("");
//            if (message.trim().equals(":b")) {
//                return;
//            }
//
//            // send message
//            RequestProtos.Request request = requestBuilder
//                    .setType(RequestProtos.RequestType.JOINCHANNEL)//3)
//                    .setToken(token)
//                    .setChannel(RequestProtos.Channel.newBuilder()
//                            .setName(channelName).build())
//                    .build();
//
//            channel.writeAndFlush(request).await();
//        }
//    }
//
//    private static void getMessages() {
//        System.out.println("Get messages");
//        RequestProtos.Request request = requestBuilder
//                .setType(RequestProtos.RequestType.GETMESSAGE)//5
//                .setToken(token)
//                .build();
//
//        try {
//            channel.writeAndFlush(request).await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static boolean connect(Bootstrap b) {
//        try {
//            System.out.print("Host: ");
//            String _host = in.nextLine();
//            if (_host.isEmpty()) {
//                host = "localhost";
//                System.out.println("Default host: " + host);
//            } else {
//                host = _host;
//            }
//
//            System.out.print("Port: ");
//            String _port = in.nextLine();
//            if (_port.isEmpty()) {
//                port = 8080;
//                System.out.println("Default port: " + port);
//            } else {
//                port = Integer.parseInt(_port);
//            }
//
//            ChannelFuture future = b.connect(host, port);
//            System.out.printf("Connecting to %s/%d ... ", host, port);
//
//            if (future.await().isSuccess()) {
//                channel = future.sync().channel();
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static void showHelp() {
//        System.out.println("~ Commands:");
//        System.out.printf("\t%-10s%s\n", ":h", "Show help");
//        System.out.printf("\t%-10s%s\n", ":lg", "Login");
//        System.out.printf("\t%-10s%s\n", ":lo", "Logout");
//        System.out.printf("\t%-10s%s\n", ":su", "Sign up");
//        System.out.printf("\t%-10s%s\n", ":c", "Chat to a user or channel");
//        System.out.printf("\t%-10s%s\n", ":gm", "Get message when offline");
//        System.out.printf("\t%-10s%s\n", ":j", "Join a channel");
//        System.out.printf("\t%-10s%s\n", ":l", "List of current channels");
//        System.out.printf("\t%-10s%s\n", ":x", "Exit a channel");
//        System.out.printf("\t%-10s%s\n", ":b", "Back");
//        System.out.printf("\t%-10s%s\n", ":q", "Quit");
//        System.out.println();
//    }
//
//    private static String inputString(String message) {
//        String input;
//        do {
//            System.out.print(message);
//            input = in.nextLine().trim();
//        } while (input.isEmpty());
//        return input;
//    }
//
//    public static void showLogin() throws InterruptedException {
//        String userName = inputString("User: ");
//        if (userName.contains(":b")) {
//            return;
//        }
//        String password = inputString("Password: ");
//        if (password.contains(":b")) {
//            return;
//        }
//
//        RequestProtos.Request request = requestBuilder
//                .setType(RequestProtos.RequestType.LOGIN)//0)
//                .setToken(token)
//                .setUser(RequestProtos.User.newBuilder()
//                        .setUsername(userName)
//                        .setPassword(password)
//                        .build())
//                .build();
//
//        System.out.printf("Logging in user [%s]...\n", userName);
//
//        channel.writeAndFlush(request).await();
//    }
//
//    public static void showSignUp() throws InterruptedException {
//        String userName = inputString("User: ");
//        if (userName.contains(":b")) {
//            return;
//        }
//        String password = inputString("Password: ");
//        if (password.contains(":b")) {
//            return;
//        }
//
//
//        RequestProtos.Request request = requestBuilder
//                .setType(RequestProtos.RequestType.SIGNUP)//1)
//                .setToken("")
//                .setUser(RequestProtos.User.newBuilder()
//                        .setUsername(userName)
//                        .setPassword(password)
//                        .build())
//                .build();
//
//        System.out.printf("Signing up for user [%s]...\n", userName);
////        currentUserName = userName;
//
//        channel.writeAndFlush(request).await();
//    }
//
//    private static void logOut() throws InterruptedException {
//        RequestProtos.Request request = RequestProtos.Request.newBuilder()
//                .setType(RequestProtos.RequestType.LOGOUT)//2)
//                .setToken(token)
//                .build();
//
//        channel.writeAndFlush(request).await();
//    }
//
//    private static void showUserChat() throws InterruptedException {
//        String userName = inputString("User name: ");
//        if (userName.contains(":b")) {
//            return;
//        }
//
//        // check username is valid
//
//
//        System.out.println("Let start! Type and press \"Enter\" to send message!\n");
//        String message;
//        while (true) {
//            message = inputString("~~> ");
//            if (message.trim().equals(":b")) {
//                return;
//            }
//
//            // send message
//
//            RequestProtos.Request request = requestBuilder
//                    .setType(RequestProtos.RequestType.CHAT)//4)
//                    .setToken(token)
//                    .setChat(RequestProtos.Chat.newBuilder()
//                            .setTo(userName)
//                            .setMessage(message)
//                            .build())
//                    .build();
//            channel.writeAndFlush(request).await();
//        }
//    }
=======
    private static void joinChannel() throws InterruptedException {
        String channelName = inputString("Channel: ");
        if (channelName.contains(":b")) { return; }

        // gui request join kenh
        RequestProtos.Request request = requestBuilder
                .setType(RequestProtos.RequestType.JOINCHANNEL)//5
                .setName(channelName)
                .setToken(token)
                .build();
        channel.writeAndFlush(request).await();

        // lap nhan gui tin nhan
        String message;
        while (true) {
            message = inputString("");
            if (message.trim().equals(":b")) { return; }

            // send message
            request = requestBuilder
                    .setType(RequestProtos.RequestType.CHATBOX)//3)
                    .setChattouser(RequestProtos.ChatToUser.newBuilder()
                            .setFromuser(currentUserName)
                            .setTouser(channelName)
                            .setMessage(message)
                            .setTime(System.currentTimeMillis()))
                    .setToken(token)
                    .build();
>>>>>>> cf671a0b8f506e242bec1370e81a9cce2da4c75e

}
