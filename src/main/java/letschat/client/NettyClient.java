package letschat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import letschat.client.ClientHandler;
import letschat.common.Constant;
import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;

import java.util.Scanner;

import static letschat.common.Constant.HELP;
import static letschat.common.Constant.SIGNUP;

public class NettyClient {

    private static ClientHandler clientHandler = new ClientHandler();
    private static Bootstrap bootstrap = null;
    private static NioEventLoopGroup workerGroup = null;

    public static void main(String[] args) {
        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ProtobufVarint32FrameDecoder());
                pipeline.addLast(new ProtobufDecoder(ResponseProtos.Response.getDefaultInstance()));
                pipeline.addLast(new ProtobufDecoder(RequestProtos.Request.getDefaultInstance()));
                pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                pipeline.addLast(new ProtobufEncoder());
                pipeline.addLast(clientHandler);
            }
        });

        System.out.println("LET'S CHAT\n======================");

        while (true) {
            if (ClientHandler.connect(bootstrap)) {
                System.out.println("DONE");
                break;
            } else {
                System.out.println("ERROR\nPlease try again!");
            }
        }

        System.out.println("Type \":h\" for help!");
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("~> ");
            command = in.nextLine().trim();
            if (!ClientHandler.mainProgram(command)) {
                workerGroup.shutdownGracefully();
                System.exit(0);
            }
        }
    }
}