package websockets.server.messaging;

import chatsystem.ChatSystemLogic;
import websockets.messaging.ChatGenericMessage;
import websockets.messaging.IServerMessageHandler;
import websockets.messaging.IServerMessageProcessor;

import javax.websocket.Session;

public class ServerMessageProcessor implements IServerMessageProcessor {

    private ChatSystemLogic logic;
    private IServerMessageHandler handler;

    public ServerMessageProcessor(IServerMessageHandler messageHandler)
    {
        handler = messageHandler;
    }

    public void setGameLogic(ChatSystemLogic logic)
    {
        this.logic = logic;
    }

    public ChatSystemLogic getGame()
    {
        return logic;
    }


    @Override
    public void processMessage(Session session, ChatGenericMessage msg) {
        handler.handleMessage(msg.getOperationMessage(), msg.getData(), session);
    }
}
