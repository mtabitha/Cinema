package edu.school21.cinema.service;

import edu.school21.cinema.model.Message;
import edu.school21.cinema.model.User;
import edu.school21.cinema.repositories.MessageRepo;
import edu.school21.cinema.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private HttpServletResponse resp;

    @Autowired
    private HttpServletRequest req;

    private Comparator<Message> comp = Comparator.comparing(Message::getDateTime);

    public Message addNew(Message message, Long channelId) {

        Long userId = message.getChannelId();
        User userFromDB = userRepo.findById(userId).get();

        message.setChannelId(channelId);
        message.setUser(userFromDB);
        message.setDateTime(LocalDateTime.now());
        message = messageRepo.save(message);
        return message;
    }

    public void addCookie(Long channelId) {

        if (req.getCookies() == null) {
            User newUser = new User(LocalDateTime.now(), req.getRemoteAddr());
            newUser = userRepo.save(newUser);
            Cookie cookie = new Cookie("id", newUser.getId().toString());
            resp.addCookie(cookie);
        }
    }

    public List<Message> findLast20Messages(Long channelId) {
        List<Message> messages = messageRepo.findTop20ByChannelIdOrderByDateTimeDesc(channelId);
        messages.sort(comp);
        return  messages;
    }
}
