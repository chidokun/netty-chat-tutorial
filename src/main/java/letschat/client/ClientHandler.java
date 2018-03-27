package letschat.client;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import letschat.protobuf.ResponseProtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends SimpleChannelInboundHandler<ResponseProtos.Response> {
    private DateFormat fm = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]");
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ResponseProtos.Response response) {

        switch (response.getType()) {
            case 0:
                // ================= LOGIN ==================
                if (response.getCode() == 0) {
                    System.out.println("Login successfully!\n");
                    NettyClient.isLogin = true;
                    NettyClient.token = response.getToken();
                } else {
                    System.out.println("Login unsuccessfully! Please try again!\n");
                    NettyClient.isLogin = false;
                    NettyClient.token = "";
                }
                break;
            case 1:
                // ================= SIGN UP ==================
                if (response.getCode() == 0) {
                    System.out.println("Sign up successfully!\nPlease login again!\n");
                } else if (response.getCode() == 1) {
                    System.out.println("Your name has been registered!\nPlease sign up with another name!\n");
                } else {
                    System.out.println("Sign up unsuccessfully!\n");
                }
                break;
            case 2:
                // ================= LOGOUT ==================
                if (response.getCode() == 0) {
                    System.out.println("Logged out!\n");
                    NettyClient.isLogin = false;
                    NettyClient.token = "";
                    NettyClient.currentUserName = "";
                } else {
                    System.out.println("Log out unsuccessfully!\n");
                }
                break;
            case 3:
                // ================= CHAT TO USER ====================
                if (response.getCode() != 0) {
                    System.out.println("Send message unsuccessfully!\n");
                } else {
                    if (!response.getUsermessage().getFromuser().equals(NettyClient.currentUserName)) {
                        System.out.printf("%s[%s]: %s\n", fm.format(new Date(response.getUsermessage().getTime())),
                                response.getUsermessage().getFromuser(), response.getUsermessage().getMessage());
                    }
                }

                break;
            case 4:
                // ================= CHECK USER NAME ==================
                if (response.getCode() == 0) {
                    NettyClient.isValidUserName = true;
                } else {
                    System.out.println("User name is invalid! Type \":b\" to go back!");
                    NettyClient.toUserName = "";
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}