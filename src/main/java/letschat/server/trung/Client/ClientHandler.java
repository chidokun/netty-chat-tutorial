package letschat.server.trung.Client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInboundHandler;
import io.netty.util.CharsetUtil;
import letschat.server.trung.Server.RequestData;
import letschat.server.trung.Server.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext){
        Scanner in = new Scanner(System.in);
        String m = "";
        while(!m.equals("exit")) {
            m = in.nextLine();
            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(m, CharsetUtil.UTF_8));
        }

    }


    public void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause){
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}