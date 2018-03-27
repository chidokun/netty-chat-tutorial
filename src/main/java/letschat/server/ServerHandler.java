package letschat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.RequestProtos.RequestType;
import letschat.protobuf.ResponseProtos;
import letschat.storage.RocksDBStorage;
import letschat.storage.Storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServerHandler extends SimpleChannelInboundHandler<RequestProtos.Request> {
    private ChannelGroup group;
    private Map<String, ChannelId> userMap;
    private Map<ChannelId, String> userMapReverse;
    private Storage<String, String> storage;
    private Map<String, ChannelGroup> groupsChat = null;


    public ServerHandler(ChannelGroup group, Map<String, ChannelId> userMap,
                         Map<ChannelId, String> userMapReverse, Storage storage) {
        this.group = group;
        this.userMap = userMap;
        this.userMapReverse = userMapReverse;
        this.storage = storage;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        group.add(ctx.channel());
        System.out.println("Client [" + ctx.channel().remoteAddress() + "] connected");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestProtos.Request request) throws Exception {
        System.out.println("Receive from: [" + ctx.channel().remoteAddress() + "]");
        System.out.println("Server received:\n" + request);
        ResponseProtos.Response.Builder responseBuilder = ResponseProtos.Response.newBuilder();
        ResponseProtos.Response response = null;
        String userName, password;
        ChannelGroup channelGroup = null;
        switch (request.getType()) {

            case SIGNUP:
                // ================= SIGN UP ==================
                userName = request.getUser().getUsername();
                password = request.getUser().getPassword();
                if (this.storage.contains("user." + userName + ".pass")) {
                    // đã có pass, chứng tỏ đã đăng ký rồi, trả về lỗi
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.DUPLICATE)
                            .setMessage(ResponseProtos.Message.newBuilder()
                                    .setFrom("Server")
                                    .setContent("Duplicate username!").build())
                            .build();
                } else {
                    // lưu xuống db
                    this.storage.put("user." + userName + ".pass", password);
                    // tra ve thanh cong
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.SUCCESS)
                            .build();
                }
                ctx.writeAndFlush(response);
                System.out.println("Sing up success: " + ctx.channel().remoteAddress() + response);
                break;
            // ================= LOGIN ==================
            case LOGIN:
                userName = request.getUser().getUsername();
                password = request.getUser().getPassword();
//                if (this.userMapReverse.containsKey(ctx.channel().id())) {
//                    response = responseBuilder
//                            .setType(ResponseProtos.ResponseType.FAILURE)
//                            .setMessage(ResponseProtos.Message.newBuilder()
//                                    .setFrom("Server")
//                                    .setContent("You were logged!")
//                                    .build())
//                            .build();
//                } else
                    if (password != null && password.equals(this.storage.get("user." + userName + ".pass"))) {
                    // đúng pass, trả về thành công và token
                    String token = Authentication.generateToken(userName);
                    if (userMap.containsKey(userName))
                        this.userMapReverse.remove(userMap.get(userName));
                    System.out.println(userMap.entrySet() + "\t" + userMapReverse.entrySet());
                    this.userMap.put(userName, ctx.channel().id());
                    this.userMapReverse.put(ctx.channel().id(), userName);
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.TOKEN)
                            .setToken(token)
                            .build();
                } else {
                    // sai pass, trả về response lỗi
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.FAILURE)
                            .setMessage(ResponseProtos.Message.newBuilder()
                                    .setFrom("Server")
                                    .setContent("Wrong username or password!")
                                    .build())
                            .build();
                }

                ctx.writeAndFlush(response);
                System.out.println(userName + " login " + ctx.channel().remoteAddress() + response);
                break;
            case LOGOUT:
                // ================= LOGOUT ==================
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.NOTLOGIN)
                            .build();
                } else {
                    userName = userMapReverse.get(ctx.channel().id());
                    this.userMapReverse.remove(ctx.channel().id());
                    this.userMap.remove(userName);
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.SUCCESS)
                            .build();
                }
                ctx.writeAndFlush(response);
                System.out.println(" logout success: " + ctx.channel().remoteAddress() + response);
                break;
            case CHAT:
                // ================= CHAT TO USER ==================
                //check token hop75 le65
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.NOTLOGIN)
                            .build();
                } else {

                    userName = userMapReverse.get(ctx.channel().id());
                    String toUser = request.getChat().getTo();
                    String content = request.getChat().getMessage();

                    if (!this.storage.contains("user." + toUser + ".pass")) {
                        response = responseBuilder
                                .setType(ResponseProtos.ResponseType.FAILURE)
                                .setMessage(ResponseProtos.Message.newBuilder()
                                        .setFrom("Server")
                                        .setContent("User not found!")
                                        .build())
                                .build();
                        ctx.writeAndFlush(response);
                        return;
                    }

                    String key = String.format("Message.%s.%s.%d",
                            userName,
                            toUser,
                            System.currentTimeMillis());
                    this.storage.put(key, content);
                    // tra ve cho user dang dang nhap
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.MESSAGE)
                            .setMessage(ResponseProtos.Message.newBuilder()
                                    .setFrom(userName)
                                    .setContent("" + request.getChat().getMessage())

                                    .build())
                            .setTime(System.currentTimeMillis())
                            .build();

