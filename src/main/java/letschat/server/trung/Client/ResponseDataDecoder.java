package letschat.server.trung.Client;

<<<<<<< HEAD
import letschat.server.trung.Server.ResponseData;
=======
import letschat.server.trung.Server.RequestData;
>>>>>>> 9e0e72b757dad4ec4bc3f5aa07627a541d47ec8a
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import letschat.server.trung.Server.ResponseData;

import java.util.List;

public class ResponseDataDecoder
        extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {

        ResponseData data = new ResponseData();
        data.setIntValue(in.readInt());
        out.add(data);
    }
}
