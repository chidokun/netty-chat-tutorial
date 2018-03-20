package letschat.server.trung.Server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;

        String received = inBuffer.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + received);

        ResponseProtos.Response r = ResponseProtos.Response.newBuilder().setType(0).setCode(0)
                .build();
        byte[] x = r.toByteArray();
        ctx.writeAndFlush(r.toByteArray());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ResponseProtos.Response r = ResponseProtos.Response.newBuilder().setType(0).setCode(0)
                .build();
        ctx.writeAndFlush(r.toByteArray())
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
