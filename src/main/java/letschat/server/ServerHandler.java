package letschat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import letschat.protobuf.RequestProtos;

import java.net.InetSocketAddress;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ip = ctx.channel();
        System.out.print(ip);
        RequestProtos.Request request = RequestProtos.Request.newBuilder()
                .setType(0)
                .setUser(RequestProtos.User.newBuilder()
                        .setUsername("Trung")
                        .setPassword("oK")
                        .build())
                .build();
//        RequestProtos.Request req = (RequestProtos.Request) msg;

        System.out.println(msg);// + ts.toString());
    }

    // Here is how we send out heart beat for idle to long
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) evt;
//            if (event.state() == IdleState.ALL_IDLE) { // idle for no read and write
//                ctx.writeAndFlush(new LoopBackTimeStamp());
//            }
//        }
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
