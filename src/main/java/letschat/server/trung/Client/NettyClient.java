package letschat.server.trung.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {
    public static void main(String[] args) throws Exception {
        String text = "start";
        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                public void initChannel(SocketChannel ch)
                        throws Exception {
                        ch.pipeline().addLast(new RequestDataEncoder(),
                                new ResponseDataDecoder(), new ClientHandler());

                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }



//        ChatToUserProtos.ChatToUser.Builder packageBuilder = ChatToUserProtos.ChatToUser.newBuilder()
//                .setFromuser("Trung")
//                .setMessage("Hello")
//                .setTouser("admin")
//                .setTime(12345)
//                .setToken("adfminasdf");
//        ChatToUserProtos.ChatToUser mess = packageBuilder.build();
//        System.out.println(mess.isInitialized());
    }
}

