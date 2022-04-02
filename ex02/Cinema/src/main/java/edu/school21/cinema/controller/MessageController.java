package edu.school21.cinema.controller;

import edu.school21.cinema.model.Message;
import edu.school21.cinema.model.User;
import edu.school21.cinema.repositories.MessageRepo;
import edu.school21.cinema.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private HttpServletResponse resp;

    @Autowired
    private HttpServletRequest req;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/films/{id}/chat")
    public String show(@PathVariable Long id) {
//        if (Arrays.stream(req.getCookies()).noneMatch(c -> c.getName().equals("id")))
//            resp.addCookie(new Cookie("id",
//                userRepo.save(new User(LocalDateTime.now(), req.getRemoteAddr(), new ArrayList<>())).getId().toString()));
        return "Chat";
    }

    @MessageMapping("/films/{id}/chat")
    public void greeting(@DestinationVariable Long id, Message message) throws Exception {
//        Long userId = Long.valueOf(Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("id")).findAny().get().getValue());
//        System.out.println(userId);
//        message.setUser(userRepo.findById(userId).get());
//        messageRepo.save(message);
        System.out.println(message.getText());
        template.convertAndSend("/topic/films/" + id + "/chat/messages", message);
    }

}
