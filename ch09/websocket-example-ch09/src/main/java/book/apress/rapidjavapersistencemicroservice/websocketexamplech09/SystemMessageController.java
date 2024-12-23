package book.apress.rapidjavapersistencemicroservice.websocketexamplech09;

import book.apress.rapidjavapersistencemicroservice.websocketexamplech09.service.SystemMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemMessageController {

    private final SystemMessageService messageService;

    public SystemMessageController(SystemMessageService messageService) {
        this.messageService = messageService;

    }

    @PostMapping("/messages")
    public void sendMessage(@RequestBody String message) {
        messageService.broadcastMessage(message);
    }
}
