package NettyLoopBack;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import letschat.protobuf.MessageProto;

public class ClientHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
//        ctx.writeAndFlush(ts); //recieved message sent back directly
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        // Close the connection when an exception is raised.
//        cause.printStackTrace();
//        ctx.close();
//    }

    //EchoClient
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        //write proto
        MessageProto.MessageTest mess = MessageProto.MessageTest.newBuilder().setContent("Trung from proto").build();
        ctx.writeAndFlush(mess);
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf in = (ByteBuf) msg;
//        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
//    }
    //Echo Encode Decode
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf in = (ByteBuf) msg;
        System.out.println("Client received: " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}