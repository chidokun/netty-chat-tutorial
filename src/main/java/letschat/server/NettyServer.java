package letschat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import letschat.protobuf.RequestProtos;

import java.io.IOException;

public class NettyServer {

    public static void main(String[] args) throws IOException, InterruptedException {
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
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
//                pipeline.addLast("idleStateHandler",new IdleStateHandler(0,0,5)); // add with name
//                pipeline.addLast(new TimeStampEncoder()); // add without name, name auto generated
//                pipeline.addLast(new TimeStampDecoder()); // add without name, name auto generated

                //===========================================================
                // 2. run handler with slow business logic
                //    in separate thread from I/O thread
                //===========================================================
//                pipeline.addLast("frameDecoder",
//                        new ProtobufVarint32FrameDecoder());
                pipeline.addLast("protobufDecoder",
                        new ProtobufDecoder(RequestProtos.Request.getDefaultInstance()));
                pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
                pipeline.addLast("protobufEncoder", new ProtobufEncoder());
                pipeline.addLast(group,"serverHandler",new ServerHandler());
            }
        });

        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.bind(8080).sync();
    }
}
