package websockets.server;

import websockets.messaging.ChatGenericMessage;

import javax.websocket.Session;
import java.util.ArrayList;

public interface IServerSocket {
    void broadcast(ChatGenericMessage msg);
    void sendTo(String sessionId, Object object);
    void sendToOthers(String sessionId, Object object);
    Session getSessionFromId(String sessionId);
    void sendToClient(Session session, String message);
    ArrayList<Session> getSessions();
}
