package letschat.common;

import letschat.protobuf.RequestProtos;
import letschat.protobuf.ResponseProtos;

public interface Req {
    ResponseProtos.Response createResponse();
    RequestProtos.Request   createReqeust();
}