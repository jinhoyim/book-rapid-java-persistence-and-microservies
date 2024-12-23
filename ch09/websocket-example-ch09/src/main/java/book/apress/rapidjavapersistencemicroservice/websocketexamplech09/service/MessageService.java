package book.apress.rapidjavapersistencemicroservice.websocketexamplech09.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class MessageService implements UserMessageService, SystemMessageService {

    AtomicInteger counter = new AtomicInteger(0);
    Map<WebSocketSession, String> sessionMap = new ConcurrentHashMap<>();

    private MessageService() {
    }

    public void newUserJoined(WebSocketSession session) {
        String username = "User" + counter.incrementAndGet();
        sessionMap.put(session, username);
        sessionMap.keySet().stream().filter(ses -> ses != session && ses.isOpen())
                .forEach(ses -> {
                    try {
                        ses.sendMessage(new TextMessage(username + " has joined"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void broadcastMessage(WebSocketSession session, TextMessage message) {
        log.info("handleTextMessage {}", message.getPayload());

        if (!sessionMap.containsKey(session)) {
            return;
        }

        String username = sessionMap.get(session);
        sessionMap.keySet().stream().filter(WebSocketSession::isOpen)
                .forEach(ses -> {
                    try {
                        ses.sendMessage(new TextMessage(username + " : " + message.getPayload()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void broadcastMessage(String message) {
        sessionMap.keySet().stream().filter(WebSocketSession::isOpen)
                .forEach(ses -> {
                    try {
                        ses.sendMessage(new TextMessage("System : " + message));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void userLeft(WebSocketSession session) {
        String username = sessionMap.remove(session);
        sessionMap.keySet().stream().filter(ses -> ses != session && ses.isOpen())
                .forEach(ses -> {
                    try {
                        ses.sendMessage(new TextMessage(username + " has left"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
