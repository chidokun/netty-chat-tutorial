package NettyLoopBack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import letschat.protobuf.MessageProto;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

public class ServerHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("new client: " + ctx.channel().remoteAddress());
//        LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
//        ts.setRecvTimeStamp(System.nanoTime());
//        System.out.println(LocalDateTime.now() +"loop delay in ms : " + 1.0 * ts.timeLapseInNanoSecond() / 1000000L);
//    }
//
//    // Here is how we send out heart beat for idle to long
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) evt;
//            if (event.state() == IdleState.ALL_IDLE) { // idle for no read and write
//                ctx.writeAndFlush(new LoopBackTimeStamp());
//            }
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        // Close the connection when an exception is raised.
//        cause.printStackTrace();
//        ctx.close();
//    }

    //EchoServer:
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        System.out.println("Receive from: " + ctx.channel().remoteAddress());
//        ByteBuf in = (ByteBuf) msg;
//        String mess =  "Da nhan duoc: " + in.toString(CharsetUtil.UTF_8);
//
//        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
////        ctx.writeAndFlush(in);
//        ctx.writeAndFlush(Unpooled.copiedBuffer(mess, CharsetUtil.UTF_8));
//
//    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
    }

    //String Encode Decode
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Receive from: " + ctx.channel().remoteAddress());
        MessageProto.MessageTest rec = (MessageProto.MessageTest) msg;
        String recContent = rec.getContent();
        String mess = "Da nhan duoc: " +  recContent;
        System.out.println("Server received: " + msg);
        ;
        ctx.write(rec.newBuilderForType().setContent(mess).build());
//        ctx.writeAndFlush(Unpooled.copiedBuffer(mess, CharsetUtil.UTF_8));


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("\t----ChannelReadComple");
        ctx.flush();
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
