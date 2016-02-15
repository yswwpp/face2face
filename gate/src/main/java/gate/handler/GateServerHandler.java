package gate.handler;

import com.google.protobuf.Message;
import gate.ClientMessage;
import gate.utils.ClientConnection;
import gate.utils.ClientConnectionMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Dell on 2016/2/1.
 */
public class GateServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //保存客户端连接
        ClientConnectionMap.addClientConnection(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        ClientConnection conn = ClientConnectionMap.getClientConnection(channelHandlerContext);
        ClientMessage.processTransferHandler(message, conn);
        //TODO 最好加一个通知客户端收到消息的通知
    }
}
