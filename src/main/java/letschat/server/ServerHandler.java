package letschat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;
import letschat.storage.RocksDBStorage;
import letschat.storage.Storage;

import java.util.Map;

public class ServerHandler extends SimpleChannelInboundHandler<RequestProtos.Request> {
    private ChannelGroup group;
    private Map<String, ChannelId> userMap;
    public Map<ChannelId, String> userMapReverse;
    private Storage storage;

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
            case 0:
                userName = request.getUser().getUsername();
                password = request.getUser().getPassword();
                if (password != null && password.equals(this.storage.get("user." + userName + ".pass"))) {
                    // đúng pass, trả về thành công và token
                    String token = Authentication.generateToken(userName);
                    this.userMap.put(userName, ctx.channel().id());
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
            case 1:
                // ================= SIGN UP ==================
                userName = request.getUser().getUsername();
                password = request.getUser().getPassword();
                if (this.storage.contains("user." + userName + ".pass")) {
                    // đã có pass, chứng tỏ đãđăng ký rồi, trả vềlỗi
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
            case 2:
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
            case 3:
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
                                    .setMessage(request.getChattouser().getMessage())
                                    .setTime(request.getChattouser().getTime()))
                            .build();

                    Channel channel = this.group.find(this.userMap.get(request.getChattouser().getTouser()));
                    if (channel != null) {
                        System.out.printf("Send to [%s]:\n%s\n", channel.remoteAddress(), response);
                        channel.writeAndFlush(response);
                    }
                }

                System.out.printf("Send to [%s]:\n%s\n", ctx.channel().remoteAddress(), response);
                ctx.write(response);
                break;
            case 4:
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
