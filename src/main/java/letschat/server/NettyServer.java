package letschat.server;

import NettyLoopBack.TestServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import letschat.protobuf.MessageProto;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;
import letschat.storage.RocksDBStorage;
import letschat.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyServer {

    static final ChannelGroup channels =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Storage storage = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (storage == null) {
            storage = new RocksDBStorage("/tmp/letschat/");
        }

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(boosGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);

        // ===========================================================
        // 1. define a separate thread pool to execute handlers with
        //    slow business logic. e.g database operation
        // ===========================================================
        final EventExecutorGroup group = new DefaultEventExecutorGroup(1500); //thread pool of 1500

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            //            @Override
//            protected void initChannel(SocketChannel ch) throws Exception {
//                ChannelPipeline pipeline = ch.pipeline();
//                pipeline.addLast("idleStateHandler",new IdleStateHandler(0,0,5)); // add with name
//                pipeline.addLast(new TimeStampEncoder()); // add without name, name auto generated
//                pipeline.addLast(new TimeStampDecoder()); // add without name, name auto generated
//
//                //===========================================================
//                // 2. run handler with slow business logic
//                //    in separate thread from I/O thread
//                //===========================================================
//                pipeline.addLast(group,"serverHandler",new ServerHandler());
//            }


            //EchoServer:
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
//                pipeline.addLast("decoder", new StringDecoder());
//                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast(new ProtobufVarint32FrameDecoder());
               // pipeline.addLast(new ProtobufDecoder(MessageProto.MessageTest.getDefaultInstance()));
                pipeline.addLast(new ProtobufDecoder(RequestProtos.Request.getDefaultInstance()));
                pipeline.addLast(new ProtobufDecoder(ResponseProtos.Response.getDefaultInstance()));
                pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                pipeline.addLast(new ProtobufEncoder());
                pipeline.addLast(new ServerHandler(channels));

            }
        });

        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
//        bootstrap.bind(8080).sync();

        Channel channel = bootstrap.bind(8080).sync().channel();
        System.out.println(channel.id());
        MessageProto.MessageTest mess;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
//                System.out.println("Go vao: " + in.readLine());
                mess = MessageProto.MessageTest.newBuilder().setContent(in.readLine()).build();
                System.out.println("write to: " + channel.remoteAddress());
//                channel.writeAndFlush(in.readLine() );
//                channel.writeAndFlush(Unpooled.copiedBuffer(in.readLine(), CharsetUtil.UTF_8));
                channel.writeAndFlush(mess);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Channel ch : channels) {
                //do something with ch object :)
                System.out.println("Channel in group: " + ch);
            }

        }
    }
}

