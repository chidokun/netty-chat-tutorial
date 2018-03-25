package letschat.client;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import letschat.protobuf.ResponseProtos;

public class ClientHandler extends SimpleChannelInboundHandler<ResponseProtos.Response> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ResponseProtos.Response response) {

        switch (response.getType()) {
            // ================= LOGIN ==================
            case 0:
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
                // ================= CHAT TO USER ==================
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