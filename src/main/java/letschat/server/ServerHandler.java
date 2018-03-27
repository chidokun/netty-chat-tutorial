package letschat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.RequestProtos.RequestType;
import letschat.protobuf.ResponseProtos;
import letschat.storage.RocksDBStorage;
import letschat.storage.Storage;

import java.util.Map;

public class ServerHandler extends SimpleChannelInboundHandler<RequestProtos.Request> {
    private ChannelGroup group;
    private Map<String, ChannelId> userMap;
    private  Map<ChannelId, String> userMapReverse;
    private Storage<String, String> storage;
    private ChannelGroup groupChat;


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

        switch (request.getType()) {
            // ================= LOGIN ==================
            case LOGIN:
                userName = request.getUser().getUsername();
                password = request.getUser().getPassword();
                if (password != null && password.equals(this.storage.get("user." + userName + ".pass"))) {
                    // đúng pass, trả về thành công và token
                    String token = Authentication.generateToken(userName);
                    this.userMap.put(userName, ctx.channel().id());
                    System.out.println("login va-----------------");
                    this.userMapReverse.put(ctx.channel().id(), userName);
                    response = responseBuilder.setType(0).setCode(0).setToken(token).build();
                    ctx.write(response);
                } else {
                    // sai pass, trả về response lỗi
                    response = responseBuilder.setType(0).setCode(3).build();
                    ctx.write(response);
                }
                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
                break;
            case SIGNUP:
                // ================= SIGN UP ==================
                userName = request.getUser().getUsername();
                password = request.getUser().getPassword();
                if (this.storage.contains("user." + userName + ".pass")) {
                    // đã có pass, chứng tỏ đã đăng ký rồi, trả về lỗi
                    response = responseBuilder.setType(1).setCode(1).build();
                    ctx.write(response);
                } else {
                    // lưu xuống db
                    this.storage.put("user." + userName + ".pass", password);
                    // tra ve thanh cong
                    response = responseBuilder.setType(1).setCode(0).build();
                    ctx.write(response);
                }
                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
                break;
            case LOGOUT:
                // ================= LOGOUT ==================
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder.setType(2).setCode(3).build();
                } else {
                    this.userMapReverse.remove(this.userMap.get(request.getName()));
                    this.userMap.remove(request.getName());
                    response = responseBuilder.setType(2).setCode(0).build();
                }
                ctx.write(response);
                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
                break;
            case CHATBOX:
                // ================= CHAT TO USER ==================
                //check token hop75 le65
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder.setType(3).setCode(3).build();
                } else {

                    String key = String.format("room.%s.%s.%d", request.getChattouser().getFromuser(),
                            request.getChattouser().getTouser(),
                            request.getChattouser().getTime());
                    this.storage.put(key, request.getChattouser().getMessage());
                    // tra ve cho user dang dang nhap
                    response = responseBuilder.setType(3)
                            .setCode(0)
                            .setUsermessage(ResponseProtos.UserMessage.newBuilder()
                                    .setFromuser(request.getChattouser().getFromuser())
                                    .setTouser(request.getChattouser().getTouser())
                                    .setMessage(request.getChattouser().getFromuser() + " " + request.getChattouser().getMessage())
                                    .setTime(request.getChattouser().getTime()))
                            .build();

//

                    if (this.userMap.get(request.getChattouser().getTouser()) != null) {
                        Channel channel = this.group.find(this.userMap.get(request.getChattouser().getTouser()));
                        System.out.printf("Send to [%s]:\n%s\n", channel.remoteAddress(), response);
                        channel.writeAndFlush(response);
                    } else { // user chua dang nhap, luu xuong db

                        String storedKey = "user." + request.getChattouser().getTouser() + ".receive";
                        int sequenceNumber = 1;
                        if (this.storage.contains(storedKey)) {
                            sequenceNumber = Integer.parseInt(this.storage.get(storedKey).toString()) + 1;

                            this.storage.remove(storedKey);
                        }
                        this.storage.put(storedKey, String.valueOf(sequenceNumber));
                        this.storage.put(storedKey + sequenceNumber,response.getUsermessage().getMessage());
                        System.out.println("put: " + storedKey + String.valueOf(sequenceNumber) + " " + response.getUsermessage().getMessage());
                        System.out.println("ddijt mejm: " + this.storage.contains(storedKey + String.valueOf(sequenceNumber)));
//                        for (int i = 0; i < sequenceNumber; i ++)
//                            if (this.storage.contains(storedKey + i)) {
//                            System.out.println(storage.get(storedKey + i));
//                            }
                    }
                }

                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
                ctx.write(response);
                break;
            case USERS:
                // ================= CHECK USER NAME ==================
                userName = request.getName();
                if (!Authentication.verifyToken(userMapReverse.get(ctx.channel().id()), request.getToken())) {
                    response = responseBuilder.setType(4).setCode(3).build();
                } else if (this.storage.contains("user." + userName + ".pass")) {
                    response = responseBuilder.setType(4).setCode(0).build();
                } else {
                    response = responseBuilder.setType(4).setCode(3).build();
                }
                ctx.write(response);
                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
                break;
            case GETMESSAGE:
                String storedKey = "user." + this.userMapReverse.get(ctx.channel().id()) + ".receive";

                boolean contains = this.storage.contains(storedKey);
                if (contains) {

                    int messageNumber = Integer.parseInt(this.storage.get(storedKey).toString());
                    System.out.println("so luong mess: " + messageNumber);
                    for (int i = 1; i <= messageNumber; i++) {
                        if (!this.storage.contains(storedKey + i))
                            continue;
                        String storedMess = this.storage.get(storedKey + i);
                        storage.remove(storedKey + i);
                        System.out.println(storedKey + String.valueOf(i) +"... " +storedMess);


                        response = responseBuilder.setType(3)
                                .setCode(0)
                                .setUsermessage(ResponseProtos.UserMessage.newBuilder()

                                        //.setFromuser(request.getChattouser().getFromuser())
                                        .setTouser(request.getChattouser().getTouser())
                                        .setMessage(i + " " + storedMess)//request.getChattouser().getFromuser() + " " + request.getChattouser().getMessage())
                                        .setTime(request.getChattouser().getTime()))
                                .build();
                        ctx.writeAndFlush(response);
                        System.out.printf("Send to " + ctx.channel().remoteAddress() + response);
                    }
                    storage.remove(storedKey);
                    storage.put(storedKey, String.valueOf(0));
                }
                break;
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