//

                    if (this.userMap.get(request.getChat().getTo()) != null) {
                        Channel channel = this.group.find(this.userMap.get(request.getChat().getTo()));
                        System.out.printf("Send to [%s]:\n%s\n", channel.remoteAddress(), response);
                        channel.writeAndFlush(response);
                    } else { // user chua dang nhap, luu xuong db

                        String storedKey = "user." + toUser + ".receive";
                        int sequenceNumber = 1;
                        if (this.storage.contains(storedKey)) {
                            sequenceNumber = Integer.parseInt(this.storage.get(storedKey).toString()) + 1;

                            this.storage.remove(storedKey);
                        }
                        this.storage.put(storedKey, String.valueOf(sequenceNumber));
                        this.storage.put(storedKey + sequenceNumber, new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]").format(System.currentTimeMillis()) + " " + userName +": "+ response.getMessage().getContent());

                    }
                }

                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
//                ctx.write(response);
                break;
//            case USERS:
//                // ================= CHECK USER NAME ==================
//                userName = request.getName();
//                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
//                    response = responseBuilder.setType(4).setCode(3).build();
//                } else if (this.storage.contains("user." + userName + ".pass")) {
//                    response = responseBuilder.setType(4).setCode(0).build();
//                } else {
//                    response = responseBuilder.setType(4).setCode(3).build();
//                }
//                ctx.write(response);
//                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
//                break;
            case GETMESSAGE:
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.NOTLOGIN)
                            .build();
                } else {
                    userName = this.userMapReverse.get(ctx.channel().id());
                    String storedKey = "user." + userName + ".receive";
                    boolean contains = this.storage.contains(storedKey);
                    if (contains) {

                        int messageNumber = Integer.parseInt(this.storage.get(storedKey).toString());
                        System.out.println("so luong mess: " + messageNumber);
                        for (int i = 1; i <= messageNumber; i++) {
                            if (!this.storage.contains(storedKey + i))
                                continue;
                            String storedMess = this.storage.get(storedKey + i);
                            storage.remove(storedKey + i);
                            System.out.println(storedKey + String.valueOf(i) + "... " + storedMess);


                            response = responseBuilder
                                    .setType(ResponseProtos.ResponseType.MESSAGE)
                                    .setMessage(ResponseProtos.Message.newBuilder()
                                            .setContent(storedMess)//request.getChattouser().getFromuser() + " " + request.getChattouser().getMessage())
                                            .build())
                                    .setTime(0)
                                    .build();
                            ctx.writeAndFlush(response);
                            System.out.println("Send to " + ctx.channel().remoteAddress() + response);
                        }
                        storage.remove(storedKey);
                        storage.put(storedKey, String.valueOf(0));
                    }
                }
                break;
            case JOINCHANNEL:
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.NOTLOGIN)
                            .build();
                } else {
                    //
                    channelGroup = groupsChat.get(request.getChannel().getName());
                    if (channelGroup == null) {
                        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
                        groupsChat.put(request.getChannel().getName(), channelGroup);
                    }

                    channelGroup.add(ctx.channel());
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.SUCCESS)
                            .build();
                    ctx.writeAndFlush(response);
                    System.out.println("Join successed ");
                }
                break;
            case EXITCHANNEL:
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.NOTLOGIN)
                            .build();
                } else {
                    channelGroup = groupsChat.get(request.getChannel().getName());
                    if (channelGroup != null) {
                        channelGroup.remove(ctx.channel());
                    }
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.SUCCESS)
                            .build();
                    ctx.writeAndFlush(response);
                    System.out.println("Exit successed ");
                }
                break;
            case LISTCHANNEL:
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder
                            .setType(ResponseProtos.ResponseType.NOTLOGIN)
                            .build();
                } else {
                    for (Map.Entry<String, ChannelGroup> group : groupsChat.entrySet()) {
                        response = responseBuilder
                                .setType(ResponseProtos.ResponseType.MESSAGE)
                                .setMessage(ResponseProtos.Message.newBuilder()
                                        .setFrom("Server")
                                        .setContent(group.getKey())
                                        .build())
                                .build();
                        ctx.writeAndFlush(response);
                    }
                }
            default:
                break;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("-------------------------------------------");
        ctx.flush();

    }
}