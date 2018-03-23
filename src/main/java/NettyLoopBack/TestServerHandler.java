package NettyLoopBack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import letschat.protobuf.MessageProto;

public class TestServerHandler extends SimpleChannelInboundHandler<MessageProto.MessageTest> {
    private ChannelGroup group;// = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public TestServerHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.channel().id();
        group.add(ctx.channel());
        System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProto.MessageTest msg) throws Exception {
        System.out.println("Receive from: " + ctx.channel().remoteAddress());

        String recContent = msg.getContent();
        String mess = "Da nhan duoc: " +  recContent;
        System.out.println("Server received: " + msg);
        ;
        ctx.write(msg.newBuilderForType().setContent(mess).build());
//        ctx.writeAndFlush(Unpooled.copiedBuffer(mess, CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("\t----ChannelReadComple");
        ctx.flush();
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
}
