package letschat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
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
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;
import letschat.storage.RocksDBStorage;
import letschat.storage.Storage;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyServer {

    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();
    public static Map<ChannelId, String> userMapReverse = new ConcurrentHashMap<>();
    static final ChannelGroup channels =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Storage<String, String> storage = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (storage == null) {
            storage = new RocksDBStorage("/tmp/letschat/");
        }

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(boosGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);

        final EventExecutorGroup group = new DefaultEventExecutorGroup(10);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new ProtobufVarint32FrameDecoder());
                pipeline.addLast(new ProtobufDecoder(RequestProtos.Request.getDefaultInstance()));
                pipeline.addLast(new ProtobufDecoder(ResponseProtos.Response.getDefaultInstance()));
                pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                pipeline.addLast(new ProtobufEncoder());
                pipeline.addLast(new ServerHandler(channels, userMap, userMapReverse, storage));
            }
        });

        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        Channel channel = bootstrap.bind(8080).sync().channel();
        System.out.println(channel.id());
    }
}

