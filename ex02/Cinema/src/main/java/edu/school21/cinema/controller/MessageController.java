package edu.school21.cinema.controller;

import edu.school21.cinema.model.Message;
import edu.school21.cinema.repositories.MessageRepo;
import edu.school21.cinema.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;

    @GetMapping("/films/{channel_id}/chat")
    public String show(@PathVariable("channel_id") Long channelId, Model model) {

        messageService.addCookie(channelId);

        model.addAttribute("messages",  messageService.findLast20Messages(channelId));
        return "Chat";
    }

    @MessageMapping("/films/{channel_id}/chat")
    public void greeting(@DestinationVariable("channel_id") Long channelId,
                         Message message) throws Exception {

        message = messageService.addNew(message, channelId);

        template.convertAndSend("/topic/films/" + channelId + "/chat/messages", messageService.findLast20Messages(channelId));
    }

}
