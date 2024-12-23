package book.apress.rapidjavapersistencemicroservice.websocketexamplech09.service;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public interface UserMessageService {
    void newUserJoined(WebSocketSession session);
    void broadcastMessage(WebSocketSession session, TextMessage message);
    void userLeft(WebSocketSession session);
}
