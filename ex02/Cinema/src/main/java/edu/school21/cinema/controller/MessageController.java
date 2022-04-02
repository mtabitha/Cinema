package edu.school21.cinema.controller;

import edu.school21.cinema.model.Message;
import edu.school21.cinema.model.Session;
import edu.school21.cinema.model.User;
import edu.school21.cinema.repositories.MessageRepo;
import edu.school21.cinema.repositories.UserRepo;
import edu.school21.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private UserService userService;

    @GetMapping("/films/{channel_id}/chat")
    public String show(@PathVariable("channel_id") Long channelId, Model model) {

        if (req.getCookies() == null) {
            User newUser = new User(LocalDateTime.now(), req.getRemoteAddr()/*, new ArrayList<>()*/);
            newUser = userService.addNew(newUser);
            Cookie cookie = new Cookie("id", newUser.getId().toString());
            resp.addCookie(cookie);
        }

        model.addAttribute("messages", messageRepo.findAllByChannelId(channelId));
        return "Chat";
    }

    @MessageMapping("/films/{channel_id}/chat")
    public void greeting(@DestinationVariable("channel_id") Long channelId,
                         Message message) throws Exception {
        Long userId = message.getChannelId();
        User userFromDB = userService.getUserId(userId);

        message.setChannelId(channelId);
        message.setUser(userFromDB);
        message = messageRepo.save(message);
        template.convertAndSend("/topic/films/" + channelId + "/chat/messages", message);
    }

}
