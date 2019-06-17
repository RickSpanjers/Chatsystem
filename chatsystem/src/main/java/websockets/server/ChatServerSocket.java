package websockets.server;

import chatsystem.ChatSystemLogic;
import chatsystem.IChatSystemLogic;
import com.google.gson.Gson;
import logging.ILogger;
import logging.LogLevel;
import logging.Logger;
import websockets.messaging.ChatGenericMessage;
import websockets.messaging.EnumOperationMessage;
import websockets.messaging.IServerMessageProcessor;
import websockets.server.messaging.ServerMessageHandler;
import websockets.server.messaging.ServerMessageProcessor;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint(value = "/chatsystem/")
public class ChatServerSocket implements IServerSocket {

    private static ArrayList<Session> sessions = new ArrayList<>();
    private static IChatSystemLogic logic = new ChatSystemLogic();
    private IServerMessageProcessor processor = new ServerMessageProcessor(new ServerMessageHandler(logic, this));
    private Gson gson = new Gson();
    private ILogger logger = Logger.getInstance();


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[Connected] SessionID:" + session.getId());
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            System.out.println("[Server Message received]::From=" + session.getId() + " Message=" + message);
            ChatGenericMessage msg = gson.fromJson(message, ChatGenericMessage.class);
            processor.processMessage(session, msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void broadcast(ChatGenericMessage msg) {
        for (Session s : sessions) {
            sendTo(s.getId(), gson.toJson(msg));
        }
    }

    @Override
    public void sendTo(String sessionId, Object object) {
        try {
            String msg = object.toString();
            if(sessionId != null){
                sendToClient(getSessionFromId(sessionId), msg);
            }else{
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            logger.log(e);
        }
    }

    @Override
    public void sendToOthers(String sessionId, Object object) {
        try {
            Session session = getSessionFromId(sessionId);
            for (Session ses : sessions) {
                if (!ses.getId().equals(session.getId())) {
                    sendTo(ses.getId(), object);
                }
            }
        } catch (Exception e) {
            logger.log(e);
        }
    }

    @Override
    public Session getSessionFromId(String sessionId) {
        for (Session s : sessions) {
            if (s.getId().equals(sessionId)) {
                return s;
            }
        }
        return sessions.get(0);
    }


    @Override
    public void sendToClient(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.log(e);
        }
    }

    @Override
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" + session.getId());
        processor.processMessage(session, new ChatGenericMessage(EnumOperationMessage.LOGOUT));
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable t) {
        logger.log(t.getMessage(), LogLevel.ERROR);
    }

}
