package letschat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;
import letschat.storage.RocksDBStorage;

public class ServerHandler extends SimpleChannelInboundHandler<RequestProtos.Request> {
    private ChannelGroup group;// = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public ServerHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.channel().id();
        group.add(ctx.channel());
        System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestProtos.Request request) throws Exception {
        System.out.println("Receive from: " + ctx.channel().remoteAddress());

        String msg = request.getUser().getUsername();
        String mess = "Da nhan duoc: " +  msg;
        System.out.println("Server received: " + request);
        ResponseProtos.Response.Builder responseBuilder = ResponseProtos.Response.newBuilder();
        ResponseProtos.Response response = null;
        if (NettyServer.storage == null) {
            NettyServer.storage = new RocksDBStorage("/tmp/letschat");
        }

        switch (request.getType()) {
            // ================= LOGIN ==================
            case 0:
                String user = request.getUser().getUsername();
                String pass = request.getUser().getPassword();
                if (pass != null && pass.equals(NettyServer.storage.get("user." + user + ".pass"))) {
                    // đúng pass, trả về thành công và token
                    String token = "";
                    response = responseBuilder.setType(0).setCode(0).setToken(token).build();
                    ctx.write(response);
                } else {
                    // sai pass, trả về response lỗi
                    response = responseBuilder.setType(0).setCode(3).build();
                    ctx.write(response);
                }
                break;
            case 1:
                // ================= SIGN UP ==================
                String usersu = request.getUser().getUsername();
                String passsu = request.getUser().getPassword();
                if (NettyServer.storage.contains("user." + usersu + ".pass")) {
                    // đã có pass, chứng tỏ đãđăng ký rồi, trả vềlỗi
                    response = responseBuilder.setType(1).setCode(1).build();
                    ctx.write(response);
                } else {
                    // lưu xuống db
                    NettyServer.storage.put("user." + usersu + ".pass", passsu);
                    // tra ve thanh cong
                    response = responseBuilder.setType(1).setCode(0).build();
                    ctx.write(response);
                }
                break;
            case 2:
                // ================= LOGOUT ==================
                //xac nhan client da log out khoi he thong

                //tra ve log out thanh cong
                response = responseBuilder.setType(2).setCode(0).build();
                ctx.write(response);

                // neu co loi tra ve loi
                break;
            case 3:
                // ================= CHAT TO USER ==================
                break;

            default:
                break;
        }

        //ctx.write();
//        ctx.writeAndFlush(Unpooled.copiedBuffer(mess, CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("\t----ChannelReadComple");
        ctx.flush();
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
}
