<<<<<<< HEAD
package letschat.server.trung.Server;
=======
package letschat.server.trung.Server;;
>>>>>>> 9e0e72b757dad4ec4bc3f5aa07627a541d47ec8a

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseDataEncoder
        extends MessageToByteEncoder<ResponseData> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          ResponseData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntValue());
    }
}
