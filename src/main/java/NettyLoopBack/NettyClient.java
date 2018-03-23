package NettyLoopBack;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
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
            Channel channel = b.connect(serverIp, 8080).sync().channel();
        MessageProto.MessageTest mess;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try {
//                System.out.println("Go vao: " + in.readLine());
                mess = MessageProto.MessageTest.newBuilder().setContent(in.readLine()).build();
//                channel.writeAndFlush(in.readLine() );
//                channel.writeAndFlush(Unpooled.copiedBuffer(in.readLine(), CharsetUtil.UTF_8));
                channel.writeAndFlush(mess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
