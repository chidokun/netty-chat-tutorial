package letschat.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import letschat.protobuf.MessageProto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyClient {


    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);

        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
//                ch.pipeline().addLast(new TimeStampEncoder(),new TimeStampDecoder(),new ClientHandler());
                ChannelPipeline pipeline = ch.pipeline();
//                pipeline.addLast("decoder", new StringDecoder());
//                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast(new ProtobufVarint32FrameDecoder());
                pipeline.addLast(new ProtobufDecoder(MessageProto.MessageTest.getDefaultInstance()));

                pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                pipeline.addLast(new ProtobufEncoder());

                pipeline.addLast(new ClientHandler());
//                ch.pipeline().addLast(new ClientHandler());
            }
        });

        String serverIp = "localhost";
        int port = -1;
        final Channel[] channel = {null};
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("Server: ");
                serverIp = input.readLine();
                if (serverIp.equals("")) {
                    System.out.println("Default server: localhost");
                    serverIp = "localhost";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.print("port: ");
                String portString = input.readLine();
                if (portString.equals("")) {
                    System.out.println("Default port: 8080");
                    port = 8080;
                } else
                    try {
                        port = Integer.parseInt(portString);
                    } catch (NumberFormatException e) {
                        System.out.println("Port mus be a valid integer");
                        continue;
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }

            ChannelFuture future = b.connect(serverIp, port);

//            future = future.sync();
            System.out.println("ok");
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("Connected!");
                        channel[0] = future.sync().channel();
                    } else {
                        System
                                .out.println("Cannot find server, please try again!");
                    }
                }
            });
            if (future.await().isSuccess())
                break;
//            System.out.println("after wai: " +connect[0]);
        }

//        String serverIp = "localhost";
//        int port = 8080;
//        Channel channel = b.connect(serverIp, port).sync().channel();
        MessageProto.MessageTest mess;

        while (true) {
            try {
//                System.out.println("Go vao: " + input.readLine());
                mess = MessageProto.MessageTest.newBuilder().setContent(input.readLine()).build();
//                channel.writeAndFlush(input.readLine() );
//                channel.writeAndFlush(Unpooled.copiedBuffer(input.readLine(), CharsetUtil.UTF_8));
                channel[0].writeAndFlush(mess);
//                channel.writeAndFlush(mess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
